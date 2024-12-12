package ticketsystem.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ticketsystem.domain.model.Ticket;
import ticketsystem.domain.model.Ticket.Status;
import ticketsystem.infrastructure.repository.TicketRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final TicketRepository ticketRepository;

    public DataInitializer(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            tickets.add(new Ticket(
                null, 
                "Usuario" + i, 
                LocalDateTime.now(), 
                LocalDateTime.now(), 
                i % 2 == 0 ? Status.CERRADO : Status.ABIERTO 
            ));
        }
        ticketRepository.saveAll(tickets);
    }
}
