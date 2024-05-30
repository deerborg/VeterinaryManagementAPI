package art.dborg.vetapp.v1.service.abstracts;

import art.dborg.vetapp.v1.core.result.ResultData;
import art.dborg.vetapp.v1.dto.request.customer.CustomerSaveRequest;
import art.dborg.vetapp.v1.dto.request.customer.CustomerUpdateRequest;
import art.dborg.vetapp.v1.dto.response.animal.AnimalGetAllResponse;
import art.dborg.vetapp.v1.dto.response.animal.AnimalResponse;
import art.dborg.vetapp.v1.dto.response.customer.CustomerAllResponse;
import art.dborg.vetapp.v1.dto.response.customer.CustomerOnlyIdResponse;
import art.dborg.vetapp.v1.dto.response.customer.CustomerResponse;
import art.dborg.vetapp.v1.entities.Animal;
import art.dborg.vetapp.v1.entities.Customer;

import java.util.List;


public interface CustomerService {
    ResultData<CustomerResponse> addCustomer(CustomerSaveRequest customer);

    ResultData<CustomerAllResponse> updateCustomer(CustomerUpdateRequest customer);

    ResultData<CustomerResponse> getCustomerById(long id);

    boolean delete(long id);

    ResultData<List<CustomerResponse>> getByCustomerName(String name);

    ResultData<List<AnimalResponse>> getByAnimalList(long id);

    ResultData <List<CustomerOnlyIdResponse>> getOnlyId();

    ResultData<List<CustomerAllResponse>> getAllCustomers();

    boolean deleteByName(String name);
}
