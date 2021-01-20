package com.wendati.jedis;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rest_table.model.Rest_TableService;
import com.rest_table.model.Rest_TableVO;
import com.wendati.model.question;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisWenDaTi {
	// 此範例key的設計為(發送者名稱:接收者名稱)，實際應採用(發送者會員編號:接收者會員編號)

	private static JedisPool pool = JedisPoolUtil.getJedisPool();

	
	
	public static JsonObject random_get_rs_id() {
	    List<Rest_TableVO> rest_TableList  =new Rest_TableService().getAll();
	    Collections.shuffle(rest_TableList);
	   
	    Gson gson = new Gson();
	    String json= gson.toJson(rest_TableList.get(0));
	    
	    JsonParser jsonParser = new JsonParser();
	    JsonObject rest_Tablejson = jsonParser.parse(json).getAsJsonObject();
	 
	    return rest_Tablejson;
	}
	
	
	
	

	// 先把問題送到redis 儲存
	public static void one_ask() {
		
		Jedis jedis = pool.getResource();
		jedis.auth("123456");
		jedis.sadd("question", "{\"Q1\":\"你覺得生魚片好吃嗎?\",\"Q2\":\"喜歡丼飯?\"}");
		jedis.sadd("question", "{\"Q1\":\"薯條你覺得要加番茄醬嗎?\",\"Q2\":\"喜歡麥當勞還是肯德基?前者選Yes,後者選No\"}");
		jedis.sadd("question", "{\"Q1\":\"你常看韓劇嗎?\",\"Q2\":\"覺得自己是大胃王嗎?\"}");
		jedis.sadd("question", "{\"Q1\":\"你喜歡吃拉麵嗎?\",\"Q2\":\"吃生魚片時喜歡沾哇沙米嗎?\"}");
		jedis.sadd("question", "{\"Q1\":\"你喜歡美式文化嗎?\",\"Q2\":\"常常吃滷肉飯嗎?\"}");
		
		jedis.close();
	}

	// 從redis隨機取出問題
	public static String get_one_ask() {
		String key = "question";
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.auth("123456");
		String set = jedis.srandmember(key);
		jedis.close();

		return set;
	}





}
