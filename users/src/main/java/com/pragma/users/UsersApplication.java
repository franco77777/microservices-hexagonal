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
	{
//		try {
//			HttpRequest request = HttpRequest.newBuilder()
//					.uri(new URI("https://graph.facebook.com/v16.0/108928785480520/messages"))
//					.header("Authorization", "Bearer EAADEmIApxS0BAI23iSUA8hKqi0NXv58UniJSErM8G0l65CkKbUDlmqoHOOHr8DJssQMb5loPEklk2nabrZApz1TO8ZBrW9zJsOllpNGocYAmyZC0T5xfBslajfn0JZCKzaDO0noZBAc6cXDBfubYqMDjOiV26EaxRvAkxZBZCEQ5kPLEQWfSdUAu9JZCA6evvXqYzRBnIDrPsQZDZD")
//					.header("Content-Type", "application/json")
//					//.POST(HttpRequest.BodyPublishers.ofString("{ \"messaging_product\": \"whatsapp\", \"recipient_type\": \"individual\", \"to\": \"5493874692393\", \"type\": \"template\", \"template\": { \"name\": \"hello_world\", \"language\": { \"code\": \"en_US\" } } }"))
//					.POST(HttpRequest.BodyPublishers.ofString("{ \"messaging_product\": \"whatsapp\", \"recipient_type\": \"individual\", \"to\": \"5493874692393\", \"type\": \"text\", \"text\": { \"preview_url\": false, \"body\": \"This is an example of a text message\" } }"))
//					.build();
//			HttpClient http = HttpClient.newHttpClient();
//			HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
//			System.out.println(response.body());
//
//		} catch (URISyntaxException | IOException | InterruptedException e) {
//			e.printStackTrace();
//		}
	}

}
