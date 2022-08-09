package io.openserum.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.openserum.util.Base64Serializer;
import io.openserum.util.PublicKeyByteSerializer;
import lombok.Data;

@Data
public class AccountInfoRow {

    @JsonSerialize(using = PublicKeyByteSerializer.class)
    private byte[] publicKey;

    @JsonSerialize(using = Base64Serializer.class)
    private byte[] data;

}
