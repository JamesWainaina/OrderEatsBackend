package com.James.order.eats.requests;

import com.James.order.eats.model.Address;
import lombok.Data;

@Data
public class OrderRequest {

    private Long restaurantId;
    private Address deliveryAddress;
}
