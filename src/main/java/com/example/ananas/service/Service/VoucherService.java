package com.example.ananas.service.Service;

import com.example.ananas.dto.request.VoucherResquest;
import com.example.ananas.dto.response.VoucherResponse;
import com.example.ananas.entity.voucher.DiscountType;
import com.example.ananas.entity.voucher.Voucher;
import com.example.ananas.exception.AppException;
import com.example.ananas.exception.ErrException;
import com.example.ananas.mapper.IVoucherMapper;
import com.example.ananas.repository.Voucher_Repository;
import com.example.ananas.service.IService.IVoucherService;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VoucherService implements IVoucherService {

    private Voucher_Repository voucherRepository;
    private IVoucherMapper  mapper;

    @Override
    public List<Voucher> getAllVouchersForAdmin() {
        return voucherRepository.findAll();
    }

    @Override
    public VoucherResponse getVouchersForUser(String code)
    {
        Voucher voucher = voucherRepository.findVoucherByCode(code);
        return mapper.voucherToVoucherResponse(voucher);
    }

    @Override
    public Voucher createVoucher(VoucherResquest voucherResquest) {
        // Kiểm tra xem mã voucher có tồn tại hay không
        if (voucherRepository.findVoucherByCode(voucherResquest.getCode()) != null) {
            throw new AppException(ErrException.VOUCHER_EXISTED);
        }
        if(voucherResquest.getDiscountType().isEmpty()) throw new AppException(ErrException.VALIDATION_ERROR);

        // Nếu không có createdAt trong request thì tự động gán thời gian hiện tại
        if (voucherResquest.getCreatedAt() == null) {
            voucherResquest.setCreatedAt(Timestamp.from(Instant.now()));
        } else {
            // Nếu có createdAt, đảm bảo chuyển đổi đúng định dạng từ chuỗi sang Timestamp (nếu cần)
            try {
                voucherResquest.setCreatedAt(Timestamp.valueOf(voucherResquest.getCreatedAt().toLocalDateTime()));
            } catch (Exception e) {
                throw new AppException(ErrException.INVALID_DATE_FORMAT);
            }
        }

        // Ánh xạ từ VoucherResquest sang entity Voucher
        Voucher voucher = mapper.voucherRequestToVoucher(voucherResquest);
        if(voucherResquest.getDiscountType().equalsIgnoreCase("FIXED")) voucher.setDiscountType(DiscountType.FIXED);
        else if(voucherResquest.getDiscountType().equalsIgnoreCase("PERCENTAGE")) voucher.setDiscountType(DiscountType.PERCENTAGE);
        else throw new AppException(ErrException.VALIDATION_ERROR);
        // Lưu voucher vào cơ sở dữ liệu
        return voucherRepository.save(voucher);
    }

    @Override
    public Voucher updateVoucher(VoucherResquest voucherResquest) {
        Voucher voucher = voucherRepository.findVoucherByCode(voucherResquest.getCode());
        if (voucher == null) throw new AppException(ErrException.VOUCHER_NOT_EXISTED);
        mapper.updateVoucher(voucher, voucherResquest);
        return voucherRepository.save(voucher);
    }

    @Override
    public boolean deleteVoucher(String code) {
        Voucher voucher = voucherRepository.findVoucherByCode(code);
        if (voucher == null) throw new AppException(ErrException.VOUCHER_NOT_EXISTED);
        voucherRepository.delete(voucher);
        return true;
    }

    @Override
    public boolean checkVoucher(String code) {
        Voucher voucher = voucherRepository.findVoucherByCode(code);
        if (voucher == null)
        {
            throw new AppException(ErrException.VOUCHER_NOT_EXISTED);
        }
        Date now = new Date(System.currentTimeMillis());

        if(now.before(voucher.getCreatedAt()) || now.after(voucher.getCreatedAt()))  return false; // Chưa được áp dụng or hết hạn áp dụng

        if(voucher.getUsageLimit() != null && voucher.getUsageLimit() <= 0) return false; // Hết số lượng

        return true;
    }

    @Override
    public BigDecimal applyVoucher(Voucher voucher, BigDecimal priceBefore) {
        // Kiểm tra điều kiện tối thiểu
        if(priceBefore.compareTo(voucher.getMinOrderValue()) < 0)
            return priceBefore;

        // Kiem tra điều kiện tối đa
        BigDecimal sumPriceDiscount;
        if(voucher.getDiscountType() == DiscountType.PERCENTAGE)
            sumPriceDiscount = priceBefore.multiply(voucher.getDiscountValue().divide(BigDecimal.valueOf(100)));

        else sumPriceDiscount = voucher.getDiscountValue().multiply(BigDecimal.valueOf(1000));

        if(sumPriceDiscount.compareTo(voucher.getMaxDiscount()) <= 0) return sumPriceDiscount;
        return voucher.getMaxDiscount();
    }
}
