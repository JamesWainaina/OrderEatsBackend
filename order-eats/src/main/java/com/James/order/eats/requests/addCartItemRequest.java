package com.James.order.eats.requests;

import lombok.Data;

import java.util.List;


@Data
public class addCartItemRequest {

    private Long foodId;

    private Long quantity;

    private List<String> ingredients;

}
