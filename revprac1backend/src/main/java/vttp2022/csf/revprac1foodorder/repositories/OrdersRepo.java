package vttp2022.csf.revprac1foodorder.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.csf.revprac1foodorder.models.Order;
import vttp2022.csf.revprac1foodorder.models.OrderItem;

import static vttp2022.csf.revprac1foodorder.repositories.Queries.*;

import java.util.LinkedList;
import java.util.List;

@Repository
public class OrdersRepo {

    @Autowired
    private JdbcTemplate template;

    public int saveCustomerOrder(Order order) {
        int count = template.update(
            STRING_SQL_INSERT_NEW_CUSTOMER_ORDER_DETAILS, order.getName(), order.getMobile(), order.getTime(), order.getOrderId());
        return count;
    }

    public int saveCustomerOrderItems(Order order) {
        int count = 0;
        for (int i = 0; i < order.getItemList().size(); i++) {
            int eachCount = template.update(
                STRING_SQL_INSERT_NEW_CUSTOMER_ORDER_ITEMS, order.getOrderId(), order.getItemList().get(i).getItem(), order.getItemList().get(i).getQuantity()
            );
            count = count + eachCount;
        }
        return count;
    }

    public Order retrieveCustomerOrder(String orderId) {
        SqlRowSet rs = template.queryForRowSet(
            STRING_SQL_SELECT_EXISTING_CUSTOMER_ORDER_DETAILS, orderId
        );

        Order order = new Order();

        while (rs.next()) {
            order.setName(rs.getString("name"));
            order.setMobile(rs.getString("mobile"));
            order.setTime(rs.getString("time"));
            order.setOrderId(orderId);
        }

        return order;
    }
    
    public List<OrderItem> retrieveCustomerOrderItems(String orderId) {
        List<OrderItem> itemsList = new LinkedList<>();

        SqlRowSet rs = template.queryForRowSet(
            STRING_SQL_SELECT_EXISTING_CUSTOMER_ORDER_ITEMS, orderId
        );

        while(rs.next()) {
            OrderItem item = new OrderItem();

            item.setItem(rs.getString("item"));
            item.setQuantity(rs.getString("quantity"));

            itemsList.add(item);
        }

        return itemsList;
    }
}
