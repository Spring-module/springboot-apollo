package com.kaiyun.springboot.apollo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;

@SpringBootApplication
@EnableApolloConfig({"application", "common","jdbc","redis"})
@ImportResource({"classpath:spring-ctx.xml"})
public class App {
//	static {O
//		System.setProperty("-Dapp.id","springboot-apollo");
//		System.setProperty("-Denv","dev");
//		System.setProperty("-Ddev_meta","http://192.168.1.115:9080");
//		System.setProperty("Dapollo.cacheDir","D:\\servers\\appconfig\\");
//	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
