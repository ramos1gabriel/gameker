package br.com.gameker.webapp;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class WebappApplication1 extends SpringBootServletInitializer {
	
	//PARA RODAR EM TOMCAT EXTERNO
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebappApplication1.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(WebappApplication1.class, args);
	}
}
