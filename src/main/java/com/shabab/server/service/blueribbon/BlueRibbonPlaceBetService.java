package com.shabab.server.service.blueribbon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shabab.server.service.blueribbon.model.placebet.BlueRibbonGameDetails;
import com.shabab.server.service.blueribbon.model.placebet.BlueRibbonPlaceBetRequest;
import com.shabab.server.service.blueribbon.model.placebet.BlueRibbonPlayerDetails;
import com.shabab.server.service.blueribbon.model.placebet.BlueRibbonWagerDetails;
import com.shabab.server.service.game.AuthService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class BlueRibbonPlaceBetService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OkHttpClient okHttpClient;

    @Autowired
    private AuthService authService;

    @Value("${blueribbon.game.id}")
    private String blueribbonGameId;

    @Value("${blueribbon.operator.id}")
    private String blueribbonOperatorId;

    @Value("${blueribbon.placebet.url}")
    private String url;

    public void dispatchPlaceBetToBlueRibbon(String playerId, Double amount) {

        BlueRibbonPlaceBetRequest placeBetRequest =  generateRequest(playerId, amount);

        String data = "";

        int attempts = 3;
        boolean finised = false;

        try {

            data = objectMapper.writeValueAsString(placeBetRequest);
            while (!finised && attempts > 0) {
                Response response = executeHttpPlaceBet(data);
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    log.info("Successfully applied place bet of {}. got {}", data, responseBody);
                    finised = true;
                }
                else if (response.code() == 401) {
                    log.info("Got expired JWT - login again");
                    authService.login();
                    attempts--;
                }
                else {
                    throw new RuntimeException("Cannot place bet " + data + ", code: " +  response.code() + ", error:" + response.body());
                }
            }
        } catch (IOException e) {
            log.error("Error while trying to place bet request. sent {}, error Message: {}", data, e.getMessage(), e);
        }

    }

    private Response executeHttpPlaceBet(String data) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, data);
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authentication", "Bearer " + authService.getJwt())
                .build();

        return okHttpClient.newCall(request).execute();
    }

    private BlueRibbonPlaceBetRequest generateRequest(String playerId, Double amount) {
        return BlueRibbonPlaceBetRequest.builder()
                .gameDetails(new BlueRibbonGameDetails(blueribbonGameId))
                .blueRibbonWagerDetails(new BlueRibbonWagerDetails(amount, "EUR"))
                .playerDetails(new BlueRibbonPlayerDetails(playerId))
                .operatorId(blueribbonOperatorId)
                .build();
    }

}
