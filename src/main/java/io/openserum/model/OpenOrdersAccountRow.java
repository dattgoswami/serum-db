package io.openserum.model;

import ch.openserum.serum.model.OpenOrdersAccount;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.openserum.util.PublicKeySerializer;
import lombok.Builder;
import org.p2p.solanaj.core.PublicKey;

import java.util.List;

@Builder
public class OpenOrdersAccountRow {

    @JsonSerialize(using = PublicKeySerializer.class)
    private PublicKey publicKey;
    private List<OpenOrdersAccount.Order> orders;

    public OpenOrdersAccountRow(PublicKey pubkey, List<OpenOrdersAccount.Order> orders) {
        this.publicKey = pubkey;
        this.orders = orders;
    }
}
