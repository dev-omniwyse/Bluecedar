package com.bluecedar.analyzer.dao;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;

/**
 * 
 * @author Ramu Enugala
 *
 */

public interface BluecedarDao {

	public String save(Map<String, Object> json, String msgtype) throws IOException, ParseException, Exception;
	
	List<Map<String, Object>> searchByUserName(String name, String msgtype) throws Exception;
}
