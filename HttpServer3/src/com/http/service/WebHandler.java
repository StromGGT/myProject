package com.http.service;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.http.entity.MappingEntity;
import com.http.entity.ServletEntity;

public class WebHandler extends DefaultHandler{
	private List<ServletEntity> servletList;
	private List<MappingEntity> mappingList;
	private ServletEntity servletEntity;
	private MappingEntity mappingEntity;
	private String tag;
	private boolean isMap;

	//文档解析开始
	@Override
	public void startDocument() throws SAXException {
		servletList = new ArrayList<ServletEntity>();
		mappingList = new ArrayList<MappingEntity>();
	}
	
	//文档解析结束
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
	}

	//元素解析开始
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if(qName != null) {
			tag = qName;
			if(qName.equals("servlet")) {
				isMap = false;
				servletEntity = new ServletEntity();
			}else if(qName.equals("servlet-mapping")) {
				isMap = true;
				mappingEntity = new MappingEntity();
			}
		}
	}
	
		
	//元素解析结束
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName != null) {
			if(qName.equals("servlet")) {
				servletList.add(servletEntity);
			}else if(qName.equals("servlet-mapping")) {
				mappingList.add(mappingEntity);
			}
		}		
		tag = null;
	}
	
	//解析内容
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String str = new String(ch, start, length);
		if(tag != null) {
			if(isMap==false) {
				if(tag.equals("servlet-name")) {
					servletEntity.setName(str);
				}else if(tag.equals("servlet-class")){
					servletEntity.setClz(str);
				}
			}else{
				if(tag.equals("servlet-name")) {
					mappingEntity.setName(str);
				}else if(tag.equals("url-pattern")) {
					mappingEntity.getUrlPattern().add(str);
				}
			}
		}
	}
	
	
	
	public List<ServletEntity> getServletList() {
		return servletList;
	}

	public List<MappingEntity> getMappingList() {
		return mappingList;
	}
	
}
