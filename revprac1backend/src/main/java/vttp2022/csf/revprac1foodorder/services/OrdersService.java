package vttp2022.csf.revprac1foodorder.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp2022.csf.revprac1foodorder.models.Order;
import vttp2022.csf.revprac1foodorder.repositories.OrdersRepo;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepo orderRepo;

    public Optional<Order> createNewOrder(String json) {
        Order order = Order.createOrder(json);

        if (orderRepo.saveCustomerOrder(order) == 1 && orderRepo.saveCustomerOrderItems(order) != 0) {
            return Optional.of(order);
        }

        return Optional.empty();
    }

    public Optional<Order> retrieveCustomerOrder(String orderId) throws IOException {
        Order order = orderRepo.retrieveCustomerOrder(orderId);
        order.setItemList(orderRepo.retrieveCustomerOrderItems(orderId));

        if (order.getName() == null) {
            return Optional.empty();
        }

        return Optional.of(order);
    }
    
}
