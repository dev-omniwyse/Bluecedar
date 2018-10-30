package com.bluecedar.analyzer.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluecedar.analyzer.dao.BluecedarDao;
import com.bluecedar.analyzer.service.BluecedarService;

/**
 * 
 * @author Ramu Enugala
 *
 */
@Service
public class BluecedarServiceImpl implements BluecedarService{

	Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BluecedarDao bluecedarDao;
	
	
	public String save(String json) throws Exception {
		logger.info("Begin: save()");
		String id = null;
		id =  bluecedarDao.save(json);
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
