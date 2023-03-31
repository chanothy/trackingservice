package edu.iu.c322.trackingservice.controller;

import edu.iu.c322.trackingservice.fakedata.Item;
import edu.iu.c322.trackingservice.fakedata.Order;
import edu.iu.c322.trackingservice.fakedata.TrackingRepository;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/trackings")
public class TrackingController {
    private TrackingRepository trackingRepository;

    public TrackingController(TrackingRepository trackingRepository) {
        this.trackingRepository = trackingRepository;
    }

    @GetMapping("/{orderId}/{itemId}")
    public Item findByOrderId(@PathVariable int orderId, @PathVariable int itemId) {
        Order order = trackingRepository.getOrderById(orderId);
        if (order != null) {
            Item item = trackingRepository.getItemById(itemId,order);
            if (item != null) {
                return item;
            }
            else {
                throw new IllegalStateException("item not valid");
            }
        }
        else {
            throw new IllegalStateException("order not valid");
        }
    }

    @PutMapping("/{orderId}")
    public void update(@RequestBody Item item, @PathVariable int orderId) {
        trackingRepository.update(item,orderId);
    }

}
