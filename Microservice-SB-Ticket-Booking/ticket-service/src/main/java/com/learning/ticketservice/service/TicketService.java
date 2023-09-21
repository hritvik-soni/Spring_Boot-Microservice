package com.learning.ticketservice.service;

import com.learning.ticketservice.repository.ITicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class TicketService {

    @Autowired
    ITicketRepo ticketRepo;




}
