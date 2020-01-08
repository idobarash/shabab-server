package com.shabab.server.service.game;

import com.shabab.server.model.game.GameRequest;
import com.shabab.server.model.game.GameResponse;
import com.shabab.server.service.blueribbon.BlueRibbonPlaceBetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private static final Integer WIN_AMOUNT = 50;

    @Autowired
    private BlueRibbonPlaceBetService blueRibbonPlaceBetService;

    public GameResponse playShabab(GameRequest gameRequest) {

        Double value = Math.random() * ((10000) + 1);

        int amount = value.intValue() % 2 == 0 ? WIN_AMOUNT : 0;

        blueRibbonPlaceBetService.dispatchPlaceBetToBlueRibbon(gameRequest.getPlayerId(), gameRequest.getAmount());

        return GameResponse.builder()
                .playerId(gameRequest.getPlayerId())
                .winAmount(Double.valueOf(amount))
                .build();
    }
}
