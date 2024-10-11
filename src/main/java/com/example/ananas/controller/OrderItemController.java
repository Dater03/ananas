package com.example.ananas.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderItemController {

// Phương thức chung

    //  Lấy danh sách sản phẩm trong một đơn hàng - List<OrderItemResponse> getAllOrderItems(Long orderId)


// Phương thức cho Admin

    //  Admin thêm sản phẩm vào đơn hàng (nếu có yêu cầu) - OrderItemResponse addOrderItem(Long orderId, OrderItemRequest orderItemRequest)

    //  Cập nhật thông tin sản phẩm trong đơn hàng - void updateOrderItem(Long orderItemId, OrderItemUpdateRequest orderItemUpdateRequest)

    //  Xóa sản phẩm khỏi đơn hàng - void deleteOrderItem(Long orderItemId)

// Phương thức riêng cho User

    // Xem sản phẩm trong đơn hàng của người dùng - OrderItemResponse getOrderItemsByOrderId(Long orderId)
}
