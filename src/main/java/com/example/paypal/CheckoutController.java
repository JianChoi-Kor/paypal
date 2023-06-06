package com.example.paypal;

import com.example.paypal.dto.*;
import com.example.paypal.entity.Order;
import com.example.paypal.enums.OrderStatus;
import com.example.paypal.enums.PaymentLandingPage;
import com.example.paypal.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequestMapping("/checkout")
@RequiredArgsConstructor
@Controller
public class CheckoutController {

    private final PaypalHttpClient paypalHttpClient;
    private final OrderRepository orderRepository;

    @PostMapping
    public ResponseEntity<CreateOrderResponseDto> checkout(@RequestBody OrderDto orderDto) throws Exception {
        PaypalAppContextDto appContext = new PaypalAppContextDto();
        appContext.setReturnUrl("http://localhost:8083/checkout/success");
        appContext.setBrandName("jan92");
        appContext.setLandingPage(PaymentLandingPage.BILLING);
        orderDto.setApplicationContext(appContext);
        CreateOrderResponseDto orderResponse = paypalHttpClient.createOrder(orderDto);

        Order order = new Order();
        order.setPaypalOrderId(orderResponse.getId());
        order.setPaypalOrderStatus(orderResponse.getStatus().toString());
        orderRepository.save(order);

        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/success")
    public ResponseEntity<?> paymentSuccess(HttpServletRequest request) {
        String orderId = request.getParameter("token");
        Order targetOrder = orderRepository.findByPaypalOrderId(orderId);
        targetOrder.setPaypalOrderStatus(OrderStatus.APPROVED.toString());
        orderRepository.save(targetOrder);

        return ResponseEntity.ok().body("Payment success");
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderDetails(@PathVariable String orderId) throws Exception {
        OrderDetailsResponseDto orderDetailsResponse = paypalHttpClient.getOrderDetails(orderId);

        return ResponseEntity.ok(orderDetailsResponse);
    }
}
