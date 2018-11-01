package com.bluecedar.service.analyzer.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.http.message.BasicHeader;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.bluecedar.service.analyzer.dao.BluecedarDao;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * 
 * @author Ramu Enugala
 *
 */
@Repository
public class BluecedarDaoImpl implements BluecedarDao{
	
	Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    private RestHighLevelClient restHighLevelClient;
    private ObjectMapper objectMapper;
    
    public BluecedarDaoImpl( ObjectMapper objectMapper, RestHighLevelClient restHighLevelClient) {
        this.objectMapper = objectMapper;
        this.restHighLevelClient = restHighLevelClient;
    }
	
    public String save(Map<String, Object> json, String msgtype) throws Exception {
		logger.info("Begin: save()");
		IndexResponse response = null;
		String result = null;
        try {
    		
    		json.put("id", UUID.randomUUID().toString());
    		IndexRequest indexRequest = new IndexRequest(msgtype, msgtype, (String)json.get("id")).source(json);
            response = restHighLevelClient.index(indexRequest);
            result = response.getId();
            
        } catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} catch(ElasticsearchException e) {
			logger.error(e.getDetailedMessage());
			e.printStackTrace();
            throw e;
        } catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
        logger.info("End: save()");
		return result;
	}

	public List<Map<String, Object>> searchByUserName(String name, String msgtype) throws Exception {
		logger.info("Begin: searchByUserName()");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		SearchResponse response = null;
		try {
			if(null != msgtype && (msgtype.equals("logs") || msgtype.equals("reports"))) {
				SearchRequest searchRequest = new SearchRequest(msgtype);
				SearchSourceBuilder ssb = new SearchSourceBuilder();
				ssb.query(QueryBuilders.matchQuery("devname", name));
				searchRequest.source(ssb);
				response = restHighLevelClient.search(searchRequest,new BasicHeader("Content-Type","application/json"));
				Iterator<SearchHit> i = response.getHits().iterator();
				Map<String, Object> map = null;
				while(i.hasNext()){
					SearchHit sh = i.next();
					map = sh.getSourceAsMap();
					list.add(map);
				}
			}else {
    			logger.error("Invalid message type provided");
    			throw new Exception("Invalid message type provided");
    		}
		}  catch(ElasticsearchException e) {
			logger.error(e.getDetailedMessage());
			e.printStackTrace();
            throw e;
        }  catch (IllegalArgumentException e) {
        	logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		logger.info("End: searchByUserName()");
		return list;
		
	}
	
	
	
	
}
