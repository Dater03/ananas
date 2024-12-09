package com.example.ananas.repository;
import com.example.ananas.entity.voucher.Voucher_User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Voucher_User_Repository extends JpaRepository<Voucher_User, Integer> {

    @Query("select vu from Voucher_User vu where vu.user.userId = :userId")
    List<Voucher_User> findVoucherByUserId(@Param("userId") int userId);

    @Query("select vu from Voucher_User vu where vu.user.userId = :userId and vu.voucher.voucherId =:voucherId")
    Voucher_User findVoucherByUserAndVoucher(@Param("userId") Integer userId, @Param("voucherId") Integer voucherId);
}
