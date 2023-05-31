package com.mastercard.gpse.processing.configuration;

import org.springframework.beans.factory.annotation.Value;


public class APILoader {
	

	@Value("${create.client}")
	private String createClient_post;

}
