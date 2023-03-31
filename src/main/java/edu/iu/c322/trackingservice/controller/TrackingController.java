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
        return trackingRepository.findByOrderId(orderId,itemId);
    }

    @PutMapping("/{orderId}")
    public void update(@RequestBody Item item, @PathVariable int orderId) {
        trackingRepository.update(item,orderId);
    }

}
