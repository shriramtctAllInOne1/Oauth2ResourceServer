/**
 * The ResourceServer Project, BSD License,Copyright (c) 2019
 * All rights reserved.
**/
package com.stock.oauth2.resourceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Shriram
 *
 */
@SpringBootApplication
@ComponentScan("com.stock.oauth2.resourceserver")
public class ResourceServer {

	public static void main(String[] args) {
		SpringApplication.run(ResourceServer.class);
	}
}
