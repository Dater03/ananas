package com.example.ananas.entity.order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum OrderStatus {
    PENDING("PENDING"), //  trạng thái chờ xử lý
    SHIPPED("SHIPPED"), //  trạng thái đã gửi đi
    DELIVERED("DELIVERED"), // giao thành công đến người nhận
    CANCELED("CANCELED");
    // Hủy bỏ
    private String status;
}
