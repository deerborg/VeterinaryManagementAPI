package art.dborg.vetapp.petcare.service;

import art.dborg.vetapp.common.result.ResultData;
import art.dborg.vetapp.petcare.model.dto.animal.AnimalNameUpdateRequest;
import art.dborg.vetapp.petcare.model.dto.animal.AnimalSaveRequest;
import art.dborg.vetapp.petcare.model.dto.animal.AnimalUpdateRequest;
import art.dborg.vetapp.petcare.model.dto.animal.AnimalsResponse;
import art.dborg.vetapp.petcare.model.dto.animal.AnimalListResponse;
import art.dborg.vetapp.petcare.model.dto.animal.AnimalOnlyIdResponse;
import art.dborg.vetapp.petcare.model.dto.animal.AnimalResponse;

import java.util.List;


public interface AnimalService {

    ResultData<AnimalResponse> addAnimal(AnimalSaveRequest animal);

    ResultData<AnimalResponse> updateAnimal(AnimalUpdateRequest animal);

    ResultData<AnimalsResponse> getAnimalById(long id);

    ResultData<List<AnimalsResponse>> getCustomerById(long animalCustomerId);

    ResultData<List<AnimalsResponse>> getAnimalByName(String name);

    ResultData<List<AnimalsResponse>> getCustomerByName(String name);

    ResultData<List<AnimalListResponse>> getAnimalList();

    ResultData<AnimalListResponse> updateByAnimalName(AnimalNameUpdateRequest animal);

    boolean delete(long id);

    ResultData<List<AnimalOnlyIdResponse>> getAllIdByAnimals();
}
