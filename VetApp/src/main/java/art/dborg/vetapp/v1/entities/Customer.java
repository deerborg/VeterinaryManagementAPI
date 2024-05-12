package art.dborg.vetapp.v1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id",columnDefinition = "serial")
    private long id; // Unique identifier for the customer

    @Column(name = "customer_name")
    private String name; // Name of the customer

    @Column(name = "customer_phone",unique = true)
    private String phone; // Phone number of the customer (unique)

    @Column(name = "customer_mail",unique = true)
    @Email
    private String mail; // Email address of the customer (unique)

    @Column(name = "customer_address")
    private String address; // Address of the customer

    @Column(name = "customer_city")
    private String city; // City of the customer

    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE) // Section - 9 : Relationships between entities
    @JsonIgnore
    private List<Animal> animalList; // List of animals owned by the customer
}
