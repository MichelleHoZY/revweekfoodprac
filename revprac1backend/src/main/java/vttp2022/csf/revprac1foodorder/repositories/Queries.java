package vttp2022.csf.revprac1foodorder.repositories;

public class Queries {

    public static final String STRING_SQL_INSERT_NEW_CUSTOMER_ORDER_DETAILS = "insert into customer_info (name, mobile, time, order_id) values (?, ?, ?, ?)";

    public static final String STRING_SQL_INSERT_NEW_CUSTOMER_ORDER_ITEMS = "insert into orders (order_id, item, quantity) values (?, ?, ?)";

    public static final String STRING_SQL_SELECT_EXISTING_CUSTOMER_ORDER_DETAILS = "select * from customer_info where order_id = ?";

    public static final String STRING_SQL_SELECT_EXISTING_CUSTOMER_ORDER_ITEMS = "select * from orders where order_id = ?";
    
}
