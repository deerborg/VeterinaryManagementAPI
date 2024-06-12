package art.dborg.vetapp.petcare.controller;

import art.dborg.vetapp.petcare.model.dto.customer.CustomersResponse;
import art.dborg.vetapp.petcare.model.dto.customer.CustomerOnlyIdResponse;
import art.dborg.vetapp.petcare.service.CustomerService;
import art.dborg.vetapp.common.result.Result;
import art.dborg.vetapp.common.result.ResultData;
import art.dborg.vetapp.common.utilities.ResultHelper;
import art.dborg.vetapp.petcare.model.dto.customer.CustomerSaveRequest;
import art.dborg.vetapp.petcare.model.dto.customer.CustomerUpdateRequest;
import art.dborg.vetapp.petcare.model.dto.animal.AnimalResponse;
import art.dborg.vetapp.petcare.model.dto.customer.CustomerResponse;
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
