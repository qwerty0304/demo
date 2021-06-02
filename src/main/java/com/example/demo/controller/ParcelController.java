package com.example.demo.controller;

import com.example.demo.feign.DiscountFeignClient;
import com.example.demo.model.Discount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParcelController {

    @Autowired
    private DiscountFeignClient feignClient;

    @GetMapping(value = "/cost/weight")
    public String getCostByWeight(@RequestParam String weight, @RequestParam(required = false) String voucherCode) {
        String cost = null;
        if (Integer.parseInt(weight) > 50) {
            cost = "N/A";
        } else if (Integer.parseInt(weight) > 10) {
            Integer sum = Integer.parseInt(weight) * 20;
            cost = getDiscount(voucherCode, sum);
        } else {
            cost = "Parcel too small. Use Volume API";
        }
        return cost;
    }

    @GetMapping(value = "/cost/volume")
    public String getCostByVolume(@RequestParam String height, @RequestParam String width, @RequestParam String length) {
        String cost = null;
        Integer volume = Integer.parseInt(height) * Integer.parseInt(width) * Integer.parseInt(length);
        if (volume < 1500) {
            cost = "PHP " + volume * 0.03;
        } else if (volume < 2500) {
            cost = "PHP " + volume * 0.04;
        } else {
            cost = "PHP " + volume * 0.05;
        }
        return cost;
    }

    private String getDiscount(String voucherCode, Integer sum) {
        String cost = null;
        if (voucherCode != null) {
            //cannot run feign, cert error
            Discount discount = feignClient.getDiscount(voucherCode, "apikey");
            cost = "PHP " + (sum - discount.getDiscount());
//            cost = "PHP " + (sum - 12.25);
        }
        return cost;
    }

}
