package com.shabab.server.service.winner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class JackpotWinService {

    @Autowired
    private ObjectMapper objectMapper;

    public void applyWin(Map<String, Object> brResponse) {

        try {
            log.info("Got win from Blue Ribbon YAYYYYYYY, {}", objectMapper.writeValueAsString(brResponse));
        } catch (JsonProcessingException e) {
            log.error("Error applying win, {}", e.getMessage(), e);
        }
    }
}
