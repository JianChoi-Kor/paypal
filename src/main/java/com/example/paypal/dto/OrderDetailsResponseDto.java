package com.example.paypal.dto;

import com.example.paypal.enums.OrderIntent;
import com.example.paypal.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDetailsResponseDto {
    private String id;
    private OrderIntent intent;
    private OrderStatus status;
    @JsonProperty("purchase_units")
    private List<PurchaseUnit> purchaseUnits;
    private List<LinkDto> links;
}