package art.dborg.vetapp.v1.api;

import art.dborg.vetapp.v1.business.abstracts.CustomerService;
import art.dborg.vetapp.v1.core.Result.Result;
import art.dborg.vetapp.v1.core.Result.ResultData;
import art.dborg.vetapp.v1.core.config.modelMapper.ModelMapperService;
import art.dborg.vetapp.v1.core.utilities.ResultHelper;
import art.dborg.vetapp.v1.dto.request.customer.CustomerSaveRequest;
import art.dborg.vetapp.v1.dto.request.customer.CustomerUpdateRequest;
import art.dborg.vetapp.v1.dto.response.customer.CustomerResponse;
import art.dborg.vetapp.v1.entities.Animal;
import art.dborg.vetapp.v1.entities.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final ModelMapperService modelMapperService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CustomerResponse> save(@RequestBody CustomerSaveRequest customerSaveRequest){
        return ResultHelper.CREATED(modelMapperService.forResponse().map(customerService.save(modelMapperService.forRequest().map(customerSaveRequest,Customer.class)),CustomerResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> getId(@PathVariable("id") long id){
        return ResultHelper.OK(modelMapperService.forResponse().map(customerService.getId(id),CustomerResponse.class));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> update(@RequestBody CustomerUpdateRequest customerUpdateRequest){
        return ResultHelper.OK(modelMapperService.forResponse().map(customerService.update(modelMapperService.forRequest().map(customerUpdateRequest,Customer.class)),CustomerResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id){
        customerService.delete(id);
        return new Result(true,"Deleted","200");
    }
}
