package com.test.common;

import java.net.URL;

import org.codehaus.xfire.client.Client;

public class TestWebService {
	
	public static Client getWebServiceConnect(String wsurl){
		Client client = null;
		try {
			client = new Client(new URL(wsurl));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return client;
	}
	
	public static String callService(String wsurl, String method, Object[] params){
		Client client = TestWebService.getWebServiceConnect(wsurl);
		Object[] results = null;
		try {
			results = client.invoke(method, params);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return String.valueOf(results[0]);
	}
	

	@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
	public static void main(String[] args) {
		Object[] params = new Object[1];
		params[0] = "{\"username\" : \"zhangsan\",\"password\" : \"123\"}";
//		params[0] = "{\"id\" : \"group_机房\",\"名称\" : \"机房\",\"描述\" : \"说明\",\"授权范围\" : {\"scene-1433239903070-123\": [" +
//			            "\"动力楼P120\",\"WATERB\",\"P117\"]},\"附加范围\" :{\"scene-1433239903070-123\": [\"CN010UINNVOADEMOII1051\","+
//			            "\"动力楼\",\"动力楼1\",\"生产楼\",\"生产楼1\"]},\"授权权限\" : \"11000\",\"附加权限\" : \"10000\"}";
		System.out.println(TestWebService.callService("http://localhost:8080/dcv_jupiter1/services/DataStream?wsdl", "addUser", params));
	}

}
