package com.example.ananas.controller;

import com.example.ananas.dto.request.ProductCreateRequest;
import com.example.ananas.dto.response.ProductImagesResponse;
import com.example.ananas.dto.response.ProductResponse;
import com.example.ananas.exception.IdException;
import com.example.ananas.service.Service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
public class ProductController {

    ProductService productService;


    @PostMapping("/product")
    public ResponseEntity<ProductCreateRequest> createProduct(@RequestBody ProductCreateRequest productCreateRequest)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productCreateRequest));
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponse> getOneProduct(@PathVariable int id) throws IdException
    {
        if(!this.productService.exisById(id))
        {
            throw new IdException("id sản phẩm không tồn tại");
        }
        return ResponseEntity.ok(productService.getOneProduct(id));
    }
    @GetMapping("/product")
    public ResponseEntity<List<ProductResponse>> getAllProduct()
    {
        return ResponseEntity.ok(productService.getAllProduct());
    }
    @PutMapping("product/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable int id, @RequestBody ProductCreateRequest productCreateRequest) throws IdException
    {
        if(!this.productService.exisById(id))
            throw new IdException("id sản phẩm không tồn tại");
        return ResponseEntity.ok(productService.updateProduct(id,productCreateRequest));
    }
    @DeleteMapping("product/{id}")
    public ResponseEntity<String> delteProduct(@PathVariable int id) throws IdException
    {
        if(!this.productService.exisById(id))
            throw new IdException("không tồn tại id sản phẩm");
        this.productService.deleteProduct(id);
        return ResponseEntity.ok("xóa thành công sản phẩm có id: "+id);
    }

    @PutMapping("product/add_images/{id}")
    public ResponseEntity<String> addImages(@PathVariable int id, @RequestParam(name = "image")MultipartFile file) throws IdException, IOException {
        if(!this.productService.exisById(id))
            throw new IdException("id sản phẩm không tồn tại");
        this.productService.uploadImages(id,file);
        return ResponseEntity.ok("them thanh cong");
    }
    @GetMapping("/product/images/{id}")
    public ResponseEntity<List<ProductImagesResponse>> getAllImages(@PathVariable int id) throws IdException
    {
        if (!this.productService.exisById(id))
            throw new IdException("id sản phẩm không tồn tại");
        return ResponseEntity.ok(this.productService.getAllImages(id));
    }

    //phương thức xóa ảnh của sản phaam

    // phương thức update ảnh sản phẩm
}
