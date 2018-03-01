package com.http.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.http.entity.MappingEntity;
import com.http.entity.ServletContext;
import com.http.entity.ServletEntity;
import com.http.servlet.Servlet;

public class WebApp {
	private static ServletContext context;
	
	static {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			WebHandler handler = new WebHandler();
			parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("WEB_INF/web.xml"), 
					handler);
			context = new ServletContext();
			
			Map<String, String> mapping = context.getMapping();
			for(MappingEntity entity : handler.getMappingList()) {
				List<String> list = entity.getUrlPattern();
				for(String url : list) {
					mapping.put(url, entity.getName());
				}
			}
			
			Map<String, String> servlet = context.getServlet();
			for(ServletEntity entity : handler.getServletList()) {
				servlet.put(entity.getName(), entity.getClz());
			}
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public static Servlet getServlet(String url) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if((url == null) || ((url=url.trim())).equals("")) {
			return null;
		}
		//根据URL得到name，在得到类名(通过map)
		String name = context.getServlet().get(context.getMapping().get(url));
		//根据反射创建对象 
		return (Servlet)Class.forName(name).newInstance();//确保空构造存在
	}

}
