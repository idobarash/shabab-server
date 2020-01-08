package com.shabab.server.service.game;

import org.springframework.stereotype.Service;

@Service
public class GameService {

    private static final Integer WIN_AMOUNT = 50;

    public Integer playShabab(String playerId) {

        Double value = Math.random() * ((10000) + 1);

        return value.intValue() % 2 == 0 ? WIN_AMOUNT : 0;
    }
}
