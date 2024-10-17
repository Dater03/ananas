package com.example.ananas.controller;
import com.example.ananas.dto.request.VoucherResquest;
import com.example.ananas.dto.response.ApiResponse;
import com.example.ananas.dto.response.VoucherResponse;
import com.example.ananas.entity.voucher.Voucher;
import com.example.ananas.service.Service.VoucherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/voucher")
public class VoucherController {

    @Autowired
    private VoucherService voucherService;

// Phương thức chung

    // Xem chi tiết voucher -  getVoucherDetail(String code)
    @GetMapping("/{code}")
    public ResponseEntity<ApiResponse<VoucherResponse>> getVouchersForUser(@PathVariable String code) {
        ApiResponse<VoucherResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1000);
        apiResponse.setMessage("Success");
        apiResponse.setResult(voucherService.getVouchersForUser(code));
        return ResponseEntity.ok(apiResponse);
    }

// Admin riêng

    @GetMapping("/admin/list")
    public ResponseEntity<ApiResponse<List<Voucher>>> getAllVouchersForAdmin() {
        ApiResponse<List<Voucher>> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1000);
        apiResponse.setMessage("Success");
        apiResponse.setResult(voucherService.getAllVouchersForAdmin());
        return ResponseEntity.ok(apiResponse);
    }

    // Tạo voucher mới - VoucherResponse createVoucher(VoucherRequest voucherRequest)
    @PostMapping("/admin/create")
    public ResponseEntity<ApiResponse<Voucher>> createVoucher(@Valid @RequestBody VoucherResquest voucher) {
        Voucher voucher1 = voucherService.createVoucher(voucher);
        ApiResponse<Voucher> apiResponse = new ApiResponse<>();
        apiResponse.setCode(2000);
        apiResponse.setMessage("Success");
        apiResponse.setResult(voucher1);
        return ResponseEntity.ok(apiResponse);
    }
    // Cập nhật voucher - void updateVoucher(VoucherUpdateRequest voucherUpdateRequest)
    @PutMapping("/admin/update")
    public  ResponseEntity<ApiResponse<Voucher>> updateVoucher(@Valid @RequestBody VoucherResquest voucher) {
        Voucher voucher1 = voucherService.updateVoucher(voucher);
        ApiResponse<Voucher> apiResponse = new ApiResponse<>();
        apiResponse.setCode(2100);
        apiResponse.setMessage("Success");
        apiResponse.setResult(voucher1);
        return ResponseEntity.ok(apiResponse);
    }

    //  Xóa voucher - void deleteVoucher(Long voucherId)
    @DeleteMapping("/admin/delete/{code}")
    public ResponseEntity<ApiResponse<String>> deleteVoucher(@PathVariable String code) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setCode(2000);
        if(voucherService.deleteVoucher(code))
        {
            apiResponse.setMessage("Success");
            apiResponse.setResult("Deleted voucher!");
        }
        else
        {
            apiResponse.setMessage("Failed");
            apiResponse.setResult("Failed to delete voucher!");
        }
        return ResponseEntity.ok(apiResponse);
    }
}
