package com.ibm.w3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulFilterConfig {
	
	  @Bean
	  public PreFilter preFilter() {
		  return new PreFilter();
	  }

}
