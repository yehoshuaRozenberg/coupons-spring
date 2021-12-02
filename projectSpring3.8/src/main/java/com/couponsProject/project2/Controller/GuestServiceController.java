package com.couponsProject.project2.Controller;

import com.couponsProject.project2.Service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * controller class to externalize AdminService methods by REST
 */
@RestController
@RequestMapping("couponsProject/guest")  //http://localhost:8080/
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GuestServiceController {
    private final GuestService guestService;

    @PostMapping("coupons/get")
    public ResponseEntity<?> getCoupons() {
        return new ResponseEntity<>(guestService.getCoupons(), HttpStatus.OK);
    }

}
