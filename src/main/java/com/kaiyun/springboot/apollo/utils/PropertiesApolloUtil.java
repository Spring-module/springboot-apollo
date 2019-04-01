/**
 * Project Name:springboot-apollo
 * File Name:ApolloUtil.java
 * Package Name:com.kaiyun.springboot.apollo.utils
 * Date:2019年3月27日下午3:09:52
 * Copyright (c) 2019, kaiyun@qk365.com All Rights Reserved.
 *
*/

package com.kaiyun.springboot.apollo.utils;
/**
 * ClassName:ApolloUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date:     2019年3月27日 下午3:09:52 <br/>
 * @author   kaiyun
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */

import com.ctrip.framework.apollo.ConfigService;

public class PropertiesApolloUtil {
	
	public static String getPropString(String key) {
		String obj = ConfigService.getAppConfig().getProperty(key, null);
		if (obj==null) {
			obj = ConfigService.getConfig("common").getProperty(key, null);
		}
		if (obj==null) {
			obj = ConfigService.getConfig("remoteService").getProperty(key, null);
		}
		return obj;
	}
	
	public static Integer getPropInteger(String key) {
		String str = ConfigService.getAppConfig().getProperty(key, null);
		Integer obj = str==null?null:Integer.parseInt(str);
		if (obj==null) {
			obj = ConfigService.getConfig("common").getIntProperty(key, null);
		}
		if (obj==null) {
			obj = ConfigService.getConfig("remoteService").getIntProperty(key, null);
		}
		return obj;
	}
	
	public static Long getPropLong(String key) {
		String str = ConfigService.getAppConfig().getProperty(key, null);
		Long obj = str==null?null:Long.parseLong(str);
		if (obj==null) {
			obj = ConfigService.getConfig("common").getLongProperty(key, null);
		}
		if (obj==null) {
			obj = ConfigService.getConfig("remoteService").getLongProperty(key, null);
		}
		return obj;
	}
}

