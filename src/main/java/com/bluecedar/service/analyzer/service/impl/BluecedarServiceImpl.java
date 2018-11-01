package com.bluecedar.service.analyzer.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluecedar.service.analyzer.dao.BluecedarDao;
import com.bluecedar.service.analyzer.service.BluecedarService;
import com.bluecedar.service.analyzer.utils.ValidationUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Ramu Enugala
 *
 */
@Service
public class BluecedarServiceImpl implements BluecedarService{

	Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	private String logsSchema;
	private String reportsSchema;
	@Autowired
	BluecedarDao bluecedarDao;
	
	
	public BluecedarServiceImpl() {
		try {
			logsSchema = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("schema/logs_schema.json"),StandardCharsets.UTF_8);
			reportsSchema = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("schema/reports_schema.json"),StandardCharsets.UTF_8);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				throw new Exception("Schema files not loaded properly");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public String save(String json) throws Exception {
		logger.info("Begin: save()");
		String id = null;
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JSONParser parser = new JSONParser(); 
			JSONObject json_obj = (JSONObject) parser.parse(json);
			Map<String, Object> json_map = objectMapper.convertValue(json_obj, Map.class);
			String msgtype = (String) json_map.get("msgtype");
			if(null != msgtype && (msgtype.equals("logs") || msgtype.equals("reports"))) {
				if(ValidationUtils.isJsonValid(msgtype.equals("logs")?logsSchema:reportsSchema, json)) {
					id =  bluecedarDao.save(json_map,msgtype);
				}
			}else {
				logger.error("Invalid message type in json");
				throw new Exception("Invalid message type in json");
			}
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error("Json parsing exception. Please verify your json");
			throw e;
		} catch(Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		logger.info("End: save()");
		return id;
	}

	@Override
	public List<Map<String, Object>> searchByUserName(String name, String msgtype) throws Exception {
		logger.info("Begin: searchByUserName()");
		List<Map<String, Object>> list = null;
		list = bluecedarDao.searchByUserName(name,msgtype);;
		logger.info("End: searchByUserName()");
		return list;
	}

}
