package com.couponsProject.project2.CLR;

import com.couponsProject.project2.Beans.Category;
import com.couponsProject.project2.Beans.Company;
import com.couponsProject.project2.Beans.Coupon;
import com.couponsProject.project2.Beans.Customer;
import com.couponsProject.project2.Exceptions.AlreadyExistsException;
import com.couponsProject.project2.Exceptions.IllegalRequestException;
import com.couponsProject.project2.Repositories.CompanyRepo;
import com.couponsProject.project2.Repositories.CouponRepo;
import com.couponsProject.project2.Repositories.CustomerRepo;
import com.couponsProject.project2.Service.CompanyService;
import com.couponsProject.project2.Service.CustomerService;
import com.couponsProject.project2.Service.LoginManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class dataForRelationsTester {
    private final CompanyRepo companyRepo;
    private final CustomerRepo customerRepo;
    private final CouponRepo couponRepo;
    private final LoginManager loginManager;
    private final CustomerService customerService;
    private final CompanyService companyService;

    public void createCompany() {
        Company company1 = Company.builder()
                .name("pizza")
                .email("cheese@suass.com")
                .password("pizza123")
                .build();
        Company company2 = Company.builder()
                .name("nofshim")
                .email("email@email.com")
                .password("12345678")
                .build();
        Company company3 = Company.builder()
                .name("best electric")
                .email("email@be.com")
                .password("123456")
                .build();

        Company company4 = Company.builder()
                .name("papagayo")
                .email("papagayo@meat.com")
                .password("papagayo123")
                .build();

        companyRepo.save(company1);
        companyRepo.save(company2);
        companyRepo.save(company3);
        companyRepo.save(company4);

        System.out.println("4 companies created");

    }

    public void createCoupons() {
        System.out.println("creating coupons for companies...");
        Coupon coupon1 = Coupon.builder()
                .title("pizza")
                .categoryID(Category.FOOD)
                .description("1+1")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf("2022-9-1"))
                .price(75)
                .amount(100)
                .image("https://backendlessappcontent.com/A27F4009-7EAA-5BC6-FFF3-6D154177E700/C384113B-FEC3-4C7E-BCE1-814C15B1B7C9/files/pizza%21.png")
                .build();
        Coupon coupon2 = Coupon.builder()
                .title("Zimmer")
                .categoryID(Category.VACATION)
                .description("1+1 night")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf("2022-1-1"))
                .price(1500)
                .amount(10)
                .image("https://backendlessappcontent.com/A27F4009-7EAA-5BC6-FFF3-6D154177E700/C384113B-FEC3-4C7E-BCE1-814C15B1B7C9/files/hotel.png")
                .build();
        Coupon coupon3 = Coupon.builder()
                .title("mazgan")
                .categoryID(Category.ELECTRICITY)
                .description("-30%")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf("2024-12-1"))
                .price(750)
                .amount(200)
                .image("https://backendlessappcontent.com/A27F4009-7EAA-5BC6-FFF3-6D154177E700/C384113B-FEC3-4C7E-BCE1-814C15B1B7C9/files/air-conditioner.png")
                .build();
        Coupon coupon4 = Coupon.builder()
                .title("meat")
                .categoryID(Category.FOOD)
                .description("-30%")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf("2024-12-1"))
                .price(20)
                .amount(200)
                .image("https://backendlessappcontent.com/A27F4009-7EAA-5BC6-FFF3-6D154177E700/C384113B-FEC3-4C7E-BCE1-814C15B1B7C9/files/meat.jpg")
                .build();
        Coupon coupon5 = Coupon.builder()
                .title("chulent")
                .categoryID(Category.FOOD)
                .description("-50%")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf("2024-12-1"))
                .price(15)
                .amount(200)
                .image("https://backendlessappcontent.com/A27F4009-7EAA-5BC6-FFF3-6D154177E700/C384113B-FEC3-4C7E-BCE1-814C15B1B7C9/files/chulent.webp")
                .build();
        Coupon coupon6 = Coupon.builder()
                .title("computer screen")
                .categoryID(Category.ELECTRICITY)
                .description("-15%")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf("2024-12-1"))
                .price(90)
                .amount(100)
                .image("https://backendlessappcontent.com/A27F4009-7EAA-5BC6-FFF3-6D154177E700/C384113B-FEC3-4C7E-BCE1-814C15B1B7C9/files/computer+screen.png")
                .build();
        try {
            //CompanyService companyService1 = (CompanyService) loginManager.login("email", "123456", ClientType.Companies);
            companyService.addCoupon(coupon1,1);
            //CompanyService companyService2 = (CompanyService) loginManager.login("email@email", "12345678", ClientType.Companies);
            companyService.addCoupon(coupon2,2);
            //CompanyService companyService3 = (CompanyService) loginManager.login("cheese@suass", "pizza123", ClientType.Companies);
            companyService.addCoupon(coupon3,3);
            companyService.addCoupon(coupon4,4);
            companyService.addCoupon(coupon5,4);
            companyService.addCoupon(coupon6,3);

        } catch ( AlreadyExistsException e) {
            System.out.println(e);
        }


        System.out.println("coupons created");
//        couponRepo.save(coupon1);
//        couponRepo.save(coupon2);
//        couponRepo.save(coupon3);

    }

    public void createCustomers() {
        Customer customer1 = Customer.builder()
                .firstName("ronen")
                .lastName("cohen")
                .email("ronen@cohen.com")
                .password("12345")
                .build();
        Customer customer2 = Customer.builder()
                .firstName("shira")
                .lastName("levi")
                .email("shira@levi.com")
                .password("12345")
                .build();
        Customer customer3 = Customer.builder()
                .firstName("yaakov")
                .lastName("israel")
                .email("yaakov@israel.com")
                .password("12345")
                .build();
        customerRepo.save(customer1);
        customerRepo.save(customer2);
        customerRepo.save(customer3);
        System.out.println("3 customers created!");
    }


    public void createCouponsPurchases() {
        try {
            System.out.println("creating coupons purchases...");
            //CustomerService customerService1 = (CustomerService) loginManager.login("ronen@cohen", "12345", ClientType.Customers);
            customerService.purchaseCoupon(couponRepo.findById(1),1);
            customerService.purchaseCoupon(couponRepo.findById(3),1);
            customerService.purchaseCoupon(couponRepo.findById(4),1);
            customerService.purchaseCoupon(couponRepo.findById(5),1);

            //CustomerService customerService2 = (CustomerService) loginManager.login("shira@levi", "12345", ClientType.Customers);
            customerService.purchaseCoupon(couponRepo.findById(1),2);
            customerService.purchaseCoupon(couponRepo.findById(2),2);
            customerService.purchaseCoupon(couponRepo.findById(4),2);
            customerService.purchaseCoupon(couponRepo.findById(6),2);

            //CustomerService customerService3 = (CustomerService) loginManager.login("yaakov@israel", "12345", ClientType.Customers);
            customerService.purchaseCoupon(couponRepo.findById(3),3);
            customerService.purchaseCoupon(couponRepo.findById(6),3);

            System.out.println("coupons purchases created!");
        } catch ( IllegalRequestException e) {  //todo chek why fall down when coupons of company 1 didnt exist
            System.out.println(e);
        }
    }

}