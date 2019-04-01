/**
 * Project Name:springboot-apollo
 * File Name:HelloController.java
 * Package Name:com.kaiyun.springboot.apollo.controller
 * Date:2019年3月27日下午1:40:58
 * Copyright (c) 2019, kaiyun@qk365.com All Rights Reserved.
 *
*/

package com.kaiyun.springboot.apollo.controller;

import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.ctrip.framework.apollo.spring.annotation.ApolloJsonValue;
import com.kaiyun.springboot.apollo.config.JavaConfigBean;
import com.kaiyun.springboot.apollo.config.SampleRedisConfig;
import com.kaiyun.springboot.apollo.pojo.User;
import com.kaiyun.springboot.apollo.utils.PropertiesApolloUtil;
import com.kaiyun.springboot.apollo.utils.PropertiesUtils;

/**
 * ClassName:HelloController <br/>
 * Function: https://blog.csdn.net/aaronsimon/article/details/83657612. <br/>
 * Date: 2019年3月27日 下午1:40:58 <br/>
 * 
 * @author kaiyun
 * @version
 * @since JDK 1.8
 * @see
 */
@RestController
public class HelloController {

	@Value("${hello:apollo}")
	private String hello;
	
	@Value("${param1:默认值}")
	private String param1;

	@Autowired
	private JavaConfigBean javaConfigBean;

	@Autowired
	private SampleRedisConfig sampleRedisConfig;

	@ApolloConfig
	private Config config;
	
	@ApolloJsonValue("${jsonBeanProperty:[]}")
	private List<User> anotherJsonBeans;
	
	
	
	@RequestMapping("/param1")
	public String param1() {
		System.out.println(PropertiesApolloUtil.getPropString("param1"));
		return "hello " + param1;
	}

	/**
	 * hello1:Spring Placeholder的使用. <br/>
	 * Spring应用通常会使用Placeholder来注入配置，使用的格式形如${someKey:someDefaultValue}，
	 * 如${timeout:100}。冒号前面的是key，冒号后面的是默认值（建议在实际使用时尽量给出默认值，以免由于key没有定义导致运行时错误）。
	 * Apollo从v0.10.0开始的版本支持placeholder在运行时自动更新。
	 * 如果需要关闭placeholder在运行时自动更新功能，可以通过以下两种方式关闭： 通过设置System Property
	 * apollo.autoUpdateInjectedSpringProperties，如启动时传入-Dapollo.autoUpdateInjectedSpringProperties=false
	 * 通过设置META-INF/app.properties中的apollo.autoUpdateInjectedSpringProperties=false
	 *
	 * @return
	 * @since JDK 1.8
	 * @author kaiyun
	 */
	@RequestMapping("/hello1")
	public String hello1() {
		return "hello " + hello;
	}

	/**
	 * hello2: Java Config使用方式. <br/>
	 *
	 * @return
	 * @since JDK 1.8
	 * @author kaiyun
	 */
	@RequestMapping("/hello2")
	public String hello2() {
		return javaConfigBean.getTimeout() + "";
	}

	/**
	 * hello3:ConfigurationProperties使用方式. <br/>
	 * Spring Boot提供了@ConfigurationProperties把配置注入到bean对象中。Apollo也支持这种方式，下面的例子会把
	 * redis.cache.expireSeconds和redis.cache.commandTimeout分别注入到SampleRedisConfig的expireSeconds和commandTimeout字段中。
	 * 注： @ConfigurationProperties如果需要在Apollo配置变化时自动更新注入的值，需要配合使用EnvironmentChangeEvent或RefreshScope。这个我会在后续文章中详细描述。
	 *
	 * @return
	 * @since JDK 1.8
	 * @author kaiyun
	 */
	@RequestMapping("/hello3")
	public String hello3() {
		return sampleRedisConfig.getCommandTimeout() + "--" + sampleRedisConfig.getExpireSeconds();
	}

	/**
	 * hello4:@ApolloConfig的使用. <br/>
	 *
	 * @return
	 * @since JDK 1.8
	 * @author kaiyun
	 */
	@RequestMapping("/hello4")
	public String hello4() {
		System.out.println("-----" + ConfigService.getAppConfig().getProperty("hello", "apollo"));
		Set<String> propertyNames = config.getPropertyNames();
		propertyNames.forEach(key -> {
			System.err.println(key + "=" + config.getIntProperty(key, 0));
		});
		return propertyNames.toString();
	}

	/**
	 * someOnChange:@ApolloConfigChangeListener的使用. <br/>
	 * 	在Apollo服务端修改timeout配置的值为300，发布后，控制台打印300
	 *
	 * @param changeEvent
	 * @since JDK 1.8
	 * @author kaiyun
	 */
	@ApolloConfigChangeListener
	private void someOnChange(ConfigChangeEvent changeEvent) {
		// update injected value of batch if it is changed in Apollo
		if (changeEvent.isChanged("timeout")) {
			System.out.println(config.getIntProperty("timeout", 0));
		}
	}
	
	/**
	 * hello5:@ApolloJsonValue的使用. <br/>
	 *
	 * @since JDK 1.8
	 * @author kaiyun
	 */
	@RequestMapping("/hello5")
	public void hello5(){
//		服务端新增配置
//		jsonBeanProperty=[ { "username": "john", "password": "1234" }, { "username": "simon", "password": "222132" } ]
	  anotherJsonBeans.forEach(item -> {
	    System.err.println(item.getUsername()+"--"+item.getPassword());
	  });
	}
	
	@GetMapping("/all")
    public Properties apolloReadDemo(){
       // 得到当前app.id中的配置
        Set<String> set = config.getPropertyNames();
        for(String key : set){
            PropertiesUtils.properties.setProperty(key,config.getProperty(key,null));
        }
        for(String key : PropertiesUtils.properties.stringPropertyNames()){
            System.out.println(key+">>>"+PropertiesUtils.properties.getProperty(key));
        }
        return PropertiesUtils.properties;
    }
}
