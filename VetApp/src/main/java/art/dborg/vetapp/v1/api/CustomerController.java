package art.dborg.vetapp.v1.api;

import art.dborg.vetapp.v1.business.abstracts.CustomerService;
import art.dborg.vetapp.v1.entities.Animal;
import art.dborg.vetapp.v1.entities.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public Customer save(@RequestBody Customer customer){
        return customerService.save(customer);
    }
}
