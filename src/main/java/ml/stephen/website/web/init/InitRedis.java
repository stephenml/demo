package ml.stephen.website.web.init;

import ml.stephen.constant.ServiceConstants;
import ml.stephen.website.web.init.service.InitRedisService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * Created by Stephen on 16/9/8.
 * Spring启动后 初始化redis缓存
 */
@Component
public class InitRedis implements InitializingBean, ServletContextAware {

	private String initConfig = ServiceConstants.INIT_ALL;

	@Autowired
	private InitRedisService initRedisService;

	@Override
	public void setServletContext(ServletContext servletContext) {
		String config = servletContext.getInitParameter("initConfig");
		if (config != null) {
			this.initConfig = config;
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (this.initConfig.equals(ServiceConstants.INIT_REDIS) || this.initConfig.equals(ServiceConstants.INIT_ALL)) {
			this.initRedisService.asyncInit();
		}
	}

}
