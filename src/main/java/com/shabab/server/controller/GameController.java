package com.shabab.server.controller;

import com.shabab.server.model.game.GameRequest;
import com.shabab.server.model.game.GameResponse;
import com.shabab.server.service.game.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/shabab")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("")
    public GameResponse shabab(@RequestBody GameRequest gameRequest) {
        log.info("Got request to SHABAB for player Id: {}  amount of {} ", gameRequest.getPlayerId() , gameRequest.getAmount() );
        return gameService.playShabab(gameRequest);
    }

}
