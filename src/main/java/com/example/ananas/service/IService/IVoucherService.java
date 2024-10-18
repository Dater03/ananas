package com.example.ananas.service.IService;

import com.example.ananas.dto.request.VoucherResquest;
import com.example.ananas.dto.response.VoucherResponse;
import com.example.ananas.entity.voucher.Voucher;

import java.math.BigDecimal;
import java.util.List;

public interface IVoucherService {

     VoucherResponse getVouchersForUser(String code);

     Voucher createVoucher(VoucherResquest voucherResquest);

     List<Voucher> getAllVouchersForAdmin();

     Voucher updateVoucher(VoucherResquest voucherResquest);

     boolean deleteVoucher(String code);

     boolean checkVoucher(String code);

     BigDecimal applyVoucher(Voucher voucher, BigDecimal priceBefore);

}
