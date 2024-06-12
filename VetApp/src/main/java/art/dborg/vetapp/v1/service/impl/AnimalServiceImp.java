package art.dborg.vetapp.v1.service.impl;

import art.dborg.vetapp.v1.core.config.modelMapper.ModelMapperService;
import art.dborg.vetapp.v1.core.result.ResultData;
import art.dborg.vetapp.v1.core.utilities.ResultHelper;
import art.dborg.vetapp.v1.dto.request.animal.AnimalNameUpdateRequest;
import art.dborg.vetapp.v1.dto.request.animal.AnimalSaveRequest;
import art.dborg.vetapp.v1.dto.request.animal.AnimalUpdateRequest;
import art.dborg.vetapp.v1.dto.response.animal.AnimalsResponse;
import art.dborg.vetapp.v1.dto.response.animal.AnimalListResponse;
import art.dborg.vetapp.v1.dto.response.animal.AnimalOnlyIdResponse;
import art.dborg.vetapp.v1.dto.response.animal.AnimalResponse;
import art.dborg.vetapp.v1.service.interfaces.AnimalService;
import art.dborg.vetapp.v1.core.exception.ForUpdateNotFoundIdException;
import art.dborg.vetapp.v1.core.exception.NotFoundObjectRequest;
import art.dborg.vetapp.v1.core.exception.NotFoundCustomerException;
import art.dborg.vetapp.v1.core.exception.NotFoundException;
import art.dborg.vetapp.v1.core.utilities.Message;
import art.dborg.vetapp.v1.dao.CustomerRepository;
import art.dborg.vetapp.v1.entities.Animal;
import art.dborg.vetapp.v1.dao.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AnimalServiceImp implements AnimalService {
    private final AnimalRepository animalRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapperService mapperService;


    @Override
    public ResultData<AnimalResponse> addAnimal(AnimalSaveRequest animal) {
        checkCustomerId(mapperService.forRequest().map(animal,Animal.class));
        animal.setAge(LocalDate.now().getYear() - animal.getDateOfBirth().getYear());
        return ResultHelper.CREATED(mapperService.forResponse().map(animalRepository.save(mapperService.forRequest().map(animal,Animal.class)),AnimalResponse.class));
    }


    @Override
    public ResultData<AnimalResponse> updateAnimal(AnimalUpdateRequest animal) {
        animalRepository.findById(animal.getId()).orElseThrow(()-> new ForUpdateNotFoundIdException(Message.UPDATE_NOT_FOUND_ID));
        checkCustomerId(mapperService.forRequest().map(animal,Animal.class));
        animal.setAge(LocalDate.now().getYear() - animal.getDateOfBirth().getYear());
        return ResultHelper.CREATED(mapperService.forResponse().map(animalRepository.save(mapperService.forRequest().map(animal,Animal.class)),AnimalResponse.class));
    }


    @Override
    public ResultData<AnimalsResponse> getAnimalById(long id) {
        return ResultHelper.OK(mapperService.forResponse().map(animalRepository.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND_ID)), AnimalsResponse.class));
    }


    @Override
    public ResultData<List<AnimalsResponse>> getCustomerById(long animalCustomerId) {
        if(animalRepository.findByCustomer_Id(animalCustomerId).isEmpty()){
            throw new NotFoundCustomerException(Message.NOT_FOUND_CUSTOMER);
        }
        return ResultHelper.OK(animalRepository.findByCustomer_Id(animalCustomerId).stream().map(animal -> mapperService.forResponse().map(animal, AnimalsResponse.class)).collect(Collectors.toList()));
    }


    @Override
    public ResultData<List<AnimalsResponse>> getAnimalByName(String name) { // Section 13 - filter by name
        if(animalRepository.findByName(name).isEmpty()){
            throw new NotFoundObjectRequest(Message.NOT_FOUND);
        }
        return ResultHelper.OK(animalRepository.findByName(name).stream().map(animal -> mapperService.forResponse().map(animal, AnimalsResponse.class)).collect(Collectors.toList()));
    }

    @Override
    public ResultData<List<AnimalsResponse>> getCustomerByName(String name) {
        if(animalRepository.findByCustomerName(name).isEmpty()){
            throw new NotFoundObjectRequest(Message.NOT_FOUND);
        }
        return ResultHelper.OK(animalRepository.findByCustomerName(name).stream().map(animal -> mapperService.forResponse().map(animal, AnimalsResponse.class)).collect(Collectors.toList()));
    }


    @Override
    public boolean delete(long id) {
       animalRepository.deleteById(animalRepository.findById(id).orElseThrow(()-> new NotFoundException(Message.NOT_FOUND_ID)).getId());
       return true;
    }

    @Override
    public ResultData<List<AnimalListResponse>> getAnimalList() {
        return ResultHelper.OK(animalRepository.findAll().stream().map(animal -> mapperService.forResponse().map(animal,AnimalListResponse.class)).collect(Collectors.toList()));
    }

    @Override
    public ResultData<AnimalListResponse> updateByAnimalName(AnimalNameUpdateRequest animal) {
        Animal updateAnimal = animalRepository.findById(animal.getId()).orElse(null);
        updateAnimal.setName(animal.getName());
        Animal savedAnimal = animalRepository.save(updateAnimal);
        AnimalListResponse animalListResponse = mapperService.forResponse().map(savedAnimal,AnimalListResponse.class);
        return ResultHelper.OK(animalListResponse);
    }

    @Override
    public ResultData<List<AnimalOnlyIdResponse>> getAllIdByAnimals() {
        return ResultHelper.OK(animalRepository.findAll().stream().map(a -> mapperService.forResponse().map(a,AnimalOnlyIdResponse.class)).collect(Collectors.toList()));
    }

    public void checkCustomerId(Animal animal){
        if (customerRepository.findById(animal.getCustomer().getId()).isEmpty()) {
            throw new NotFoundCustomerException(Message.NOT_FOUND_CUSTOMER);
        }
    }
}
