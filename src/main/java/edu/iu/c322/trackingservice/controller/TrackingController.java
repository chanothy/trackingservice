package edu.iu.c322.trackingservice.controller;

import edu.iu.c322.trackingservice.models.Invoice;
import edu.iu.c322.trackingservice.models.InvoiceItem;
import edu.iu.c322.trackingservice.models.Item;
import edu.iu.c322.trackingservice.models.Order;
import edu.iu.c322.trackingservice.repository.TrackRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/trackings")
public class TrackingController {
    private TrackRepo trackingRepository;

    public TrackingController(TrackRepo trackingRepository) {
        this.trackingRepository = trackingRepository;
    }

    @GetMapping
    public List<Order> findAll() {
        return trackingRepository.findAll();
    }

    @GetMapping("/{orderId}/{itemId}")
    public Item findByOrderId(@PathVariable int orderId, @PathVariable int itemId) {
        Optional<Order> temp = trackingRepository.findById(orderId);
//        Order order = trackingRepository.getById(orderId);

        if (temp.isEmpty()) {
            throw new IllegalStateException("order id is not valid");
        }
        Order order = temp.get();
        InvoiceItem invoiceItem = new InvoiceItem(order.getItems(), order.getShippingAddress());
        Invoice invoice = new Invoice(order.getOrderId(),order.getCustomerId(),order.getDate(),order.getTotal(),invoiceItem,order.getPayment());

        for (int i = 0; i < order.getItems().size(); i++) {
            if (order.getItems().get(i).getItemId() == itemId) {
                order.getItems().get(i).setStatus("shipped");
                order.getItems().get(i).setStatusUpdatedOn(java.time.LocalDate.now().toString());
                return order.getItems().get(i);
            }
        }
        throw new IllegalStateException("item id is not valid");
    }

    @PutMapping("/{orderId}")
    public void update(@RequestBody Item item, @PathVariable int orderId) {
        Optional<Order> temp = trackingRepository.findById(orderId);
//        Order order = trackingRepository.getById(orderId);

        if (temp.isEmpty()) {
            throw new IllegalStateException("order id is not valid");
        }
        Order order = temp.get();
        int validItemId = 0;
        for (int i = 0; i < order.getItems().size(); i++) {
            if (order.getItems().get(i).getItemId() == item.getItemId()) {
                order.getItems().get(i).setStatus(item.getStatus());
                validItemId = 1;
            }
        }
        if (validItemId == 0) {
            throw new IllegalStateException("item id is not valid");
        }

        trackingRepository.save(order);
    }

}
