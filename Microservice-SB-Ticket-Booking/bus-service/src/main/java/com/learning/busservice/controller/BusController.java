package com.learning.busservice.controller;

import com.learning.busservice.model.dto.BusOppRequestInput;
import com.learning.busservice.model.dto.BusRequestInput;
import com.learning.busservice.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/bus")
public class BusController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    BusService busService;

    @PostMapping("/new")
    public String createBus(@RequestBody BusRequestInput busRequestInput, @RequestParam String email){
        if(email.substring(email.length()-8,email.length()).equals("@bus.com")){
            BusOppRequestInput oppDetails = restTemplate.getForObject("user-service/api/user/info/bus?="+email,
                    BusOppRequestInput.class);
            if(oppDetails==null){
                return "Bus operator not found cannot create bus service";
            }
            return busService.createBus(busRequestInput,oppDetails);

        }

        return "Only bus operator can create bus!!!";
    }
}
