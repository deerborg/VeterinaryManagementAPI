package art.dborg.vetapp.v1.controller;

import art.dborg.vetapp.v1.service.abstracts.CustomerService;
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
    public ResultData<CustomerResponse> update(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest){
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

    @GetMapping("/by-animal-list/{id}")
    public ResultData<List<AnimalResponse>> getByAnimalList(@PathVariable("id") long id){
        return customerService.getByAnimalList(id);
    }
}
