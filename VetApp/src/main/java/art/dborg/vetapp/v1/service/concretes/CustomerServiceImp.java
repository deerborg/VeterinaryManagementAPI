package art.dborg.vetapp.v1.service.concretes;

import art.dborg.vetapp.v1.core.config.modelMapper.ModelMapperService;
import art.dborg.vetapp.v1.core.exception.NullPointerException;
import art.dborg.vetapp.v1.core.result.ResultData;
import art.dborg.vetapp.v1.core.utilities.ResultHelper;
import art.dborg.vetapp.v1.dto.request.customer.CustomerSaveRequest;
import art.dborg.vetapp.v1.dto.request.customer.CustomerUpdateRequest;
import art.dborg.vetapp.v1.dto.response.animal.AnimalResponse;
import art.dborg.vetapp.v1.dto.response.customer.CustomerOnlyIdResponse;
import art.dborg.vetapp.v1.dto.response.customer.CustomerResponse;
import art.dborg.vetapp.v1.service.abstracts.CustomerService;
import art.dborg.vetapp.v1.core.exception.*;
import art.dborg.vetapp.v1.core.utilities.Message;
import art.dborg.vetapp.v1.dao.CustomerRepository;
import art.dborg.vetapp.v1.entities.Animal;
import art.dborg.vetapp.v1.entities.Customer;
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
    public ResultData<CustomerResponse> updateCustomer(CustomerUpdateRequest customer) {
        customerRepository.findById(customer.getId()).orElseThrow(() -> new ForUpdateNotFoundIdException(Message.UPDATE_NOT_FOUND_ID));
        return ResultHelper.OK(mapperService.forResponse().map(customerRepository.save(mapperService.forRequest().map(customer, Customer.class)), CustomerResponse.class));
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
    public ResultData<List<CustomerResponse>> getAllCustomers() {
        return ResultHelper.OK(customerRepository.findAll().stream().map(customer -> mapperService.forResponse().map(customer, CustomerResponse.class)).collect(Collectors.toList()));
    }

    @Override
    public boolean deleteByName(String name) {
        customerRepository.deleteById(customerRepository.findCustomerByName(name).getId());
        return true;
    }
}
