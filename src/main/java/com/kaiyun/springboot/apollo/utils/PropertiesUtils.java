/**
 * Project Name:springboot-apollo
 * File Name:PropertiesUtils.java
 * Package Name:com.kaiyun.springboot.apollo.utils
 * Date:2019年3月28日上午9:22:31
 * Copyright (c) 2019, kaiyun@qk365.com All Rights Reserved.
 *
*/

package com.kaiyun.springboot.apollo.utils;

import java.util.Properties;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;

/**
 * ClassName: PropertiesUtils <br/>
 * Function: 读取公共apollo配置. <br/>
 * date: 2019年3月28日 上午9:23:12 <br/>
 *
 * @version 
 * @since JDK 1.8
 * @author kaiyun
 */
public class PropertiesUtils {
    private static final String COMMON = "common";
    public static Properties properties = new Properties();
    
    static {
        Config commonConfig = ConfigService.getConfig(COMMON);
        if(commonConfig != null){
            for(String key : commonConfig.getPropertyNames()){
                properties.setProperty(key,commonConfig.getProperty(key,null));
            }
        }
    }
}
