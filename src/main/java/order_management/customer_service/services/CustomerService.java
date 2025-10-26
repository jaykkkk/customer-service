package order_management.customer_service.services;


import order_management.customer_service.dto.CustomerRequestDTO;
import order_management.customer_service.dto.CustomerResponseDTO;

public interface CustomerService {

    Long createCustomer(CustomerRequestDTO customerRequestDTO);

    void updateCustomer(Long id, CustomerRequestDTO customerRequestDTO);

    CustomerResponseDTO getCustomerById(Long id);

    void deleteCustomerById(Long id);
}
