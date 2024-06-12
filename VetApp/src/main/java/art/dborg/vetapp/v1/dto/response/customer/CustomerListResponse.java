package art.dborg.vetapp.v1.dto.response.customer;

import art.dborg.vetapp.v1.entities.Animal;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class CustomerListResponse {
    List<Animal> animalList; // List of animals associated with the customer
}
