package com.example.ananas.service.IService;

import com.example.ananas.dto.request.ProductCreateRequest;
import com.example.ananas.dto.response.ProductResponse;

import java.util.List;

public interface IProductService {
    ProductCreateRequest createProduct(ProductCreateRequest productCreateRequest);

    ProductResponse getOneProduct(int id);

    List<ProductResponse> getAllProduct();

    ProductResponse updateProduct(int id);

    boolean exisById(int id);
}
