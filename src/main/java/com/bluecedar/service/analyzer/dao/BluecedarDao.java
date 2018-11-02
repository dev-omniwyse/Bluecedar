package com.bluecedar.service.analyzer.dao;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * 
 * @author Ramu Enugala
 *
 */

public interface BluecedarDao {

	public String save(Map<String, Object> json, String msgtype) throws IOException;
	
	List<Map<String, Object>> searchByUserName(String name, String msgtype) throws Exception;
}
