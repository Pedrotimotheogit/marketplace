package market.marketplace.registration.infrastructure.persistence.repository;

import market.marketplace.registration.domain.Customer;
import market.marketplace.registration.domain.CustomerId;
import market.marketplace.registration.domain.CustomerRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Repository
public class JpaCustomerRepository implements CustomerRepository {
    private final CustomerEntityRepository customerEntityRepository;

    public JpaCustomerRepository(CustomerEntityRepository customerEntityRepository) {
        this.customerEntityRepository = customerEntityRepository;
    }

    // Save entity customer
    @Override
    public Customer save(Customer customer) {
        var entity = mapper(customer);
        customerEntityRepository.save(entity);
        return customer;
    }

    @Override
    public List<Customer> findAll() {

        var interable = customerEntityRepository.findAll();

        return StreamSupport.stream(interable.spliterator(),false)
                .map(JpaCustomerRepository::mapper).toList();

    }

    //Customer from domain to Entity
    public static market.marketplace.registration.infrastructure.persistence.entity.Customer mapper(Customer customer) {
        var entity = new market.marketplace.registration.infrastructure.persistence.entity.Customer();

        entity.setId(customer.getId().id());
        entity.setFirstName(customer.getName());
        entity.setEmail(customer.getEmail());
        return entity;

    }

    private static Customer mapper(market.marketplace.registration.infrastructure.persistence.entity.Customer customer) {
        String fullName = Optional.ofNullable(customer.getLastName()).map(
                lastName -> customer.getFirstName() + " " + customer.getLastName()).orElseGet(customer::getFirstName);

        return new Customer(new CustomerId(customer.getId()), fullName, customer.getEmail());
    }
}
