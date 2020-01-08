package com.shabab.server.controller;

import com.shabab.server.service.winner.JackpotWinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/shabab/jackpot/win")
public class JackpotWinController {

    @Autowired
    private JackpotWinService jackpotWinService;

    @PostMapping
    public Map<String, Object> receiveBlueRibbonWinner(@RequestBody Map<String, Object> brResponse) {

        jackpotWinService.applyWin(brResponse);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "OK");

        return response;
    }

}
