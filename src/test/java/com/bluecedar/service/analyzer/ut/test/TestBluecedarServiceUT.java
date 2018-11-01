package com.bluecedar.service.analyzer.ut.test;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bluecedar.service.analyzer.dao.BluecedarDao;
import com.bluecedar.service.analyzer.service.BluecedarService;
import com.bluecedar.service.analyzer.service.impl.BluecedarServiceImpl;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
/**
 * 
 * @author Ramu Enugala
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBluecedarServiceUT {
	
	@Mock
	BluecedarDao bluecedarDao;
	
	@InjectMocks
	BluecedarService bluecedarService = new BluecedarServiceImpl();

	@Before
	public void setup(){
		
	}
	
	@Test
	public void saveServiceTest() throws Exception {
		
		Mockito.when(bluecedarDao.save(Mockito.any(Map.class), Mockito.any(String.class))).thenReturn("12345");
		String id = bluecedarService.save(Jsons.logJson);
		assertEquals("12345",id);
	}
	
	@Test(expected = Exception.class)
	public void invalidJsonTest() throws Exception {
		String id = bluecedarService.save(Jsons.invalidJson);
	}
	
	@Test(expected = Exception.class)
	public void parsingJsonExceptionTest() throws Exception {
		String id = bluecedarService.save(Jsons.parsingExceptionJson);
	}
	
	@Test(expected = ProcessingException.class)
	public void jsonSchemaTest() throws Exception {
		String id = bluecedarService.save(Jsons.wrongJsonSchema);
	}
	
}

