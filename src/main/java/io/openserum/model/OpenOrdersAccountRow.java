package io.openserum.model;

import ch.openserum.serum.model.OpenOrdersAccount;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.openserum.util.PublicKeySerializer;
import lombok.Builder;
import org.p2p.solanaj.core.PublicKey;

import java.util.List;

@Builder
public class OpenOrdersAccountRow {

    private PublicKey publicKey;
    @JsonSerialize(using = PublicKeySerializer.class)
    private PublicKey market;
    @JsonSerialize
    private List<OpenOrdersAccount.Order> orders;
}
