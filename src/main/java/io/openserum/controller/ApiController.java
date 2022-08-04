package io.openserum.controller;

import io.openserum.responses.MarketResponse;
import org.p2p.solanaj.core.PublicKey;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping(value = "/api/serum/market/{marketId}")
    public MarketResponse getMarket(@PathVariable String marketId) {
        // query postgres for binary data
        // deserialize into Market object

        PublicKey placeholderResponse = PublicKey.valueOf("9wFFyRfZBsuAha4YcuxcXLKwMxJR43S7fPfQLusDBzvT");

        return new MarketResponse(
                placeholderResponse,
                placeholderResponse,
                placeholderResponse
        );
    }
}
