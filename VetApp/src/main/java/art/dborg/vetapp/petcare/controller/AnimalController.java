package art.dborg.vetapp.petcare.controller;

import art.dborg.vetapp.petcare.model.dto.animal.AnimalNameUpdateRequest;
import art.dborg.vetapp.petcare.model.dto.animal.AnimalListResponse;
import art.dborg.vetapp.petcare.model.dto.animal.AnimalOnlyIdResponse;
import art.dborg.vetapp.petcare.service.AnimalService;
import art.dborg.vetapp.common.result.Result;
import art.dborg.vetapp.common.result.ResultData;
import art.dborg.vetapp.common.utilities.ResultHelper;
import art.dborg.vetapp.petcare.model.dto.animal.AnimalSaveRequest;
import art.dborg.vetapp.petcare.model.dto.animal.AnimalUpdateRequest;
import art.dborg.vetapp.petcare.model.dto.animal.AnimalsResponse;
import art.dborg.vetapp.petcare.model.dto.animal.AnimalResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalsResponse> getAnimalById(@PathVariable("id") long id) {
        return animalService.getAnimalById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> addAnimal(@Valid @RequestBody() AnimalSaveRequest animalSaveRequest) {
        return animalService.addAnimal(animalSaveRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> updateAnimal(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest) {
       return animalService.updateAnimal(animalUpdateRequest);
    }

    @GetMapping("/customer-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalsResponse>> getCustomerById(@PathVariable("id") long id) {
        return animalService.getCustomerById(id);
    }


    @GetMapping("/name")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalsResponse>> getAnimalByName(@RequestParam String name) {
        return animalService.getAnimalByName(name);
    }

    @GetMapping("/customer-name")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalsResponse>> getCustomerByName(@RequestParam String name) {
        return animalService.getCustomerByName(name);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable long id){
        return ResultHelper.DELETE(animalService.delete(id));
    }

    @GetMapping("/animals")
    public ResultData<List<AnimalListResponse>> getAllAnimal(){
        return animalService.getAnimalList();
    }
    @PutMapping("/update-name")
    public ResultData<AnimalListResponse> updateByAnimalName(@RequestBody AnimalNameUpdateRequest animal){
        return animalService.updateByAnimalName(animal);
    }
    @GetMapping("/animals-id")
    public ResultData<List<AnimalOnlyIdResponse>> getAllAnimalsById(){
        return animalService.getAllIdByAnimals();
    }
}
