package com.example.ananas.controller;

import com.example.ananas.entity.TempOrder;
import com.example.ananas.service.Service.OrderService;
import com.example.ananas.service.Service.TempOrderService;
import com.example.ananas.service.Service.VnpayService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

@RestController
@AllArgsConstructor
public class PaymentController {

    VnpayService vnpayService;
    TempOrderService tempOrderService;
    OrderService orderService;

    @GetMapping("/createPayment")
    public String createPayment(@RequestParam String orderInfo, @RequestParam long amount, @RequestParam int orderId)   {
        try {
            // lưu thông tin order vào bảng tạm để đối chiếu xử lý sau khi thanh toán
            String result = vnpayService.createPaymentURL(orderInfo, amount);
            TempOrder tempOrder = new TempOrder();
            tempOrder.setOrderId(orderId);
            tempOrder.setTxnRef(vnpayService.code);
            this.tempOrderService.save(tempOrder);
            return result ;

        }
        catch (Exception e){
           return "xay ra loi: " + e.getMessage().toString();
        }
    }

    @GetMapping("/vnpay-return")
    public String vnpayReturn(@RequestParam Map<String, String> params) {
        String vnp_SecureHash = params.get("vnp_SecureHash");
        params.remove("vnp_SecureHash");

        // In ra các tham số nhận được
        System.out.println("Received params: " + params);
        StringBuilder hashData = new StringBuilder();
        params.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    try {
                        if (entry.getValue() != null) {
                            hashData.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), "UTF-8")).append("&");
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                });

        // Loại bỏ ký tự '&' cuối cùng
        if (hashData.length() > 0) {
            hashData.setLength(hashData.length() - 1);
        }

        // Tính toán mã hash
        String computedHash = vnpayService.HMACSHA512(vnpayService.getHashSecret(), hashData.toString());

        // In ra các thông tin cần thiết để kiểm tra
        System.out.println("Computed Hash: " + computedHash);
        System.out.println("Received Secure Hash: " + vnp_SecureHash);

        if (computedHash.equals(vnp_SecureHash)) {
            String vnp_ResponseCode = params.get("vnp_ResponseCode");
            String vnp_TxnRef = params.get("vnp_TxnRef");
            if ("00".equals(vnp_ResponseCode)) {
                // thao tác lưu hóa đơn <<thêm sau :v
                //dùng một bảng phụ để lưu các thông tin liên quan đến đơn hàng gửi đi trước khi thanh toán.
                TempOrder tempOrder = this.tempOrderService.findByTxnRef(vnp_TxnRef);
                this.orderService.changePaymentStatus(tempOrder.getOrderId(),"paid");
                this.orderService.handleAfterCreateOrder(tempOrder.getOrderId());

                return "Giao dịch thành công mời bạn về trang chủ";
            } else {

                //xóa order trước khi thông báo không thanhf công
                TempOrder tempOrder = this.tempOrderService.findByTxnRef(vnp_TxnRef);
                this.orderService.deleteOrder(tempOrder.getOrderId());
                return "Giao dịch thất bại mời bạn về trang chủ";
            }
        } else {
            return "Chữ ký không hợp lệ";
        }
    }


}