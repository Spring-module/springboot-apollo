package com.kaiyun.springboot.springboot_apollo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class) // 整合
@ContextConfiguration(locations = { "classpath:spring-ctx.xml" }) // 加载配置
public class AppTest {
	
	@Test
	public void testOne() {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
