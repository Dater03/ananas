package com.example.ananas.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    int orderId;

    @Column(name = "user_id")
    int userId;

    @Column(name = "total_amount")
    double totalAmount;

    @Column(name = "voucder_id")
    int voucherId;

    @Column(name = "total_price")
    double totalPrice;

    @ElementCollection
    Set<String> status;

    @ElementCollection
    Set<String> payment_method;

    @ElementCollection
    Set<String> payment_status;

    @Column(name = "recipient_name")
    String recipientName;

    @Column(name = "recipient_phone")
    String recipientPhone;

    @Column(name = "recipient_address")
    String recipientAddress;

    @Column(name = "create_at")
    Date createAt;

    @Column(name = "update_at")
    Date updateAt;

    @ManyToOne
    @JsonBackReference("user-order")
    @JoinColumn(name = "user_id",insertable = false,updatable = false, nullable = false)
    User user;
}
