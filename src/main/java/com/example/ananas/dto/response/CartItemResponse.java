package com.example.ananas.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemResponse {
    String product;
    int quantity;
}
