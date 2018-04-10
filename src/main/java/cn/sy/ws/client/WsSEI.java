package cn.sy.ws.client;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(name="WeatherWebServiceSoap")
public interface WsSEI {

	@WebMethod(action="http://WebXml.com.cn/getWeatherbyCityName",
			operationName="getWeatherbyCityName")
	public @WebResult(name="getWeatherbyCityNameResult") List<String> getWeatherbyCityName(@WebParam(name="theCityName") String theCityName);

	@WebMethod(action="http://WebXml.com.cn/getSupportCity",
			operationName="getSupportCity")
	public @WebResult(name="getSupportCityResult") List<String> getSupportCity(@WebParam(name="byProvinceName") String byProvinceName);
}
