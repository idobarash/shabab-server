package com.shabab.server.controller;

import com.shabab.server.service.game.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/shabab")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/{playerId}")
    public Integer shabab(@PathVariable("playerId") String playerId) {
        log.info("Got request to SHABAB for player Id: " + playerId);
        return gameService.playShabab(playerId);
    }

}
