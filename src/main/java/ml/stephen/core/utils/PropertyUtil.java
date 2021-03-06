package ml.stephen.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by Stephen on 16/9/6.
 * 配置文件工具类
 * 
 * <pre>
 * 启动加载: 
 * classpath*:properties/*.properties 文件中的配置
 * 
 * spring配置文件: 
 * classpath:spring/core/spring-core-common.xml
 * </pre>
 *
 */
@SuppressWarnings("rawtypes")
public class PropertyUtil extends PropertyPlaceholderConfigurer {

	private static Properties properties;

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

	public static String getProperty(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}

	public static void appendProperties(Properties paramProperties) {
		if (properties == null) {
			properties = paramProperties;
		} else {
			Enumeration enumeration = paramProperties.propertyNames();
			while (enumeration.hasMoreElements()) {
				String key = (String) enumeration.nextElement();
				properties.setProperty(key, paramProperties.getProperty(key));
			}
		}
	}

	@Override
	protected void processProperties(ConfigurableListableBeanFactory paramConfigurableListableBeanFactory, Properties paramProperties) throws BeansException {
		super.processProperties(paramConfigurableListableBeanFactory, paramProperties);
		appendProperties(paramProperties);
	}

}
