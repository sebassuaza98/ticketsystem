package ticketsystem.application.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ticketsystem.config.Constants;
import ticketsystem.domain.model.Ticket;
import ticketsystem.infrastructure.exception.CustomException;
import ticketsystem.infrastructure.repository.TicketRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service

public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket createTicket(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException(Constants.TICKET_NULL);
        }
        ticket.setFechaCreacion(LocalDateTime.now());
        return ticketRepository.save(ticket);
    }

    public Ticket updateTicket(Long id, Ticket updatedTicket) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new CustomException(Constants.TICKET_NOT_FOUND));
        ticket.setUsuario(updatedTicket.getUsuario());
        ticket.setStatus(updatedTicket.getStatus());
        ticket.setFechaActualizacion(LocalDateTime.now());
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new CustomException(Constants.TICKET_NOT_FOUND);
        }
        ticketRepository.deleteById(id);
    }
    

    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    public Page<Ticket> getAllTickets(Pageable pageable) {
        Pageable defaultPageable = pageable == null ? Pageable.ofSize(10) : pageable;
        return ticketRepository.findAll(defaultPageable);
    }
}
