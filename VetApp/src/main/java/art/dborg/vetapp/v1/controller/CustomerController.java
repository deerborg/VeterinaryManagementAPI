package art.dborg.vetapp.v1.controller;

import art.dborg.vetapp.v1.dto.response.customer.CustomersResponse;
import art.dborg.vetapp.v1.dto.response.customer.CustomerOnlyIdResponse;
import art.dborg.vetapp.v1.service.interfaces.CustomerService;
import art.dborg.vetapp.v1.core.result.Result;
import art.dborg.vetapp.v1.core.result.ResultData;
import art.dborg.vetapp.v1.core.utilities.ResultHelper;
import art.dborg.vetapp.v1.dto.request.customer.CustomerSaveRequest;
import art.dborg.vetapp.v1.dto.request.customer.CustomerUpdateRequest;
import art.dborg.vetapp.v1.dto.response.animal.AnimalResponse;
import art.dborg.vetapp.v1.dto.response.customer.CustomerResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CustomerResponse> save(@Valid @RequestBody CustomerSaveRequest customerSaveRequest){
        return customerService.addCustomer(customerSaveRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> getId(@PathVariable("id") long id){
        return customerService.getCustomerById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomersResponse> update(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest){
        return customerService.updateCustomer(customerUpdateRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id){
        return ResultHelper.DELETE(customerService.delete(id));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<CustomerResponse>> getByCustomerName(@RequestParam String name) {
        return customerService.getByCustomerName(name);
    }

    @GetMapping("/animal-list/{id}")
    public ResultData<List<AnimalResponse>> getByAnimalList(@PathVariable("id") long id){
        return customerService.getByAnimalList(id);
    }
    @GetMapping("/all-id")
    public ResultData<List<CustomerOnlyIdResponse>> getAllId(){
        return customerService.getOnlyId();
    }
    @GetMapping("/all-customer")
    public ResultData<List<CustomersResponse>> getAllCustomer(){
        return customerService.getAllCustomers();
    }
    @DeleteMapping("/name/{name}")
    public Result deleteByName(@PathVariable String name){
        return ResultHelper.DELETE(customerService.deleteByName(name));
    }
}
