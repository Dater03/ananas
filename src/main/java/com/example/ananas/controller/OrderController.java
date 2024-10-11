package com.example.ananas.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
// Phương thức chung cho cả Admin và User - DTO chung

    // Danh sách đơn hàng - List<OrderResponse> getAllOrders()

    //  Xem chi tiết đơn hàng - OrderResponse getOrderDetail(Long orderId)

    // Phương thức xem lịch sử mua hàng - List<OrderResponse> getOrderHistory(Long userId)

// Phương thức Admin riêng

    // Trả về chi tiết đơn hàng theo ID - getOrderById(Long id)

    //  Cập nhật thông tin đơn hàng - void updateOrder(Long orderId, OrderUpdateRequest orderUpdateRequest)

    // Xóa đơn hàng theo ID - void deleteOrder(Long orderId)

    // Cập nhật trạng thái đơn hàng - void updateOrderStatus(Long orderId, OrderStatusUpdateRequest statusUpdateRequest)

// Phương thức User riêng

    // Tạo đơn hàng từ giỏ hàng khi người dùng thanh toán - OrderResponse createOrderFromCart(Long userId)

    // Hủy đơn hàng nếu trạng thái cho phép - void cancelOrder(Long orderId)
}

