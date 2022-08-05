package io.openserum.model;

import ch.openserum.serum.model.OpenOrdersAccount;
import org.p2p.solanaj.core.PublicKey;

public class OpenOrdersAccountRow {

    private PublicKey publicKey;
    private byte[] data;
    private OpenOrdersAccount openOrdersAccount;

    public OpenOrdersAccountRow(byte[] pubkey, byte[] data) {
        this.publicKey = new PublicKey(pubkey);
        this.data = data;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public OpenOrdersAccount getOpenOrdersAccount() {
        return openOrdersAccount;
    }

    public void setOpenOrdersAccount(OpenOrdersAccount openOrdersAccount) {
        this.openOrdersAccount = openOrdersAccount;
    }
}
