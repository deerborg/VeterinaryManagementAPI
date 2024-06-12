package art.dborg.vetapp.petcare.model.dto.customer;

import art.dborg.vetapp.petcare.model.entity.Animal;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class CustomerListResponse {
    List<Animal> animalList; // List of animals associated with the customer
}
