package com.websocketchat.jedis;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisHandleMessage {
	// 此範例key的設計為(發送者名稱:接收者名稱)，實際應採用(發送者會員編號:接收者會員編號)

	private static JedisPool pool = JedisPoolUtil.getJedisPool();

	public static List<String> getHistoryMsg(String sender, String receiver) {
		// 設置redis中的key值
		String key = new StringBuilder(sender).append(":").append(receiver).toString();

		// 建立連線(類似con)
		Jedis jedis = null;
		// 連線由連線值中取得
		jedis = pool.getResource();
		// 連線密碼
		jedis.auth("123456");

		// 取得對話訊息字串(key,一開始,最後)
		List<String> historyData = jedis.lrange(key, 0, -1);
		// 取得完關閉
		jedis.close();
		// 回傳歷史訊息
		return historyData;
	}

	public static void saveChatMessage(String sender, String receiver, String message) {
		// 對雙方來說，都要各存著歷史聊天記錄
		// 設置雙方的redis中k值
		String senderKey = new StringBuilder(sender).append(":").append(receiver).toString();
		String receiverKey = new StringBuilder(receiver).append(":").append(sender).toString();

		// 以下取得連線
		Jedis jedis = pool.getResource();
		jedis.auth("123456");

		// 儲存資料
		jedis.rpush(senderKey, message);
		jedis.rpush(receiverKey, message);

		try {
			if ("客服人員".equals(receiver)) {
				String haveChatfriendKey = "客服人員";
				List<String> historyName = jedis.lrange(haveChatfriendKey, 0, -1);

				// 存次數

				jedis.incr(sender);

//			int count = 1;
//			String divKey = sender;
//			String judgment = jedis.get(divKey);
//			if (judgment == null) {
//				String countString = String.valueOf(count);
//				jedis.set(divKey, countString);
//			} else {
//				count = Integer.parseInt(judgment);
//				count++;
//				jedis.del(divKey);
//				String countString = String.valueOf(count);
//				jedis.set(divKey, countString);
//			}

				for (String name : historyName) {
					if (name.equals(sender)) {
						jedis.close();
						return;
					}
				}
				jedis.rpush(haveChatfriendKey, sender);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		jedis.close();
	}

	// 管理者取得所有有跟他聊過天的業者
	public static List<String> getAllCompies(String sender) {
		// 建立連線(類似con)
		Jedis jedis = null;
		// 連線由連線值中取得
		jedis = pool.getResource();
		// 連線密碼
		jedis.auth("123456");

		List<String> compies = jedis.lrange(sender, 0, -1);
		jedis.close();
		return compies;
	}

	// 我要DIV拉!!!
	public static String getDiv(String receiver) {
		// 建立連線(類似con)
		Jedis jedis = null;
		// 連線由連線值中取得
		jedis = pool.getResource();
		// 連線密碼
		jedis.auth("123456");

		String count = jedis.get(receiver);
		System.out.println("count " + count);
		jedis.close();
		return count;
	}

	public void deleteCount(String receiver) {
		// 建立連線(類似con)
		Jedis jedis = null;
		// 連線由連線值中取得
		jedis = pool.getResource();
		// 連線密碼
		jedis.auth("123456");

		jedis.del(receiver);
		jedis.close();
	}
}
