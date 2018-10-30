package com.bluecedar.analyzer.it.test

import static org.junit.Assert.assertEquals
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

import java.util.concurrent.TimeUnit

import org.json.simple.JSONArray
import org.json.simple.parser.JSONParser
import org.junit.FixMethodOrder
import org.junit.runners.MethodSorters
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

import com.bluecedar.analyzer.BluecedarInit
import com.bluecedar.analyzer.controller.ApiController
import com.bluecedar.analyzer.ut.test.Jsons
import com.fasterxml.jackson.databind.ObjectMapper

import pl.allegro.tech.embeddedelasticsearch.EmbeddedElastic
import pl.allegro.tech.embeddedelasticsearch.IndexSettings
import pl.allegro.tech.embeddedelasticsearch.PopularProperties
import spock.lang.Specification
/**
 *
 * @author Ramu Enugala
 *
 */
@TestPropertySource(locations = "classpath:application-intigration.properties")
@SpringBootTest(classes = BluecedarInit.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class TestBluecedarSpockIT extends Specification {

	MockMvc mvc;
	
	@Autowired
	ApiController bluecedarController;
	
	@Value('${es.port}')
	private int EsTransportPort;

	@Value('${es.clustername}')
	private String EsClusterName;

	@Value('${es.httpports}')
	private int httpPort;

	static EmbeddedElastic embeddedESCluster = null;
	
	
	def setup() {
		this.mvc = standaloneSetup(this.bluecedarController).build();
		if (null == embeddedESCluster) {
			embeddedESCluster = EmbeddedElastic.builder().withElasticVersion("6.0.0")
			.withSetting(PopularProperties.TRANSPORT_TCP_PORT, EsTransportPort)
			.withSetting(PopularProperties.CLUSTER_NAME, EsClusterName)
			.withSetting(PopularProperties.HTTP_PORT, httpPort)
			.withEsJavaOpts("-Xms128m -Xmx2g")
			.withStartTimeout(60, TimeUnit.SECONDS)
			.withIndex("testing", IndexSettings.builder().build())
			.withCleanInstallationDirectoryOnStop(false)
			.build()
			.start();
		}
	}
	
	def cleanupSpec() {
		if(null != embeddedESCluster) 
			embeddedESCluster.stop();
	}
	
	
	def "Test1 - Json should save successfully in ES"() {
		given: 
			def json1 = Jsons.logJson
			def json2 = Jsons.reportJson
		when:
			 def results1 = mvc.perform(post("/analyzer/payload").contentType(MediaType.APPLICATION_JSON).content(json1))
			 def results2 = mvc.perform(post("/analyzer/payload").contentType(MediaType.APPLICATION_JSON).content(json2))
		then:
			results1.andExpect(MockMvcResultMatchers.status().isCreated())
			results2.andExpect(MockMvcResultMatchers.status().isCreated())
	}
	
	def "Test2 - Search json by devname 'bluecedar'"() {
		Thread.currentThread().sleep(2000);
		given:
			def name1 = "bluecedar_log";
			def index1 = "logs"
			def name2 = "bluecedar_report";
			def index2 = "reports"
		when:
			def results1 = mvc.perform(get("/analyzer/payload/"+index1+"/"+name1).contentType(MediaType.APPLICATION_JSON))
			def results2 = mvc.perform(get("/analyzer/payload/"+index2+"/"+name2).contentType(MediaType.APPLICATION_JSON))
		then:
			MvcResult mvcRes1 =  results1.andExpect(MockMvcResultMatchers.status().isOk()).andReturn()
			MvcResult mvcRes2 =  results2.andExpect(MockMvcResultMatchers.status().isOk()).andReturn()
		and:
			ObjectMapper objectMapper = new ObjectMapper();
			JSONParser parser = new JSONParser(); 
			
			JSONArray json_array1 = (JSONArray) parser.parse(mvcRes1.getResponse().getContentAsString());
			List<Map<String, Object>> datalist1 = objectMapper.convertValue(json_array1, List.class);
			
			JSONArray json_array2 = (JSONArray) parser.parse(mvcRes2.getResponse().getContentAsString());
			List<Map<String, Object>> datalist2 = objectMapper.convertValue(json_array2, List.class);
			
			assertEquals(1, datalist1.size());
			assertEquals(1, datalist2.size());
	}
	
	def "Test3 - Save should throw parsing exception"() {
		given:
			def json = Jsons.parsingExceptionJson
		when:
			 mvc.perform(post("/analyzer/payload").contentType(MediaType.APPLICATION_JSON).content(json))
		then:
			org.springframework.web.util.NestedServletException ex = thrown()
			org.assertj.core.api.Assertions.assertThat(ex).hasCauseExactlyInstanceOf(org.json.simple.parser.ParseException.class);
			
	}
	
	def "Test4 - Save should throw exception for invalid index"() {
		given:
			def json = Jsons.invalidJson
		when:
			 mvc.perform(post("/analyzer/payload").contentType(MediaType.APPLICATION_JSON).content(json))
		then:
			org.springframework.web.util.NestedServletException ex = thrown()
			org.assertj.core.api.Assertions.assertThat(ex).hasCauseExactlyInstanceOf(Exception.class);
			
	}
	
	def "Test5 - Save should throw ProcessingException(schema validation) exception"() {
		given:
			def json = Jsons.wrongJsonSchema
		when:
			 mvc.perform(post("/analyzer/payload").contentType(MediaType.APPLICATION_JSON).content(json))
		then:
			org.springframework.web.util.NestedServletException ex = thrown()
			org.assertj.core.api.Assertions.assertThat(ex).hasCauseExactlyInstanceOf(com.github.fge.jsonschema.core.exceptions.ProcessingException.class);
			
	}
	
	def "Test6 - Search json should throw exception for invalid index"() {
		given:
			def name = "bluecedar";
			def index = "invalid_index"
		when:
			mvc.perform(get("/analyzer/payload/"+index+"/"+name).contentType(MediaType.APPLICATION_JSON))
		then:
			org.springframework.web.util.NestedServletException ex = thrown()
			org.assertj.core.api.Assertions.assertThat(ex).hasCauseExactlyInstanceOf(Exception.class);
			
	}
	
	
}