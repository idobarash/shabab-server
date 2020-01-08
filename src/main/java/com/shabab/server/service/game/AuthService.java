package com.shabab.server.service.game;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shabab.server.model.auth.BlueRibbonAuthResponse;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService {

    @Getter
    @Setter
    private String jwt;

    @Autowired
    private LoginService loginService;

    @Autowired
    private ObjectMapper mapper;


    public void login(){
        String loginResponse = loginService.login();
        try {
            BlueRibbonAuthResponse blueRibbonAuthResponse = mapper.readValue(loginResponse,BlueRibbonAuthResponse.class);
            if(blueRibbonAuthResponse != null){
                setJwt(blueRibbonAuthResponse.getData().getUserJwt());
            }
        } catch (JsonProcessingException e) {
            log.error("Error while trying to login. Message: {}",e.getMessage());
        }

    }




}
