package vttp2022.csf.revprac1foodorder.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import vttp2022.csf.revprac1foodorder.models.Order;
import vttp2022.csf.revprac1foodorder.models.OrderItem;
import vttp2022.csf.revprac1foodorder.services.OrdersService;

@RestController
@RequestMapping(path="/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrdersRestController {

    @Autowired
    private OrdersService orderSvc;

    @PostMapping(path="/newOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveOrder(@RequestBody String json) {

        System.out.println(">>> Json: " + json);

        Optional<Order> optOrder = orderSvc.createNewOrder(json);

        if (optOrder.isEmpty()) {
            return ResponseEntity.badRequest().body("Order not created.");
        }

        Order order = optOrder.get();

        JsonObjectBuilder jsonObject = Json.createObjectBuilder();
        jsonObject.add("name", order.getName());
        jsonObject.add("mobile", order.getMobile());
        jsonObject.add("time", order.getTime());
        jsonObject.add("orderId", order.getOrderId());

        return ResponseEntity.status(HttpStatus.CREATED).body(jsonObject.build().toString());
    }

    @PostMapping(path="/retrieve")
    public ResponseEntity<String> retrieveOrderPost(@RequestBody String id) throws IOException {
        System.out.println(">>> Id: " + id);
        Optional<Order> optOrder = orderSvc.retrieveCustomerOrder(id);

        if (optOrder.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Order order = optOrder.get();

        JsonObjectBuilder jsonObject = Json.createObjectBuilder();
        jsonObject.add("name", order.getName());
        jsonObject.add("mobile", order.getMobile());
        jsonObject.add("time", order.getTime());
        jsonObject.add("orderId", order.getOrderId());
        
        JsonArrayBuilder array = Json.createArrayBuilder();
        for (OrderItem item : order.getItemList()) {
            JsonObjectBuilder arrObj = Json.createObjectBuilder();
            arrObj.add("item", item.getItem());
            arrObj.add("quantity", item.getQuantity());
            array.add(arrObj);
        }

        jsonObject.add("orderItems", array);

        return ResponseEntity.status(HttpStatus.CREATED).body(jsonObject.build().toString());
    }

    @GetMapping(path="/retrieve/{id}")
    public ResponseEntity<String> retrieveOrderGet(@PathVariable String id) throws IOException {
        Optional<Order> optOrder = orderSvc.retrieveCustomerOrder(id);

        if (optOrder.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Order order = optOrder.get();

        JsonObjectBuilder jsonObject = Json.createObjectBuilder();
        jsonObject.add("name", order.getName());
        jsonObject.add("mobile", order.getMobile());
        jsonObject.add("time", order.getTime());
        jsonObject.add("orderId", order.getOrderId());
        
        JsonArrayBuilder array = Json.createArrayBuilder();
        for (OrderItem item : order.getItemList()) {
            JsonObjectBuilder arrObj = Json.createObjectBuilder();
            arrObj.add("item", item.getItem());
            arrObj.add("quantity", item.getQuantity());
            array.add(arrObj);
        }

        jsonObject.add("orderItems", array);

        return ResponseEntity.status(HttpStatus.CREATED).body(jsonObject.build().toString());
    }
    
}
