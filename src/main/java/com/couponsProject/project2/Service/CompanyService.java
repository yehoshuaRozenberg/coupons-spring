package com.couponsProject.project2.Service;

import com.couponsProject.project2.Beans.Category;
import com.couponsProject.project2.Beans.Company;
import com.couponsProject.project2.Beans.Coupon;
import com.couponsProject.project2.Exceptions.AlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Class- making all logic needed in methods to give response for all things required in Company's allowed requests,\
 * getting data by Repositories
 */

@Service
//@RequiredArgsConstructor
public class CompanyService extends ClientService {
//    private int id;


    @Override
    public boolean login(String email, String password) {
        if (companyRepo.existsCompanyByEmailAndPassword(email, password)) {
    //        this.id = companyRepo.findByEmail(email).getId();
            return true;
        } else return false;
    }

    public int getId(String email) {
        return companyRepo.findByEmail(email).getId();
    }

    public void addCoupon(Coupon coupon, int id) throws AlreadyExistsException {
        if (couponRepo.existsCouponByTitleAndCompanyID(coupon.getTitle(), id)) {
            throw new AlreadyExistsException("coupons name already exists in company");
        }
        coupon.setCompanyID(id);
        couponRepo.save(coupon);
        System.out.println("coupon successfully added");
    }

    public void updateCoupon(Coupon coupon, int id) throws AlreadyExistsException {
        if (couponRepo.existsCouponByTitleAndCompanyID(coupon.getTitle(), id)&&
                !couponRepo.existsCouponByTitleAndCompanyIDAndId(coupon.getTitle(),id,coupon.getId())) {
            throw new AlreadyExistsException("coupons name already exists in company");
        }
        coupon.setCompanyID(id); //make sure company id will not change
        couponRepo.saveAndFlush(coupon);
        System.out.println("coupon successfully updated");
    }

    public void deleteCoupon(int id) {
        couponRepo.deleteById(id);
        System.out.println("coupon successfully deleted");
    }

    public List<Coupon> getCompanyCoupons(int id) {
        return companyRepo.findById(id).getCoupons();
    } //todo giving null!!!!!!!!

    public List<Coupon> getCompanyCoupons(Category category, int id) {
        return couponRepo.findByCompanyIDAndCategoryID( id, category);
    }

    public List<Coupon> getCompanyCoupons(double maxPrice, int id) {
        return couponRepo.findByCompanyIDAndPriceLessThan(id, maxPrice+1);//+1 to include the price itself
    }

    public Company getCompanyDetails(int id) {
        return companyRepo.findById(id);
    }



}
