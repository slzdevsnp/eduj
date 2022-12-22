package com.pluralsight.service;

import com.pluralsight.entity.Ticket;

public interface TicketService {
    Iterable<Ticket> listTickets();
}


