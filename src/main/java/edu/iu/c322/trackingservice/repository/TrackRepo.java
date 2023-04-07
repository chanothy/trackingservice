package edu.iu.c322.trackingservice.repository;

import edu.iu.c322.trackingservice.models.Invoice;
import edu.iu.c322.trackingservice.models.Order;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface TrackRepo extends JpaRepository<Order,Integer>{

}
