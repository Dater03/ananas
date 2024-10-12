package com.example.ananas.service.Service;

import com.example.ananas.dto.request.ProductCreateRequest;
import com.example.ananas.dto.response.ProductResponse;
import com.example.ananas.entity.Product;
import com.example.ananas.mapper.IProductMapper;
import com.example.ananas.repository.Category_Repository;
import com.example.ananas.repository.Product_Repository;
import com.example.ananas.service.IService.IProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService implements IProductService {

    Product_Repository productRepository;
    Category_Repository categoryRepository;
    IProductMapper productMapper;
    @Override
    public ProductCreateRequest createProduct(ProductCreateRequest productCreateRequest) {
        Product product = new Product();
        product.setProductName(productCreateRequest.getProductName());
        product.setCategory(this.categoryRepository.findByCategoryName(productCreateRequest.getCategory()));
        product.setColor(productCreateRequest.getColor());
        product.setDescription(productCreateRequest.getDescription());
        product.setDiscount(productCreateRequest.getDiscount());
        product.setMaterial(productCreateRequest.getMaterial());
        product.setPrice(productCreateRequest.getPrice());
        product.setSize(productCreateRequest.getSize());
        product.setStock(productCreateRequest.getStock());
        product.setSoldQuantity(0);
        this.productRepository.save(product);
        return productCreateRequest;
    }

    @Override
    public ProductResponse getOneProduct(int id) {

        return productMapper.toProductResponse(this.productRepository.findById(id).get());
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        List<Product> productList = this.productRepository.findAll();
        return this.productMapper.toProductResponseList(productList);
    }

    @Override
    public ProductResponse updateProduct(int id) {
        return null;
    }

    @Override
    public boolean exisById(int id) {
        return this.productRepository.existsById(id);
    }
}
