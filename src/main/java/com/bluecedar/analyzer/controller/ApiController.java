package com.bluecedar.analyzer.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluecedar.analyzer.service.BluecedarService;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author Ramu Enugala
 *
 */

@RestController
@RequestMapping("/analyzer")
public class ApiController {

	Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BluecedarService bluecedarService;
	
	@PostMapping(path = "/payload" , consumes = "application/json" )
	@ApiOperation(value = "Save Json",
	    notes = "This endpoint will save the json into elastic search")
	public ResponseEntity<?> savePayload(@RequestBody String json) throws Exception{
		logger.info("Begin: save()");
		logger.debug("Json -> "+json);
		String id = null;
		try {
			id =  bluecedarService.save(json);
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		logger.info("End: save()");
		return new ResponseEntity<String>(id, HttpStatus.CREATED);
	}

	@GetMapping(path =  "/payload/{msgtype}/{name}", produces = "application/json" )
	@ApiOperation(value = "Fetch json by devname",
    	notes = "This endpoint will fetch the json from elastic search based on 'devname' field")
	public ResponseEntity<?> getPayload(@PathVariable("name") String name , @PathVariable("msgtype") String msgtype) throws Exception {
		logger.info("Begin: getByUseName() method and name is "+name +" msgtype is "+msgtype);
		List<Map<String, Object>> list = null;
		
		try {
			list =  bluecedarService.searchByUserName(name,msgtype);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		logger.info("End: getByUseName()");
		return new ResponseEntity<List<Map<String, Object>>>(list, HttpStatus.OK);
	}
	
}
