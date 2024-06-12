package art.dborg.vetapp.petcare.service.impl;

import art.dborg.vetapp.common.config.modelMapper.ModelMapperService;
import art.dborg.vetapp.common.exception.*;
import art.dborg.vetapp.common.result.ResultData;
import art.dborg.vetapp.common.utilities.ResultHelper;
import art.dborg.vetapp.petcare.model.dto.customer.CustomerSaveRequest;
import art.dborg.vetapp.petcare.model.dto.customer.CustomerUpdateRequest;
import art.dborg.vetapp.petcare.model.dto.animal.AnimalResponse;
import art.dborg.vetapp.petcare.model.dto.customer.CustomersResponse;
import art.dborg.vetapp.petcare.model.dto.customer.CustomerOnlyIdResponse;
import art.dborg.vetapp.petcare.model.dto.customer.CustomerResponse;
import art.dborg.vetapp.petcare.service.CustomerService;
import art.dborg.vetapp.common.utilities.Message;
import art.dborg.vetapp.petcare.repository.CustomerRepository;
import art.dborg.vetapp.petcare.model.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImp implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapperService mapperService;

    @Override
    public ResultData<CustomerResponse> addCustomer(CustomerSaveRequest customer) {
        if (customerRepository.existsByMail(customer.getMail()) || customerRepository.existsByPhone(customer.getPhone())) {
            throw new NotUniqueValues(Message.NOT_UNIQUE);
        }
        return ResultHelper.CREATED(mapperService.forResponse().map(customerRepository.save(mapperService.forRequest().map(customer, Customer.class)), CustomerResponse.class));
    }

    @Override
    public ResultData<CustomersResponse> updateCustomer(CustomerUpdateRequest customer) {
        customerRepository.findById(customer.getId()).orElseThrow(() -> new ForUpdateNotFoundIdException(Message.UPDATE_NOT_FOUND_ID));
        return ResultHelper.OK(mapperService.forResponse().map(customerRepository.save(mapperService.forRequest().map(customer, Customer.class)), CustomersResponse.class));
    }

    @Override
    public ResultData<CustomerResponse> getCustomerById(long id) {
        return ResultHelper.OK(mapperService.forResponse().map(customerRepository.findById(id), CustomerResponse.class));
    }

    @Override
    public boolean delete(long id) {
        customerRepository.delete(customerRepository.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND_ID)));
        return true;
    }

    @Override
    public ResultData<List<CustomerResponse>> getByCustomerName(String name) {
        if (customerRepository.findByName(name).isEmpty()) {
            throw new NotFoundObjectRequest(Message.NOT_FOUND);
        }
        return ResultHelper.OK(customerRepository.findByName(name).stream().map(customer -> mapperService.forResponse().map(customer, CustomerResponse.class)).collect(Collectors.toList()));
    }

    @Override
    public ResultData<List<AnimalResponse>> getByAnimalList(long id) {
        if (customerRepository.findById(id).isEmpty()) {
            throw new NotFoundCustomerException(Message.NOT_FOUND_CUSTOMER);
        }
        if (customerRepository.findById(id).orElseThrow().getAnimalList().isEmpty()) {
            throw new NotFoundObjectRequest(Message.NOT_FOUND);
        }
        return ResultHelper.OK(customerRepository.findById(id).orElseThrow().getAnimalList().stream().map(animal -> mapperService.forResponse().map(animal, AnimalResponse.class)).collect(Collectors.toList()));
    }

    @Override
    public ResultData<List<CustomerOnlyIdResponse>> getOnlyId() {
        return ResultHelper.OK(customerRepository.findAll().stream().map(customer -> mapperService.forResponse().map(customer, CustomerOnlyIdResponse.class)).collect(Collectors.toList()));
    }

    @Override
    public ResultData<List<CustomersResponse>> getAllCustomers() {
        return ResultHelper.OK(customerRepository.findAll().stream().map(customer -> mapperService.forResponse().map(customer, CustomersResponse.class)).collect(Collectors.toList()));
    }

    @Override
    public boolean deleteByName(String name) {
        customerRepository.deleteById(customerRepository.findCustomerByName(name).getId());
        return true;
    }
}
