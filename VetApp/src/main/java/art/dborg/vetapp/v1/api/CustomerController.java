package art.dborg.vetapp.v1.api;

import art.dborg.vetapp.v1.business.abstracts.CustomerService;
import art.dborg.vetapp.v1.core.result.Result;
import art.dborg.vetapp.v1.core.result.ResultData;
import art.dborg.vetapp.v1.core.config.modelMapper.ModelMapperService;
import art.dborg.vetapp.v1.core.utilities.ResultHelper;
import art.dborg.vetapp.v1.dto.request.customer.CustomerSaveRequest;
import art.dborg.vetapp.v1.dto.request.customer.CustomerUpdateRequest;
import art.dborg.vetapp.v1.dto.response.animal.AnimalResponse;
import art.dborg.vetapp.v1.dto.response.customer.CustomerResponse;
import art.dborg.vetapp.v1.entities.Customer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller class for managing customers.
 */
@RestController
@RequestMapping("/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final ModelMapperService modelMapperService;

    /**
     * Saves a new customer.
     * @param customerSaveRequest The request object containing customer data.
     * @return ResultData containing the response object with saved customer data.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CustomerResponse> save(@Valid @RequestBody CustomerSaveRequest customerSaveRequest){
        // Save a new customer and map the result to a response object.
        return ResultHelper.CREATED(modelMapperService.forResponse().map(customerService.save(modelMapperService.forRequest().map(customerSaveRequest,Customer.class)),CustomerResponse.class));
    }

    /**
     * Retrieves a customer by its ID.
     * @param id The ID of the customer to retrieve.
     * @return ResultData containing the response object with the customer data.
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> getId(@PathVariable("id") long id){
        // Retrieve a customer by its ID and map the result to a response object.
        return ResultHelper.OK(modelMapperService.forResponse().map(customerService.getId(id),CustomerResponse.class));
    }

    /**
     * Updates an existing customer.
     * @param customerUpdateRequest The request object containing updated customer data.
     * @return ResultData containing the response object with updated customer data.
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> update(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest){
        // Update an existing customer and map the result to a response object.
        return ResultHelper.OK(modelMapperService.forResponse().map(customerService.update(modelMapperService.forRequest().map(customerUpdateRequest,Customer.class)),CustomerResponse.class));
    }

    /**
     * Deletes a customer by its ID.
     * @param id The ID of the customer to delete.
     * @return Result indicating the success of the deletion operation.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id){
        // Delete a customer by its ID.
        return ResultHelper.DELETE(customerService.delete(id));
    }

    /**
     * Retrieves customers by their names.
     * @param name The name of the customer to search for.
     * @return ResultData containing the response object with matched customer data.
     */
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<CustomerResponse>> getByCustomerName(@RequestParam String name) {
        // Retrieve customers by name and map the results to response objects.
        return ResultHelper.OK(customerService.getByCustomerName(name).stream().map(customer -> modelMapperService.forResponse().map(customer, CustomerResponse.class)).collect(Collectors.toList()));
    }

    /**
     * Retrieves customers by the ID of an associated animal.
     * @param id The ID of the associated animal.
     * @return ResultData containing the response object with matched customer data.
     */
    @GetMapping("/by-animal-list/{id}")
    public ResultData<List<AnimalResponse>> getByAnimalList(@PathVariable("id") long id){
        // Retrieve customers by associated animal ID and map the results to response objects.
        return ResultHelper.OK(customerService.getByAnimalList(id).stream().map(customer -> modelMapperService.forResponse().map(customer,AnimalResponse.class)).collect(Collectors.toList()));
    }
}
