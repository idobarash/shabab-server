package com.shabab.server.service.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shabab.server.model.login.AuthenticationData;
import com.shabab.server.model.login.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class LoginService {

    @Autowired
    private OkHttpClient okHttpClient;

    @Value("${access.key}")
    private String accessKey;

    @Value("${secret.key}")
    private String secretKey;

    @Value("${auth.url}")
    private String url;


    ObjectMapper mapper = new ObjectMapper();

    public String login() {
        LoginRequest loginRequest = buildLoginRequest();
        String responseBody = "";
        try {
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, mapper.writeValueAsString(loginRequest));
            Request request = new Request.Builder()
                    .url(url)
                    .method("PUT", body)
                    .addHeader("Content-Type", "application/json")
                    .build();

            Response response = okHttpClient.newCall(request).execute();
            responseBody = response.body().string();
        } catch (IOException e) {
            log.error("Error while trying to create login request. Message: {}", e.getMessage());
        }

        return responseBody;

    }

    private LoginRequest buildLoginRequest() {
        LoginRequest loginRequest = new LoginRequest();
        AuthenticationData authenticationData = new AuthenticationData();
        authenticationData.setAuthenticationKey(accessKey);
        authenticationData.setAuthenticationSecret(secretKey);
        loginRequest.setAuthenticationData(authenticationData);
        return loginRequest;
    }
}
