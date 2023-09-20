package com.learning.busservice.service;

import com.learning.busservice.model.Bus;
import com.learning.busservice.model.dto.BusOppRequestInput;
import com.learning.busservice.model.dto.BusRequestInput;
import com.learning.busservice.repository.IBusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusService {
    @Autowired
    IBusRepo busRepo;

    public String createBus(BusRequestInput busRequestInput, BusOppRequestInput oppDetails) {
        Bus newBus = Bus.builder()
                .busCityFrom(busRequestInput.getBusCityFrom())
                .busCityTo(busRequestInput.getBusCityTo())
                .busNumber(busRequestInput.getBusNumber())
                .busTicketPrice(busRequestInput.getBusTicketPrice())
                .busDepartureTime(busRequestInput.getBusDepartureTime())
                .busArrivalTime(busRequestInput.getBusArrivalTime())
                .busOppNumber(oppDetails.getBusOppNumber())
                .busOppName(oppDetails.getBusOppName())
                .busOppEmail(oppDetails.getBusOppEmail())
                .build();
        Bus savedBus = busRepo.save(newBus);
        return "A new Bus service is created : "+ savedBus.getBusNumber();
    }

    public String searchBus(String cityFrom, String cityTo) {

    }
}
