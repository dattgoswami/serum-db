package io.openserum.controller;

import ch.openserum.serum.model.OpenOrdersAccount;
import com.google.common.io.BaseEncoding;
import io.openserum.model.OpenOrdersAccountRow;
import io.openserum.model.OrderRow;
import lombok.extern.slf4j.Slf4j;
import org.p2p.solanaj.core.PublicKey;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class ApiController {

    private final JdbcTemplate jdbcTemplate;

    public ApiController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping(value = "/serum/account/{accountId}")
    public String getAccount(@PathVariable String accountId) {
        PublicKey accountPubkey = new PublicKey(accountId);

        String sql = "SELECT data FROM account WHERE pubkey=decode(?, 'hex')";
        Map<String, Object> rowData = jdbcTemplate.queryForMap(
                sql,
                BaseEncoding.base16().lowerCase().encode(
                        accountPubkey.toByteArray()
                )
        );

        byte[] accountData = (byte[]) rowData.get("data");
        String response = Base64.getEncoder().encodeToString(accountData);

        return response;
    }

    @GetMapping(value = "/serum/slot/{accountId}")
    public Long getSlot(@PathVariable String accountId) {
        PublicKey accountPubkey = new PublicKey(accountId);

        String sql = "SELECT slot FROM account WHERE pubkey=decode(?, 'hex')";
        Long slot = jdbcTemplate.queryForObject(
                sql,
                Long.class,
                BaseEncoding.base16().lowerCase().encode(
                        accountPubkey.toByteArray()
                )
        );

        return slot;
    }

    @GetMapping(value = "/serum/orders/{owner}")
    public List<OpenOrdersAccountRow> getOpenOrderAccounts(@PathVariable String owner) {
        PublicKey ownerPubkey = new PublicKey(owner);
        String sql = "SELECT data " +
                "FROM account " +
                "WHERE length(data)=3228 and position" +
                "('\\x" + BaseEncoding.base16().lowerCase().encode(ownerPubkey.toByteArray()) + "'::bytea in data)>0";

        List<OpenOrdersAccountRow> openOrdersAccountRows = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {
                    OpenOrdersAccount openOrdersAccount = OpenOrdersAccount.readOpenOrdersAccount(
                            rs.getBytes(1)
                    );

                    List<OrderRow> orderRows = convertOrdersToOrderRows(openOrdersAccount.getOrders());

                    OpenOrdersAccountRow row = OpenOrdersAccountRow.builder()
                            .market(openOrdersAccount.getMarket())
                            .orders(orderRows)
                            .build();

                    return row;
                }
        );

        return openOrdersAccountRows.stream()
                .filter(openOrdersAccountRow -> openOrdersAccountRow.getOrders().size() > 0)
                .toList();
    }

    private List<OrderRow> convertOrdersToOrderRows(List<OpenOrdersAccount.Order> orders) {
        if (orders.size() == 0) {
            return Collections.emptyList();
        }

        return orders.stream()
                .map(order -> OrderRow.builder().price(order.getPrice()).bid(order.isBid()).build())
                .toList();
    }
}
