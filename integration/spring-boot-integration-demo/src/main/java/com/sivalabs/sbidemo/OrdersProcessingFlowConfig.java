package com.sivalabs.sbidemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.jdbc.JdbcPollingChannelAdapter;
import org.springframework.integration.json.ObjectToJsonTransformer;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
public class OrdersProcessingFlowConfig {

    @Autowired
    DataSource dataSource;

    @Bean
    public MessageSource<Object> jdbcMessageSource() {
        JdbcPollingChannelAdapter channelAdapter = new JdbcPollingChannelAdapter(this.dataSource, "SELECT * FROM orders where status='CREATED'");
        channelAdapter.setUpdateSql("update orders set status='DELIVERED' where id=:id");
        channelAdapter.setUpdatePerRow(true);
        channelAdapter.setRowMapper(new OrderMapper());
        return channelAdapter;
    }

    @Bean
    public IntegrationFlow pollingFlow() {
        return IntegrationFlows.from(jdbcMessageSource(),
                c -> c.poller(Pollers.fixedRate(10000).maxMessagesPerPoll(1)))
                .transform(new ObjectToJsonTransformer())
                .handle(new LoggingHandler("INFO"))
                //.channel("furtherProcessChannel")
                .get();
    }
}

class OrderMapper implements RowMapper<Order>
{
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong("id"));
        order.setDetails(rs.getString("details"));
        order.setCreatedDate(rs.getDate("created_date"));
        order.setStatus(Order.Status.valueOf(rs.getString("status")));
        return order;
    }
}