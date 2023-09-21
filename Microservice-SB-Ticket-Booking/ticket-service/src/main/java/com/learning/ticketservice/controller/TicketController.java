package com.learning.ticketservice.controller;

import com.learning.ticketservice.model.dto.BusDetailsInput;
import com.learning.ticketservice.model.dto.TicketRequestInput;
import com.learning.ticketservice.model.dto.UserDetailsForTicketInput;
import com.learning.ticketservice.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

   private RestTemplate restTemplate;

   @Autowired
    TicketService ticketService;

    @PostMapping("/new")
    public String createTicket ( @RequestParam String busNumber , @RequestParam String userEmails){

//        boolean busIsValid = restTemplate.getForObject("bus-service/api/bus/busIsValid?="+busNumber , boolean.class);
//        if(!busIsValid ){
//            return "Invalid Bus number please try again !!!";
//        }

        BusDetailsInput busDetailsInput = restTemplate.getForObject("bus-service/api/bus/detail?="+ busNumber , BusDetailsInput.class);

        if(busDetailsInput==null){
            return "Invalid Bus number please try again !!!";
        }

        UserDetailsForTicketInput  userDetailsForTicket =restTemplate.getForObject("user-service/api/user/info/ticket?="+ userEmails , UserDetailsForTicketInput.class);

        if(userDetailsForTicket==null){
            return "User Details not Found please try again with correct details !!!";
        }

        return ticketService.createTicket(userDetailsForTicket,busDetailsInput);


    }








}
