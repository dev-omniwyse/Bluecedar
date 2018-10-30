package com.bluecedar.analyzer.ut.test;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.message.BasicHeader;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bluecedar.analyzer.dao.BluecedarDao;
import com.bluecedar.analyzer.dao.impl.BluecedarDaoImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 
 * @author Ramu Enugala
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBluecedarDaoUT {
	
	BluecedarDao bluecedarDao;
	
	@Mock
    private RestHighLevelClient restHighLevelClient;
	
	@Mock
	private IndexResponse indexResponse;
	
	@Mock
	private GetResponse getResponse;
	
	@Mock
	private SearchResponse searchResponse;
	
	@Mock
	private Iterator<SearchHit> iterator;
	
	@Mock
	private SearchHits searchHits;
	
	@Mock
	private SearchHit searchHit;

	@Before
	public void setup(){
		bluecedarDao = new BluecedarDaoImpl(new ObjectMapper(), restHighLevelClient);
	}
	
	@Test
	public void saveTest() throws Exception {
		Mockito.when(restHighLevelClient.index(Mockito.any(IndexRequest.class))).thenReturn(indexResponse);
		Mockito.when(indexResponse.getId()).thenReturn("12345");
		
		ObjectMapper objectMapper = new ObjectMapper();
		JSONParser parser = new JSONParser(); 
		JSONObject json_obj = (JSONObject) parser.parse(Jsons.logJson);
		Map<String, Object> json_map = objectMapper.convertValue(json_obj, Map.class);
		String msgtype = (String) json_map.get("msgtype");
		
		
		String id = bluecedarDao.save(json_map,msgtype);
		assertEquals("12345",id);
	}
	
	
	@Test
	public void searchUserByNameTest() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		JSONParser parser = new JSONParser(); 
		JSONObject json_obj = (JSONObject) parser.parse(Jsons.logJson);
		Map<String, Object> dataMap = objectMapper.convertValue(json_obj, Map.class);
		
		Mockito.when(restHighLevelClient.search(Mockito.any(SearchRequest.class),Mockito.any(BasicHeader.class))).thenReturn(searchResponse);
		Mockito.when(searchResponse.getHits()).thenReturn(searchHits);
		Mockito.when(searchHits.iterator()).thenReturn(iterator);
		Mockito.when(iterator.hasNext()).thenReturn(true,true,false);
		Mockito.when(iterator.next()).thenReturn(searchHit);
		Mockito.when(searchHit.getSourceAsMap()).thenReturn(dataMap);
		List<Map<String, Object>> sourceList = bluecedarDao.searchByUserName("ramu","logs");
		assertEquals(2, sourceList.size());
	}
	
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void searchByNULLTeat() throws Exception {
		bluecedarDao.searchByUserName(null,"logs");
	}
	
}

