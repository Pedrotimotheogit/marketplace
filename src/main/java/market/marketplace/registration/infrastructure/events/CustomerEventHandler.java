package market.marketplace.registration.infrastructure.events;

import market.marketplace.registration.infrastructure.persistence.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class CustomerEventHandler {
    Logger logger = LoggerFactory.getLogger(CustomerEventHandler.class);

    @HandleAfterCreate
    public void handleAfterCreate(Customer customer) {
        logger.warn("handleAfterCreate");
    }

    @HandleAfterDelete
    public void handleAfterDelete(Customer customer) {
        logger.warn("handleAfterDelete");
    }

    @HandleAfterSave
    public void handleAfterSave(Customer customer) {
        logger.warn("handleAfterSave");
    }
}
