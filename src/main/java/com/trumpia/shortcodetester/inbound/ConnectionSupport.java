package com.trumpia.shortcodetester.inbound;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.trumpia.shortcodetester.model.APIMessagePayload;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
@PropertySource("classpath:application.properties")

public class ConnectionSupport {
	@Autowired
	private Environment env;
	private static String postUrl;
	private static String contentType;
	private static String xApikey;
	private RequestBody body;
	private OkHttpClient client;
	private Response response = null;


	public Date SetConnection(APIMessagePayload subscription) {
		postUrl = env.getProperty("POST_URL");
		contentType=env.getProperty("content-type");
		xApikey=env.getProperty("x-apikey");

		client = new OkHttpClient();
		body = RequestBody.create(MediaType.parse("application/json"),subscription.toString());
		Request request = new Request.Builder().url(postUrl)
				.put(body)
				.addHeader("content-type", contentType)
				.addHeader("x-apikey", xApikey)
				.build();
		excuteConnection(request);
		return new Date();
	}
	
	public void excuteConnection(Request request) {
		
		Call call = client.newCall(request);
		try {
			response = call.execute();
			String msg = response.body().string();
			System.out.println(msg);
		} catch (IOException e) {
			excuteConnection(request);
		}

	}

}
