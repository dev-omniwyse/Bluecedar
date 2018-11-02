package com.bluecedar.service.analyzer.service;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Ramu Enugala
 *
 */

public interface BluecedarService {

	public String save(String json) throws Exception;
	
	List<Map<String, Object>> searchByUserName(String name, String msgtype) throws Exception;
}
