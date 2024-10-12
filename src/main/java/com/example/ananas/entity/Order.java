package com.example.ananas.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "order")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @ManyToOne
    @JoinColumn(name = "voucher_id")
    Voucher voucher;

    @Column(name = "total_amount", nullable = false)
    BigDecimal totalAmount; // Được sử dụng để lưu trữ số thực với độ chính xác cao

    @Column(name = "total_price")
    BigDecimal totalPrice;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    OrderStatus status;

    @Column(name = "payment_method", nullable = false)
    @Enumerated(EnumType.STRING)
    PaymentMethod paymentMethod;

    @Column(name = "payment_status", nullable = false)
    @Enumerated(EnumType.STRING)
    PaymentStatus paymentStatus;

    @Column(name = "recipient_name", nullable = false)
    String recipientName;

    @Column(name = "recipient_phone", nullable = false)
    String recipientPhone;

    @Column(name = "recipient_address", nullable = false)
    String recipientAddress;

    @Column(name = "created_at", nullable = false)
    Timestamp createdAt; // bao gồm cả phần giây, phút, giờ, ngày, tháng, và năm

    @Column(name = "updated_at")
    Timestamp updatedAt;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Order_Item> orderItems;
}

enum OrderStatus {
    PENDING, SHIPPED, DELIVERED, CANCELED
}

enum PaymentMethod {
    CREDIT_CARD, PAYPAL, CASH_ON_DELIVERY
}

enum PaymentStatus {
    PAID, UNPAID
}