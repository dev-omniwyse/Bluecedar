
package com.bluecedar.service.analyzer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author Ramu Enugala
 *
 */

@SpringBootApplication(scanBasePackages="com.bluecedar.service")
public class BluecedarInit {
	
	static Logger logger = LoggerFactory.getLogger(BluecedarInit.class);
	
	 public static void main(String args[]) {
		 
		 SpringApplication.run(BluecedarInit.class, args);
		 logger.debug("Application started successfully...");
	 }

}
