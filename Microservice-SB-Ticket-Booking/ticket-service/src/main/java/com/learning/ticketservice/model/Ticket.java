package com.learning.ticketservice.model;

import com.learning.ticketservice.model.dto.BusDetailsInput;
import com.learning.ticketservice.model.dto.UserDetailsForTicketInput;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketId;

    @GeneratedValue(strategy = GenerationType.UUID)
    private String ticketNumber;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

//    @OneToOne
//    private BusDetailsInput busDetails;
//
//    @OneToOne
//    private UserDetailsForTicketInput userDetailsForTicket;


}
