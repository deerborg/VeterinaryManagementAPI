package art.dborg.vetapp.v1.service.abstracts;

import art.dborg.vetapp.v1.core.result.ResultData;
import art.dborg.vetapp.v1.dto.request.animal.AnimalSaveRequest;
import art.dborg.vetapp.v1.dto.request.animal.AnimalUpdateRequest;
import art.dborg.vetapp.v1.dto.response.animal.AnimalGetAllResponse;
import art.dborg.vetapp.v1.dto.response.animal.AnimalResponse;

import java.util.List;


public interface AnimalService {

    ResultData<AnimalResponse> addAnimal(AnimalSaveRequest animal);

    ResultData<AnimalResponse> updateAnimal(AnimalUpdateRequest animal);

    ResultData<AnimalGetAllResponse> getAnimalById(long id);

    ResultData<List<AnimalGetAllResponse>> getCustomerById(long animalCustomerId);

    ResultData<List<AnimalGetAllResponse>> getAnimalByName(String name);

    ResultData<List<AnimalGetAllResponse>> getCustomerByName(String name);

    boolean delete(long id);
}