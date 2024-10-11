package com.example.ananas.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "voucher")
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer voucherId;

    @Column(name = "code", nullable = false, unique = true)
    String code;

    @Column(name = "discount_type", nullable = false)
    @Enumerated(EnumType.STRING)
    DiscountType discountType;

    @Column(name = "discount_value", nullable = false)
    BigDecimal discountValue;

    @Column(name = "start_date")
    Date startDate;

    @Column(name = "end_date")
    Date endDate;

    @Column(name = "usage_limit", nullable = false)
    Integer usageLimit;

    @Column(name = "created_at", nullable = false)
    Timestamp createdAt;
}

enum DiscountType {
    PERCENTAGE, FIXED_AMOUNT
}
