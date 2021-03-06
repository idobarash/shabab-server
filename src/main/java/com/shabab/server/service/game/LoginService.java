package com.shabab.server.service.game;

import com.fasterxml.jackson.core.JsonProcessingException;
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

    @Value("${blueribbon.access.key}")
    private String accessKey;

    @Value("${blueribbon.secret.key}")
    private String secretKey;

    @Value("${blueribbon.login.url}")
    private String loginUrl;


    private ObjectMapper mapper = new ObjectMapper();


    public String loginPlayer(String playerId) {
        LoginRequest loginRequest = buildLoginRequest(playerId);
        String responseBody = "";
        try {
        Request request = buildRequestBody(loginRequest,loginUrl + "/player");

            Response response = okHttpClient.newCall(request).execute();
            responseBody = response.body().string();
        } catch (IOException e) {
            log.error("Error while trying to create login player request. Message: {}", e.getMessage());
        }

        return responseBody;

    }

    public String login() {
        LoginRequest loginRequest = buildLoginRequest(null);
        String responseBody = "";
        try {
            Request request = buildRequestBody(loginRequest,loginUrl);

            Response response = okHttpClient.newCall(request).execute();
            responseBody = response.body().string();
        } catch (IOException e) {
            log.error("Error while trying to create login request. Message: {}", e.getMessage());
        }

        return responseBody;

    }

    private Request buildRequestBody(Object objectRequest,String requestUrl) throws JsonProcessingException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, mapper.writeValueAsString(objectRequest));
        return new Request.Builder()
                .url(requestUrl)
                .method("PUT", body)
                .addHeader("Content-Type", "application/json")
                .build();

    }

    private LoginRequest buildLoginRequest(String playerId) {
        LoginRequest loginRequest = new LoginRequest();
        AuthenticationData authenticationData = new AuthenticationData();
        authenticationData.setAuthenticationKey(accessKey);
        authenticationData.setAuthenticationSecret(secretKey);
        authenticationData.setPlayerId(playerId != null ? playerId : null);
        loginRequest.setAuthenticationData(authenticationData);
        return loginRequest;
    }
}
