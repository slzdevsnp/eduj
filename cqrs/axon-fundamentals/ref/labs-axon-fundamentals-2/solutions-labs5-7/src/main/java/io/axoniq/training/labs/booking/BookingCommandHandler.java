package io.axoniq.training.labs.booking;

import io.axoniq.training.labs.booking.coreapi.PlaceBookingCommand;
import io.axoniq.training.labs.booking.coreapi.BookingPlacedEvent;
import io.axoniq.training.labs.booking.coreapi.ConfirmBookingCommand;
import io.axoniq.training.labs.booking.coreapi.RejectBookingCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookingCommandHandler {

    private static final Logger logger = LoggerFactory.getLogger(BookingCommandHandler.class);

    private final EventGateway eventGateway;

    public BookingCommandHandler(EventGateway eventGateway) {
        this.eventGateway = eventGateway;
    }

    @CommandHandler
    public void handle(PlaceBookingCommand command) {
        logger.info("PlaceBookingCommand for [{}], from gift card [{}] for booking [{}].",
                    command.getGiftCardAmount(), command.getCardId(), command.getBookingId());
        eventGateway.publish(new BookingPlacedEvent(
                command.getBookingId(), command.getCardId(), command.getGiftCardAmount(), command.isPartialPayment()
        ));
    }

    @CommandHandler
    public void handle(ConfirmBookingCommand command) {
        logger.info(
                "Booking confirmed for [{}] from gift card [{}] for booking [{}].",
                command.getGiftCardAmount(), command.getCardId(), command.getBookingId()
        );
    }

    @CommandHandler
    public void handle(RejectBookingCommand command) {
        logger.info(
                "Booking rejected from gift card [{}] for booking [{}], with the following reason:\n{}",
                command.getCardId(), command.getBookingId(), command.getReason()
        );
    }
}
