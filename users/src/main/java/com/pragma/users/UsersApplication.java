package com.pragma.users;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;



@SpringBootApplication
@EnableFeignClients
public class UsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}
//	{
//		try {
//			HttpRequest request = HttpRequest.newBuilder()
//					.uri(new URI("https://graph.facebook.com/v16.0/107099585663853/messages"))
//					.header("Authorization", "Bearer EAADEmIApxS0BAJrXQ71r1JKdIUKA8FURMFUjLAJ2IR5g8LjartlvFmPNnG9nOVq6SlT2ndZAtbRRm6NfAmJWnhnGwydNU8hhyAqELkHMNI3rLwhWd8ZBZC1iQZAHmv2A3gzurNoBILMu5FjZATY62dF46zvxAuDT3PtIs7y4BkJxYgTHDz1GiTTokkKfCSZByWAmZAVGEGvgwZDZD")
//					.header("Content-Type", "application/json")
//					.POST(HttpRequest.BodyPublishers.ofString("{ \"messaging_product\": \"whatsapp\", \"recipient_type\": \"individual\", \"to\": \"5493875295146\", \"type\": \"text\", \"text\": { \"preview_url\": false, \"body\": \"This is an example of a text message\" } }"))
//					.build();
//			HttpClient http = HttpClient.newHttpClient();
//			HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
//			System.out.println(response.body());
//
//		} catch (URISyntaxException | IOException | InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

}
