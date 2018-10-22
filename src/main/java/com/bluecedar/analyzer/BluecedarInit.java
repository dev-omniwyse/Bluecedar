
package com.bluecedar.analyzer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @author Ramu Enugala
 *
 */

@SpringBootApplication(scanBasePackages="com.bluecedar.analyzer")
public class BluecedarInit {
	
	static Logger logger = (Logger) LoggerFactory.getLogger(BluecedarInit.class);
	
	 public static void main(String args[]) {
		 
		 SpringApplication.run(BluecedarInit.class, args);
		 logger.debug("Application started successfully...");
	 }

}
