package com.example.ananas.service.IService;

import com.example.ananas.dto.request.OrderCreate;
import com.example.ananas.dto.request.OrderUpdateUser;
import com.example.ananas.dto.response.OrderResponse;
import com.example.ananas.entity.order.Order;

import java.util.List;

public interface IOrderService {

    List<OrderResponse> getOrdersForAdmin();

    List<OrderResponse> getOrderByUsername(String username);

    OrderResponse createOrder(Integer userId, OrderCreate orderCreate);

    OrderResponse updateOrder(Integer orderId, OrderUpdateUser orderUpdateUser);

    boolean deleteOrder(Integer orderId);

    OrderResponse changeOrderStatusShipped(Integer orderId);

    OrderResponse changeOrderStatusDelivered(Integer orderId);

    OrderResponse changeOrderStatusPending(Integer orderId);

    OrderResponse changePaymentStatusPaid(Integer orderId);

    OrderResponse changePaymentStatusUnPaid(Integer orderId);

    boolean cancelOrder(Integer orderId);

    List<OrderResponse> getNowOrder(String username);

    List<OrderResponse> getCancelOrder(String username);

    List<OrderResponse> getHistoryOrder(String username);


}
