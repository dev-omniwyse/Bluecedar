package com.bluecedar.analyzer.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;

/**
 * 
 * @author Ramu Enugala
 *
 */

public interface BluecedarService {

	public String save(String json) throws IOException, ParseException, Exception;
	
	List<Map<String, Object>> searchByUserName(String name, String msgtype) throws Exception;
}
