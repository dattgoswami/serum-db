package io.openserum.responses;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.openserum.util.PublicKeySerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.p2p.solanaj.core.PublicKey;

@Data
@AllArgsConstructor
public class MarketResponse {

    @JsonSerialize(using = PublicKeySerializer.class)
    private PublicKey marketId;

    @JsonSerialize(using = PublicKeySerializer.class)
    private PublicKey bids;

    @JsonSerialize(using = PublicKeySerializer.class)
    private PublicKey asks;

}
