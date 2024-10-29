package com.example.ananas.mapper;

import com.example.ananas.dto.response.CartItemResponse;
import com.example.ananas.entity.Cart_Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICartItemMapper {
    @Mapping(target = "product.productName", source = "productVariant.product.productName")
    @Mapping(target = "product.size", source = "productVariant.size")
    @Mapping(target = "product.color", source = "productVariant.color")
    @Mapping(target = "product.stock", source = "productVariant.stock")
    CartItemResponse toCartItemResponse(Cart_Item cartItem);

    List<CartItemResponse> toCartItemResponseList(List<Cart_Item> cartItemList);
}
