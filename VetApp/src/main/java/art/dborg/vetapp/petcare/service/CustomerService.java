package art.dborg.vetapp.petcare.service;

import art.dborg.vetapp.common.result.ResultData;
import art.dborg.vetapp.petcare.model.dto.customer.CustomerSaveRequest;
import art.dborg.vetapp.petcare.model.dto.customer.CustomerUpdateRequest;
import art.dborg.vetapp.petcare.model.dto.animal.AnimalResponse;
import art.dborg.vetapp.petcare.model.dto.customer.CustomersResponse;
import art.dborg.vetapp.petcare.model.dto.customer.CustomerOnlyIdResponse;
import art.dborg.vetapp.petcare.model.dto.customer.CustomerResponse;

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
