package com.example.paypal.dto;

import com.example.paypal.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateOrderResponseDto {
    private String id;
    private OrderStatus status;
    private List<LinkDto> links;
}