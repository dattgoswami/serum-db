package io.openserum.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderRow {

    private long price;
    private boolean bid;

}
