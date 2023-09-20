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
                .


                .build()
    }
}
