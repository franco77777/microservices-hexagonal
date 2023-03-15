package com.pragma.square;

import com.whatsapp.api.WhatsappApiFactory;
import com.whatsapp.api.domain.messages.*;
import com.whatsapp.api.domain.messages.response.MessageResponse;
import com.whatsapp.api.domain.messages.type.ButtonType;
import com.whatsapp.api.domain.messages.type.InteractiveMessageType;
import com.whatsapp.api.domain.templates.type.ComponentType;
import com.whatsapp.api.domain.templates.type.LanguageType;
import com.whatsapp.api.exception.utils.Formatter;
import com.whatsapp.api.impl.WhatsappBusinessCloudApi;
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
public class SquareApplication {

	public static void main(String[] args) {
		SpringApplication.run(SquareApplication.class, args);
	}{
//		String number = "5493874692393";
//		String idOrder = "1234567890";
//		try {
//			HttpRequest request = HttpRequest.newBuilder()
//					.uri(new URI("https://graph.facebook.com/v16.0/108928785480520/messages"))
//					.header("Authorization", "Bearer EAADEmIApxS0BAN29QRQ2dvo06KsCu9dZC56vIi4J33iB7ZA65zVdh83oaqrT4WlkvpchjwZCZAHJFyCcfSkI7ANb8Jk7ZBnLoNkZCMNktiOTiW6Tgm70uI0hd7P8gUnZAShU86PReEphQXsuLe3CAwjyRSGa8lGD55DrZBnWzO9c6p8cH8LZAEjLFxMwwtLeQiLWTMoL72lzvHAZDZD")
//					.header("Content-Type", "application/json")
//					.POST(HttpRequest.BodyPublishers.ofString("{ \"messaging_product\": \"whatsapp\", \"recipient_type\": \"individual\", \"to\": \""+number+"\", \"type\": \"template\", \"template\": { \"name\": \"hello_world\", \"language\": { \"code\": \"en_US\" } } }"))
//					//.POST(HttpRequest.BodyPublishers.ofString("{ \"messaging_product\": \"whatsapp\", \"recipient_type\": \"individual\", \"to\": \""+number+"\", \"type\": \"text\", \"text\": { \"preview_url\": false, \"body\": \"Hi, your order: "+idOrder+" is ready\" } }"))
//					.build();
//			HttpClient http = HttpClient.newHttpClient();
//			HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
//			System.out.println(response.body());
//
//		} catch (URISyntaxException | IOException | InterruptedException e) {
//			e.printStackTrace();
//		}
//    try{
//		WhatsappApiFactory factory = WhatsappApiFactory.newInstance();
//
//		WhatsappBusinessCloudApi whatsappBusinessCloudApi = factory.newBusinessCloudApi();
//
//		var message = Message.MessageBuilder.builder()//
//				.setTo("5493874692393")//
//				.buildTemplateMessage(//
//						new TemplateMessage()//
//								.setLanguage(new Language(LanguageType.EN))//
//								.setName("order_client3")//
//								.addComponent(//
//										new Component(ComponentType.BODY)//
//												.addParameter(new TextParameter("francoaresis@gmail.com"))
//												.addParameter(new TextParameter("123"))
//
//
//								));
//
//		MessageResponse messageResponse = whatsappBusinessCloudApi.sendMessage("108928785480520", message);
//		System.out.println(messageResponse);
//
//	} catch (Exception e) {
//		System.out.println(e.getMessage());
//	}

			}


		}


