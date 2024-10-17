package com.example.ananas.controller;

import com.example.ananas.dto.request.OrderCreate;
import com.example.ananas.dto.request.OrderUpdateUser;
import com.example.ananas.dto.response.ApiResponse;
import com.example.ananas.dto.response.OrderResponse;
import com.example.ananas.entity.order.Order;
import com.example.ananas.entity.order.OrderStatus;
import com.example.ananas.entity.order.PaymentStatus;
import com.example.ananas.service.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Tạo đơn hàng
    @PostMapping("/create/{userId}")
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(@PathVariable("userId") Integer userId, @RequestBody OrderCreate order) {
        ApiResponse<OrderResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1);
        apiResponse.setMessage("Success");
        apiResponse.setResult(orderService.createOrder(userId,order));
        return ResponseEntity.ok(apiResponse);
    }
    // Xem các đơn hàng mới tạo (PENDING, SHIPPED)
    @GetMapping("/newOrder/{username}")
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getNewOrder(@PathVariable String username) {
        ApiResponse<List<OrderResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1);
        apiResponse.setMessage("Success");
        apiResponse.setResult(orderService.getNowOrder(username));
        return ResponseEntity.ok(apiResponse);
    }
    // Xem lịch sử mua hàng (đã thanh toan)
    @GetMapping("/historyOrder/{username}")
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getHistoryOrder(@PathVariable String username) {
        ApiResponse<List<OrderResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1);
        apiResponse.setMessage("Success");
        apiResponse.setResult(orderService.getHistoryOrder(username));
        return ResponseEntity.ok(apiResponse);
    }
    // Xem các đơn hàng đã hủy
    @GetMapping("/listCancelOrder/{username}")
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getListCancelOrder(@PathVariable String username) {
        ApiResponse<List<OrderResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1);
        apiResponse.setMessage("Success");
        apiResponse.setResult(orderService.getCancelOrder(username));
        return ResponseEntity.ok(apiResponse);
    }
    // Cập nhật đơn hàng
    @PutMapping("/updateOrder/{orderId}")
    public ResponseEntity<ApiResponse<OrderResponse>> updateOrder(@PathVariable String orderId, @RequestBody OrderUpdateUser orderUpdateUser)
    {
        ApiResponse<OrderResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1);
        apiResponse.setMessage("Success");
        apiResponse.setResult(orderService.updateOrder(orderId,orderUpdateUser));
        return ResponseEntity.ok(apiResponse);
    }
    // Xem tất cả các đơn hàng theo username
    @GetMapping("/{username}")
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getOrderByUsername(@PathVariable String username) {
        ApiResponse<List<OrderResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1111);
        apiResponse.setMessage("Success");
        apiResponse.setResult(orderService.getOrderByUsername(username));
        return ResponseEntity.ok(apiResponse);
    }

    // Hủy đơn hàng
    @GetMapping("/cancelOrder/{orderId}")
    public ResponseEntity<ApiResponse<String>> cancelOrder(@PathVariable String orderId) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1);
        apiResponse.setMessage("Success");
        if(orderService.cancelOrder(orderId)) apiResponse.setResult("This order canceled!");
        return ResponseEntity.ok(apiResponse);
    }
// Phương thức Admin riêng

    // Xem danh sách đơn hàng
    @GetMapping("/admin/list")
    public ResponseEntity<ApiResponse<List<Order>>> getOrdersForAdmin () {
        ApiResponse<List<Order>> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1111);
        apiResponse.setMessage("Success");
        apiResponse.setResult(orderService.getOrdersForAdmin());
        return ResponseEntity.ok(apiResponse);
    }
    //  Cập nhật trạng thái đơn hàng
    @PutMapping("/admin/statusOrderPending/{id}")
    public ResponseEntity<ApiResponse<Order>> getOrderStatusPending(@PathVariable String id) {
        ApiResponse<Order> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1112);
        apiResponse.setMessage("Success");
        apiResponse.setResult(orderService.changeOrderStatusPending(id));
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/admin/statusOrderShipped/{id}")
    public ResponseEntity<ApiResponse<Order>> getOrderStatusShipped(@PathVariable String id) {
        ApiResponse<Order> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1112);
        apiResponse.setMessage("Success");
        apiResponse.setResult(orderService.changeOrderStatusShipped(id));
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/admin/statusOrderDelivered/{id}")
    public ResponseEntity<ApiResponse<Order>> getOrderStatusDelivered(@PathVariable String id) {
        ApiResponse<Order> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1112);
        apiResponse.setMessage("Success");
        apiResponse.setResult(orderService.changeOrderStatusDelivered(id));
        return ResponseEntity.ok(apiResponse);
    }
    // Cập nhật trạng thái thanh toán
    @PutMapping("/admin/PaymentStatusPaid/{id}")
    public ResponseEntity<ApiResponse<Order>> changeOrderStatusPaid(@PathVariable String id) {
        ApiResponse<Order> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1113);
        apiResponse.setMessage("Success");
        apiResponse.setResult(orderService.changePaymentStatusPaid(id));
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/admin/PaymentStatusUnpaid/{id}")
    public ResponseEntity<ApiResponse<Order>> changeOrderStatusUnpaid(@PathVariable String id) {
        ApiResponse<Order> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1113);
        apiResponse.setMessage("Success");
        apiResponse.setResult(orderService.changePaymentStatusUnPaid(id));
        return ResponseEntity.ok(apiResponse);
    }
    // Xóa đơn hàng theo ID
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<ApiResponse<String>> deleteOrder(@PathVariable String id) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1114);
        apiResponse.setMessage("Success");
        apiResponse.setResult((orderService.deleteOrder(id)? "Xóa thành công" : "Xóa không thành công"));
        return ResponseEntity.ok(apiResponse);
    }
}

