package com.pragma.users.domain.usecase;
import com.pragma.users.domain.api.IUserServicePort;
import com.pragma.users.domain.exception.DomainException;
import com.pragma.users.domain.model.UserModel;
import com.pragma.users.domain.spi.IUserPersistencePort;
import com.pragma.users.infrastructure.output.utils.AuthorityName;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final PasswordEncoder passwordEncoder;

    public UserUseCase(IUserPersistencePort userPersistencePort, PasswordEncoder passwordEncoder) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserModel saveUser(UserModel userModel, AuthorityName role) {
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userModel.setRoles(role);
        UserModel savedUser = userPersistencePort.saveUser(userModel);
        String number = savedUser.getMobile().substring(1);
        //sendMessageConfirmation(number);

       return savedUser;
    }

    @Override
    public List<UserModel> getAllUsers() {
        return userPersistencePort.getAllUsers();
    }

    @Override
    public boolean emailExists(String email) {
        return userPersistencePort.emailExists(email);
    }

    @Override
    public UserModel userExists(String email, String password) {
        UserModel user = userPersistencePort.userExists(email);
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new DomainException("Wrong password", HttpStatus.BAD_REQUEST);
        }
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        userPersistencePort.deleteUser(id);
    }

    public void sendMessageConfirmation(String number){
        if(number.equals("7777777") )return;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://graph.facebook.com/v16.0/108928785480520/messages"))
                    .header("Authorization", "Bearer EAADEmIApxS0BAI23iSUA8hKqi0NXv58UniJSErM8G0l65CkKbUDlmqoHOOHr8DJssQMb5loPEklk2nabrZApz1TO8ZBrW9zJsOllpNGocYAmyZC0T5xfBslajfn0JZCKzaDO0noZBAc6cXDBfubYqMDjOiV26EaxRvAkxZBZCEQ5kPLEQWfSdUAu9JZCA6evvXqYzRBnIDrPsQZDZD")
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString("{ \"messaging_product\": \"whatsapp\", \"recipient_type\": \"individual\", \"to\": \""+number+"\", \"type\": \"template\", \"template\": { \"name\": \"hello_world\", \"language\": { \"code\": \"en_US\" } } }"))
                    //.POST(HttpRequest.BodyPublishers.ofString("{ \"messaging_product\": \"whatsapp\", \"recipient_type\": \"individual\", \"to\": \"5493874692393\", \"type\": \"text\", \"text\": { \"preview_url\": false, \"body\": \"This is an example of a text message\" } }"))
                    .build();
            HttpClient http = HttpClient.newHttpClient();
            HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());

        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
