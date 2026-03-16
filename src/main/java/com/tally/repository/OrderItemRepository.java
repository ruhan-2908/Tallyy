package com.tally.repository;

import com.tally.model.OrderItem;
import com.tally.payload.dto.OrderItemDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

}
