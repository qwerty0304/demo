package com.example.demo.feign;

import com.example.demo.model.Discount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "discount", url = "https://mynt-exam.mocklab.io/")
public interface DiscountFeignClient {
    @GetMapping(value = "/voucher/{voucherCode}", produces = "application/json")
    Discount getDiscount(@PathVariable("voucherCode") String voucherCode,
                         @RequestParam("key") String key);
}
