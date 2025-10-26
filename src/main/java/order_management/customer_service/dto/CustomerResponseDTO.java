package order_management.customer_service.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponseDTO implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
}
