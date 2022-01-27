package com.couponsProject.project2.Controller;

import com.couponsProject.project2.Beans.Category;
import com.couponsProject.project2.Beans.Coupon;
import com.couponsProject.project2.Beans.UserDetails;
import com.couponsProject.project2.Exceptions.AlreadyExistsException;
import com.couponsProject.project2.Exceptions.IllegalRequestException;
import com.couponsProject.project2.MyUtils.JWTutil;
import com.couponsProject.project2.Service.ClientType;
import com.couponsProject.project2.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * controller class to externalize CustomerService methods by REST
 */

@RestController
@RequestMapping("customer")  //http://localhost:8080/
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
//@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class CustomerServiceController {
    private final CustomerService customerService;
    private final JWTutil jwtUtil;


    @PostMapping("coupon/purchase")
    public ResponseEntity<?> purchaseCoupon(@RequestHeader(name = "Authorization") String token, @RequestBody Coupon coupon) throws Exception {
        try {
            if (jwtUtil.validateToken(token)) {
                customerService.purchaseCoupon(coupon, getExtractId(token));
                return ResponseEntity.accepted()
                        .headers(getHeaders(token))
                        .body("successfully purchased");
            }
            return new ResponseEntity<>("Invalid Token", HttpStatus.UNAUTHORIZED);
        } catch (IllegalRequestException e) {
            throw new AlreadyExistsException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("coupon/getAll")
    public ResponseEntity<?> getAllCoupons(@RequestHeader(name = "Authorization") String token) throws Exception {
        try {
            if (jwtUtil.validateToken(token)) {
                return ResponseEntity.ok()
                        .headers(getHeaders(token))
                        .body(customerService.getCustomerCoupons(getExtractId(token)));
            }
            return new ResponseEntity<>("Invalid Token", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("coupon/getByCategory")
    public ResponseEntity<?> getCouponsByCategory(@RequestHeader(name = "Authorization") String token, @RequestBody Category category) throws Exception {
        try {
            if (jwtUtil.validateToken(token)) {
                return ResponseEntity.ok()
                        .headers(getHeaders(token))
                        .body(customerService.getCustomerCoupons(category, getExtractId(token)));
            }
            return new ResponseEntity<>("Invalid Token", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("coupon/getByMaxPrice")
    public ResponseEntity<?> getCouponsByMaxPrice(@RequestHeader(name = "Authorization") String token, @RequestBody double maxPrice) throws Exception {
        try {
            if (jwtUtil.validateToken(token)) {
                return ResponseEntity.ok()
                        .headers(getHeaders(token))
                        .body(customerService.getCustomerCoupons(maxPrice, getExtractId(token)));
            }
            return new ResponseEntity<>("Invalid Token", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("getDetails")
    public ResponseEntity<?> getDetails(@RequestHeader(name = "Authorization") String token) throws Exception {
        try {
            if (jwtUtil.validateToken(token)) {
                return ResponseEntity.ok()
                        .headers(getHeaders(token))
                        .body(customerService.getCustomerDetails(getExtractId(token)));
            }
            return new ResponseEntity<>("Invalid Token", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    private org.springframework.http.HttpHeaders getHeaders(String token) {
        //create new userDetail and DI
        UserDetails userDetails = new UserDetails();
        userDetails.setEmail(jwtUtil.extractEmail(token));
        userDetails.setClientType(ClientType.valueOf((String) jwtUtil.extractAllClaims(token).get("clientType")));
        userDetails.setId((int) jwtUtil.extractAllClaims(token).get("id"));
        //send ok with header of new token
        org.springframework.http.HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", jwtUtil.generateToken(userDetails));
        return httpHeaders;
    }

    private int getExtractId(String token) {
        System.out.println(jwtUtil.extractAllClaims(token));
        return (int) jwtUtil.extractAllClaims(token).get("id");
    }

}
