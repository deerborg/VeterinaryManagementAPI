package art.dborg.vetapp.v1.controller;

import art.dborg.vetapp.v1.dto.request.animal.AnimalNameUpdateRequest;
import art.dborg.vetapp.v1.dto.response.animal.AnimalListResponse;
import art.dborg.vetapp.v1.service.abstracts.AnimalService;
import art.dborg.vetapp.v1.core.result.Result;
import art.dborg.vetapp.v1.core.result.ResultData;
import art.dborg.vetapp.v1.core.config.modelMapper.ModelMapperService;
import art.dborg.vetapp.v1.core.utilities.ResultHelper;
import art.dborg.vetapp.v1.dto.request.animal.AnimalSaveRequest;
import art.dborg.vetapp.v1.dto.request.animal.AnimalUpdateRequest;
import art.dborg.vetapp.v1.dto.response.animal.AnimalGetAllResponse;
import art.dborg.vetapp.v1.dto.response.animal.AnimalResponse;
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
    public ResultData<AnimalGetAllResponse> getAnimalById(@PathVariable("id") long id) {
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

    @GetMapping("/by-customer-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalGetAllResponse>> getCustomerById(@PathVariable("id") long id) {
        return animalService.getCustomerById(id);
    }


    @GetMapping("/by-name")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalGetAllResponse>> getAnimalByName(@RequestParam String name) {
        return animalService.getAnimalByName(name);
    }

    @GetMapping("/by-customer-name")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalGetAllResponse>> getCustomerByName(@RequestParam String name) {
        return animalService.getCustomerByName(name);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable long id){
        return ResultHelper.DELETE(animalService.delete(id));
    }

    @GetMapping("/all-animals")
    public ResultData<List<AnimalListResponse>> getAllAnimal(){
        return animalService.getAnimalList();
    }
    @PutMapping("/update-name")
    public ResultData<AnimalListResponse> updateByAnimalName(@RequestBody AnimalNameUpdateRequest animal){
        return animalService.updateByAnimalName(animal);
    }
}
