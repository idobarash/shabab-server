package com.shabab.server.model.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BlueRibbonAuthResponse {

    @JsonProperty("data")
    BlueRibbonAuthDataResponse data;


}
