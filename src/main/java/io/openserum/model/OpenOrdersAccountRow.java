package io.openserum.model;

import ch.openserum.serum.model.OpenOrdersAccount;
import lombok.Builder;
import lombok.Data;
import org.p2p.solanaj.core.PublicKey;

@Data
@Builder
public class OpenOrdersAccountRow {

    private PublicKey publicKey;
    private byte[] data;
    private OpenOrdersAccount openOrdersAccount;

    public OpenOrdersAccountRow(byte[] pubkey, byte[] data) {
        this.publicKey = new PublicKey(pubkey);
        this.data = data;
    }

}
