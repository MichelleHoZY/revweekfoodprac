package vttp2022.csf.revprac1foodorder.models;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Order {

    private String name;
    private String orderId;
    private String mobile;
    private String time;
    private List<OrderItem> itemList = new LinkedList<>();
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public List<OrderItem> getItemList() {
        return itemList;
    }
    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }
    public void addOrderItem(OrderItem orderItem) {
        this.itemList.add(orderItem);
    }

    @Override
    public String toString() {
        return "Order [itemList=" + itemList + ", mobile=" + mobile + ", name=" + name + ", orderId=" + orderId
                + ", time=" + time + "]";
    }

    public static Order createOrder(String json) {
        InputStream is = new ByteArrayInputStream(json.getBytes());
        JsonReader reader = Json.createReader(is);
        JsonObject object = reader.readObject();

        Order order = new Order();
        order.setName(object.getString("name"));
        order.setMobile(object.getString("mobile"));
        order.setTime(object.getString("time"));
        order.setOrderId(object.getString("orderId"));

        List<OrderItem> itemList = new LinkedList<>();

        JsonArray array = object.getJsonArray("orderItems");
        for (int i = 0; i < array.size(); i++) {
            OrderItem item = new OrderItem();
            JsonObject itemObj = array.getJsonObject(i);

            item.setItem(itemObj.getString("item"));
            item.setQuantity(itemObj.getString("quantity"));

            itemList.add(item);
        }

        order.setItemList(itemList);

        System.out.println(">>> Order: " + order);

        return order;

    }
    
    
}
