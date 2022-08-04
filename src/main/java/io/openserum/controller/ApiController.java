package io.openserum.controller;

import com.google.common.io.BaseEncoding;
import lombok.extern.slf4j.Slf4j;
import org.p2p.solanaj.core.PublicKey;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.Map;

@RestController
@Slf4j
public class ApiController {

    private final JdbcTemplate jdbcTemplate;

    public ApiController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping(value = "/market/{marketId}")
    public String getMarket(@PathVariable String marketId) {
        PublicKey marketPubkey = new PublicKey(marketId);

        String sql = "SELECT data FROM account WHERE pubkey=decode(?, 'hex')";
        Map<String, Object> rowData = jdbcTemplate.queryForMap(
                sql,
                BaseEncoding.base16().lowerCase().encode(
                        marketPubkey.toByteArray()
                )
        );

        byte[] accountData = (byte[]) rowData.get("data");
        String response = Base64.getEncoder().encodeToString(accountData);

        log.info(response);

        return response;
    }
}
