package com.learning.ticketservice.service;

import com.learning.ticketservice.model.Ticket;
import com.learning.ticketservice.model.dto.BusDetailsInput;
import com.learning.ticketservice.model.dto.UserDetailsForTicketInput;
import com.learning.ticketservice.repository.ITicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class TicketService {

    @Autowired
    ITicketRepo ticketRepo;


    public String createTicket(UserDetailsForTicketInput userDetailsForTicket, BusDetailsInput busDetailsInput) {

        Ticket newTicket = Ticket.builder()



                .build();

        Ticket savedTicket = ticketRepo.save(newTicket);

        return " Your Ticket is Booked Successfully : " + savedTicket.getTicketNumber() +" at : " + savedTicket .getCreatedAt() ;
    }
}
