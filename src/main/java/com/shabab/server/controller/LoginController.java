package com.shabab.server.controller;

import com.shabab.server.service.game.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping(value="", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login() {
        String response = loginService.login();
        return new ResponseEntity(response,HttpStatus.OK);
    }

    @GetMapping(value="/player", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginPlayer(@RequestParam String playerId) {
        String response = loginService.login(playerId);
        return new ResponseEntity(response,HttpStatus.OK);
    }
}
