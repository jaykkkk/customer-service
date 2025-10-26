package order_management.customer_service.controller;


import order_management.customer_service.dto.CustomerRequestDTO;
import order_management.customer_service.dto.CustomerResponseDTO;
import order_management.customer_service.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {


    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Endpoint methods would go here (e.g., createCustomer, getCustomerById, etc.)
    @PostMapping
    public ResponseEntity<Long> createCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) {

        return ResponseEntity.status(201).body(customerService.createCustomer(customerRequestDTO));
    }
    @PutMapping
    public ResponseEntity<String> updateCustomer(@RequestParam Long id, @RequestBody CustomerRequestDTO customerRequestDTO) {
        customerService.updateCustomer(id, customerRequestDTO);
        return ResponseEntity.ok("Customer updated successfully");
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable("id") Long id) {
        // Implementation for fetching customer by ID would go here
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        // Implementation for deleting customer by ID would go here
        customerService.deleteCustomerById(id);
        return ResponseEntity.ok("Customer deleted successfully");
    }

}
