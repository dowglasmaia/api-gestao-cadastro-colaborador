package com.maia.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.maia.utils.testIntegrations.TestsIntegrationsDB;

@Configuration
@Profile("test")
public class ProfileTestIntegrationsConfig {

	@Autowired
	private TestsIntegrationsDB dbTestsServices;

	@Bean
	public boolean instantiateDataBase() throws ParseException {

		dbTestsServices.instanciateTestDatabase();
		return true;
	}

}
