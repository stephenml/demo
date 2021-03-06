package ml.stephen.core.web;

import ml.stephen.core.utils.PropertyUtil;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.ToolContext;
import org.apache.velocity.tools.ToolManager;
import org.apache.velocity.tools.config.ConfigurationUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Stephen on 16/9/6.
 */
public class VelocityLayoutView extends org.springframework.web.servlet.view.velocity.VelocityLayoutView {

	private static ToolContext toolContext;

	@Override
	protected Context createVelocityContext(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		VelocityContext context = new VelocityContext(getToolContext());
		if (model != null) {
			for (Map.Entry<String, Object> entry : (Set<Map.Entry<String, Object>>) model.entrySet()) {
				context.put(entry.getKey(), entry.getValue());
			}
		}
		context.put("ctx", request.getContextPath());
		if ("zh".equals(request.getLocale().getLanguage())) {
			context.put("lang", "zh");
		} else {
			context.put("lang", "en");
		}
		
		context.put("attributes", initAttribute(request));
		
		ServletContext application = this.getServletContext();
		Object _version = application.getAttribute("_version");	// 用于发布解决页面缓存js问题，非真实版本号
		if (_version == null) {
			_version = new Date().getTime();
			application.setAttribute("_version", _version);
		}
		context.put("_version", _version);
		
		return context;
	}

	private ToolContext getToolContext() throws IllegalStateException, IOException {
		if (toolContext == null) {
			ToolManager toolManager = new ToolManager();
			toolManager.setVelocityEngine(this.getVelocityEngine());
			toolManager.configure(ConfigurationUtils.findInClasspath(this.getToolboxConfigLocation()));
			toolContext = toolManager.createContext();
		}
		return toolContext;
	}
	
	public Map<String, Object> initAttribute(HttpServletRequest request){
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("jsessionId", request.getSession().getId());
		attributes.put("path", request.getContextPath());
		attributes.put("production", request.getSession().getServletContext().getInitParameter("spring.profiles.default"));
		attributes.put("domainJs", StringUtils.isEmpty(PropertyUtil.getProperty("domain.js")) ? (request.getContextPath() + "/resource/js") : PropertyUtil.getProperty("domain.js"));
		attributes.put("domainImages", StringUtils.isEmpty(PropertyUtil.getProperty("domain.images") ) ? (request.getContextPath() + "/resource/images") : PropertyUtil.getProperty("domain.images"));
		attributes.put("domainCss",  StringUtils.isEmpty(PropertyUtil.getProperty("domain.css")) ? (request.getContextPath() + "/resource/css") : PropertyUtil.getProperty("domain.css"));

		return attributes;
	}

}
