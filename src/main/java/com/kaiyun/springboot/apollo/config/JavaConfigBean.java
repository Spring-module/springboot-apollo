/**
 * Project Name:springboot-apollo
 * File Name:JavaConfigBean.java
 * Package Name:com.kaiyun.springboot.apollo.config
 * Date:2019年3月27日下午2:22:00
 * Copyright (c) 2019, kaiyun@qk365.com All Rights Reserved.
 *
*/

package com.kaiyun.springboot.apollo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName:JavaConfigBean <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date: 2019年3月27日 下午2:22:00 <br/>
 * 
 * @author kaiyun
 * @version
 * @since JDK 1.8
 * @see
 */
@Configuration
public class JavaConfigBean {

	@Value("${timeout:20}")
	private int timeout;

	public int getTimeout() {
		return timeout;
	}

}
