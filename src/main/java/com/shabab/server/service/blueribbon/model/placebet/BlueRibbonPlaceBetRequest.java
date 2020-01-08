package com.shabab.server.service.blueribbon.model.placebet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlueRibbonPlaceBetRequest {

    private String operatorId;

    private BlueRibbonGameDetails gameDetails;

    private BlueRibbonPlayerDetails playerDetails;

    private BlueRibbonWagerDetails blueRibbonWagerDetails;
}
