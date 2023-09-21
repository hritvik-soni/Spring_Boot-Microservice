package com.learning.ticketservice.model;

import com.learning.ticketservice.model.dto.BusDetailsInput;
import com.learning.ticketservice.model.dto.Gender;
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
import java.time.LocalTime;
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

    private String busCityFrom;
    private String busCityTo;
    private String busNumber;
    private String busName;
    private Integer busSeatNumber;
    private String busOppNumber;
    private Integer busTicketPrice;
    private LocalTime busDepartureTime;
    private LocalTime busArrivalTime;

    private String userName;
    private String userEmail;
    private String userMobileNumber;
    private Integer userAge;
    private Gender gender;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime modifiedAt;


}
