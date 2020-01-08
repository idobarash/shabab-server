package com.shabab.server.model.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BlueRibbonAuthDataResponse {

    @JsonProperty("userJWT")
    String userJwt;
}
