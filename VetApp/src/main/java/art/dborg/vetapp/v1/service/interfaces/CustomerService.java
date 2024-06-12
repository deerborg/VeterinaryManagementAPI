package art.dborg.vetapp.v1.service.interfaces;

import art.dborg.vetapp.v1.core.result.ResultData;
import art.dborg.vetapp.v1.dto.request.customer.CustomerSaveRequest;
import art.dborg.vetapp.v1.dto.request.customer.CustomerUpdateRequest;
import art.dborg.vetapp.v1.dto.response.animal.AnimalResponse;
import art.dborg.vetapp.v1.dto.response.customer.CustomersResponse;
import art.dborg.vetapp.v1.dto.response.customer.CustomerOnlyIdResponse;
import art.dborg.vetapp.v1.dto.response.customer.CustomerResponse;

import java.util.List;


public interface CustomerService {
    ResultData<CustomerResponse> addCustomer(CustomerSaveRequest customer);

    ResultData<CustomersResponse> updateCustomer(CustomerUpdateRequest customer);

    ResultData<CustomerResponse> getCustomerById(long id);

    boolean delete(long id);

    ResultData<List<CustomerResponse>> getByCustomerName(String name);

    ResultData<List<AnimalResponse>> getByAnimalList(long id);

    ResultData <List<CustomerOnlyIdResponse>> getOnlyId();

    ResultData<List<CustomersResponse>> getAllCustomers();

    boolean deleteByName(String name);
}
