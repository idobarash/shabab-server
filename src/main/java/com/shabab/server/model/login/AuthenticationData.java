package com.shabab.server.model.login;

import lombok.Data;

@Data
public class AuthenticationData {

    private String authenticationKey;

    private String authenticationSecret;

    private String playerId;

}
