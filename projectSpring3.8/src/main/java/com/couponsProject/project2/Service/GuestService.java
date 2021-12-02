package com.couponsProject.project2.Service;

import com.couponsProject.project2.Beans.Coupon;
import com.couponsProject.project2.Repositories.CouponRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuestService {
    private final CouponRepo couponRepo;

    public List<Coupon> getCoupons (){
        return couponRepo.findAll(); //todo best sellers or few from category
    }



}
