package utils;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;


/**
 * 利用百度地图 根据地址获取经纬度信息
 * @author pact
 *
 */
public class LngAndLatUtil {
	public static Map<String,Double> getLngAndLat(String address){
		Map<String,Double> map=new HashMap<String, Double>();
		String url = "http://api.map.baidu.com/geocoder/v2/?address="+address+"&output=json&ak=10be3557d3f60c5c88fba0ff583a2e6b";
        String json = loadJSON(url);
        JSONObject obj = JSONObject.parseObject(json);
        if(obj.get("status").toString().equals("0")){
        	double lng=obj.getJSONObject("result").getJSONObject("location").getDouble("lng");
        	double lat=obj.getJSONObject("result").getJSONObject("location").getDouble("lat");
        	map.put("lng", lng);
        	map.put("lat", lat);
        }else{
        	return null;
        }
		return map;
	}
	
	 public static String loadJSON (String url) {
	        StringBuilder json = new StringBuilder();
	        try {
	            URL oracle = new URL(url);
	            URLConnection yc = oracle.openConnection();
	            BufferedReader in = new BufferedReader(new InputStreamReader(
	                                        yc.getInputStream()));
	            String inputLine = null;
	            while ( (inputLine = in.readLine()) != null) {
	                json.append(inputLine);
	            }
	            in.close();
	        } catch (MalformedURLException e) {
	        } catch (IOException e) {
	        }
	        return json.toString();
	    }
}
