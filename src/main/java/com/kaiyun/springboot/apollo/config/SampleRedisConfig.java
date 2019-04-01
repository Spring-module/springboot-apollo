/**
 * Project Name:springboot-apollo
 * File Name:SampleRedisConfig.java
 * Package Name:com.kaiyun.springboot.apollo.config
 * Date:2019年3月27日下午2:33:47
 * Copyright (c) 2019, kaiyun@qk365.com All Rights Reserved.
 *
*/

package com.kaiyun.springboot.apollo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName:SampleRedisConfig <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date:     2019年3月27日 下午2:33:47 <br/>
 * @author   kaiyun
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Configuration
@ConfigurationProperties(prefix = "redis.cache")
public class SampleRedisConfig {
  private int expireSeconds;
  private int commandTimeout;

  public void setExpireSeconds(int expireSeconds) {
    this.expireSeconds = expireSeconds;
  }

  public void setCommandTimeout(int commandTimeout) {
    this.commandTimeout = commandTimeout;
  }

  public int getExpireSeconds() {
    return expireSeconds;
  }

  public int getCommandTimeout() {
    return commandTimeout;
  }
}
