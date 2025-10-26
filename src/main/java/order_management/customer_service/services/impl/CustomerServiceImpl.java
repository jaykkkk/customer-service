package order_management.customer_service.services.impl;


import order_management.customer_service.dto.CustomerRequestDTO;
import order_management.customer_service.dto.CustomerResponseDTO;
import order_management.customer_service.model.Customer;
import order_management.customer_service.repo.CustomerRepository;
import order_management.customer_service.services.CustomerService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public Long createCustomer(CustomerRequestDTO customerRequestDTO) {

        Customer customer= Customer.builder().
                name(customerRequestDTO.getName()).
                email(customerRequestDTO.getEmail()).
                phoneNumber(customerRequestDTO.getPhoneNumber()).
                address(customerRequestDTO.getAddress()).
                build();
        Customer customerSaved = customerRepository.save(customer);
        return customerSaved.getId();
    }

    @CachePut(value = "customers", key = "#id")
    @Override
    public void updateCustomer(Long id, CustomerRequestDTO customerRequestDTO) {
       Customer customer= customerRepository.findById(id).orElseThrow(()->new RuntimeException("Customer not found"));
         customer.setName(customerRequestDTO.getName());
            customer.setEmail(customerRequestDTO.getEmail());
            customer.setPhoneNumber(customerRequestDTO.getPhoneNumber());
            customer.setAddress(customerRequestDTO.getAddress());
            customerRepository.save(customer);
    }

    @Cacheable(value = "customers", key = "#id")
    @Override
    public CustomerResponseDTO getCustomerById(Long id) {
        Customer customer= customerRepository.findById(id).orElseThrow(()->new RuntimeException("Customer not found"));
        return CustomerResponseDTO.builder().
                name(customer.getName()).
                email(customer.getEmail()).
                phoneNumber(customer.getPhoneNumber()).
                address(customer.getAddress()).
                build();


    }
    @CacheEvict(value = "customers", key = "#id")
    @Override
    public void deleteCustomerById(Long id) {
        if(customerRepository.existsById(id)){
            customerRepository.deleteById(id);
        }else {
            throw new RuntimeException("Customer not found");
        }

    }
}
