package com.example.ananas.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoucherController {

// Phương thức chung

    //  Lấy danh sách voucher - List<VoucherResponse> getAllVouchers()

    // Xem chi tiết voucher - VoucherResponse getVoucherDetail(Long voucherId)

// Admin riêng

    // Tạo voucher mới - VoucherResponse createVoucher(VoucherRequest voucherRequest)

    // Cập nhật voucher - void updateVoucher(Long voucherId, VoucherUpdateRequest voucherUpdateRequest)

    //  Xóa voucher - void deleteVoucher(Long voucherId)

// User riêng

    //   Áp dụng voucher cho đơn hàng khi thanh toán - void applyVoucherToOrder(Long orderId, Long voucherId)

    //   Xem danh sách các voucher có thể áp dụng - List<VoucherResponse> getAvailableVouchers()

    //   Kiểm tra xem voucher có hợp lệ không trước khi áp dụng - VoucherResponse checkVoucherValidity(String voucherCode)

}
