package ticketsystem.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ticketsystem.application.service.TicketService;
import ticketsystem.config.Constants;
import ticketsystem.domain.model.Ticket;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createTicket(@Validated @RequestBody Ticket ticket) {
        Ticket created = ticketService.createTicket(ticket);
        return ResponseEntity.ok(Map.of(
                "status", 201,
                "data", created,
                "details", Constants.TICKET_CREATED
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateTicket(@PathVariable Long id, @Validated @RequestBody Ticket ticket) {
        Ticket updated = ticketService.updateTicket(id, ticket);
        return ResponseEntity.ok(Map.of(
                "status", 200,
                "data", updated,
                "details", Constants.TICKET_UPDATED
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        Map<String, Object> response = new HashMap<>();
        response.put("status", 200);
        response.put("data", Constants.TICKET_DELETED);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getTicket(@PathVariable Long id) {
        return ticketService.getTicketById(id)
                .map(ticket -> ResponseEntity.ok(Map.of(
                        "status", 200,
                        "data", ticket,
                        "details", ""
                )))
                .orElse(ResponseEntity.status(404).body(Map.of(
                        "status", 404,
                        "data", null,
                        "details", Constants.TICKET_NOT_FOUND
                )));
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllTickets(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        Page<Ticket> tickets = ticketService.getAllTickets(PageRequest.of(page, size));
        
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("totalPages", tickets.getTotalPages());
        response.put("currentPage", tickets.getNumber());
        response.put("status", 200);
        response.put("data", tickets.getContent());

        return ResponseEntity.ok(response);
}

}
