package main.java.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

	public static void main(String[] args) {
		String url = "";
		String data = "[]";
		String authorization = "";
		String result = sendHttpPOSTWithJson(url, data, authorization);
		System.out.println(result);
	}

	public static String sendHttpPOSTWithJson(String url, String data, String authorization) {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		post.setHeader("Authorization", authorization);
		String result = "";
		try {
			StringEntity s = new StringEntity(data);
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");

			post.setEntity(s);
			HttpResponse res = client.execute(post);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = res.getEntity();
				result = EntityUtils.toString(entity);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

}
