package io.openserum.controller;

import com.google.common.io.BaseEncoding;
import io.openserum.responses.MarketResponse;
import lombok.extern.slf4j.Slf4j;
import org.p2p.solanaj.core.PublicKey;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@Slf4j
public class ApiController {

    private final JdbcTemplate jdbcTemplate;

    public ApiController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping(value = "/api/market/{marketId}")
    public MarketResponse getMarket(@PathVariable String marketId) {
        // query postgres for binary data
        // deserialize into Market object

        PublicKey placeholderResponse = PublicKey.valueOf("9wFFyRfZBsuAha4YcuxcXLKwMxJR43S7fPfQLusDBzvT");
        String marketIdHex = BaseEncoding.base16().lowerCase().encode(placeholderResponse.toByteArray());

        String sql = "SELECT data FROM account WHERE pubkey=decode(?, 'hex')";
        byte[] accountData = (byte[]) jdbcTemplate.queryForMap(
                sql,
                new Object[]{marketIdHex})
                .get("data");
        log.info("Data: " + Arrays.toString(accountData));

        return new MarketResponse(
                placeholderResponse,
                placeholderResponse,
                placeholderResponse
        );
    }
}
