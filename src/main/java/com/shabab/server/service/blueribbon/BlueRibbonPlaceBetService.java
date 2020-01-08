package com.shabab.server.service.blueribbon;

import com.shabab.server.service.blueribbon.model.placebet.BlueRibbonGameDetails;
import com.shabab.server.service.blueribbon.model.placebet.BlueRibbonPlaceBetRequest;
import com.shabab.server.service.blueribbon.model.placebet.BlueRibbonPlayerDetails;
import com.shabab.server.service.blueribbon.model.placebet.BlueRibbonWagerDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BlueRibbonPlaceBetService {

    @Value("${blueribbon.game.id}")
    private String blueribbonGameId;

    @Value("${blueribbon.operator.id}")
    private String blueribbonOperatorId;

    public void dispatchPlaceBetToBlueRibbon(String playerId) {

        BlueRibbonPlaceBetRequest request =  generateRequest(playerId);

    }

    private BlueRibbonPlaceBetRequest generateRequest(String playerId) {
        return BlueRibbonPlaceBetRequest.builder()
                .gameDetails(new BlueRibbonGameDetails(blueribbonGameId))
                .blueRibbonWagerDetails(new BlueRibbonWagerDetails(50d, "EUR"))
                .playerDetails(new BlueRibbonPlayerDetails(playerId))
                .operatorId(blueribbonOperatorId)
                .build();
    }

}
