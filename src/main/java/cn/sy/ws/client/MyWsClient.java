package cn.sy.ws.client;

import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class MyWsClient {

	public static void invoke() {
		try {
			System.out.println("start invoke");
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
			factory.setServiceClass(WsSEI.class);
			factory.setAddress("http://ws.webxml.com.cn/WebServices/WeatherWebService.asmx");
		    WsSEI sv = (WsSEI) factory.create();
	
		    System.out.println("got sv");
//		    List<String> result = sv.getWeatherbyCityName("北京");
		    List<String> result = sv.getSupportCity(null);
			
		    System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
