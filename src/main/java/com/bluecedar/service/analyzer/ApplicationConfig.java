package com.bluecedar.service.analyzer;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
 * @author Ramu Enugala
 *
 */
@Configuration
public class ApplicationConfig extends AbstractFactoryBean<RestHighLevelClient> implements  WebMvcConfigurer , WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${es.httpports}")
    private String httpPorts;
    
	@Value("${es.host}")
    private String host;
	
    @Value("${es.isSecured}")
    private boolean isSecured;
    
	@Value("${es.userName}")
    private String userName;
	
	@Value("${es.password}")
    private String password;

    @Override
    public Class<RestHighLevelClient> getObjectType() {
        return RestHighLevelClient.class;
    }

    @Override
    public RestHighLevelClient createInstance() {
        return buildClient();
    }

    private RestHighLevelClient buildClient() {
    	RestHighLevelClient client = null;
        try {
        	logger.info("Begin: buildClient()");
    		logger.debug("isAuthenticES -> "+isSecured +"\n ES httpPort -> "+httpPorts+"\nES host -> "+host);
    		
        	if(isSecured) {
        		// With Authentication
        		final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            	credentialsProvider.setCredentials(AuthScope.ANY,
            	        new UsernamePasswordCredentials(userName, password));

            	RestClientBuilder builder = RestClient.builder(getHttpPorts())
            	        .setHttpClientConfigCallback((HttpAsyncClientBuilder httpClientBuilder)-> {
            	        	return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            	        });

            	client = new RestHighLevelClient(builder);
        	}else {
        		// With out Authentication
        		client = new RestHighLevelClient(
                        RestClient.builder(getHttpPorts()));
        	}
        	
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
        logger.info("End: buildClient()");
		return client;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new RequestInterceptor());
	}
    
    private HttpHost[] getHttpPorts() {
    	
    	List<HttpHost> hostslist = new ArrayList<>();
    	String[] ports = httpPorts.split(",");
    	for (int i =0;i<ports.length;i++) {
    		if(!"".equals(ports[i].trim())) {
    			hostslist.add(new HttpHost(host, Integer.parseInt(ports[i].trim()), "http"));
    		}
		}
    	HttpHost[] hosts = new HttpHost[hostslist.size()];
    	hosts = hostslist.toArray(hosts);
		return hosts;
    	
    }

    @Override
    public void customize(ConfigurableServletWebServerFactory server) {
      server.setContextPath("/api/v1");
    }

}
