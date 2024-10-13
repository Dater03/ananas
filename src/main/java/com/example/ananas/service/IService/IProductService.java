package com.example.ananas.service.IService;

import com.example.ananas.dto.request.ProductCreateRequest;
import com.example.ananas.dto.response.ProductImagesResponse;
import com.example.ananas.dto.response.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProductService {
    ProductCreateRequest createProduct(ProductCreateRequest productCreateRequest);

    ProductResponse getOneProduct(int id);

    List<ProductResponse> getAllProduct();

    ProductResponse updateProduct(int id, ProductCreateRequest productCreateRequest);

    boolean exisById(int id);

    void deleteProduct(int id);

    void uploadImages(int id, MultipartFile file) throws IOException;

    List<ProductImagesResponse> getAllImages(int id);
}
