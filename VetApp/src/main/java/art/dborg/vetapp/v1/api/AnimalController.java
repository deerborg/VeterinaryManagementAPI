package art.dborg.vetapp.v1.api;

import art.dborg.vetapp.v1.business.abstracts.AnimalService;
import art.dborg.vetapp.v1.core.Result.ResultData;
import art.dborg.vetapp.v1.core.config.modelMapper.ModelMapperService;
import art.dborg.vetapp.v1.core.utilities.ResultHelper;
import art.dborg.vetapp.v1.dao.CustomerRepository;
import art.dborg.vetapp.v1.dto.request.animal.AnimalSaveRequest;
import art.dborg.vetapp.v1.dto.request.animal.AnimalUpdateRequest;
import art.dborg.vetapp.v1.dto.response.animal.AnimalResponse;
import art.dborg.vetapp.v1.entities.Animal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;
    private final ModelMapperService modelMapperService;
    private final CustomerRepository customerRepository;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> getId(@PathVariable("id") long id) {
        return ResultHelper.OK(modelMapperService.forResponse().map(animalService.getId(id), AnimalResponse.class));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalSaveRequest animalSaveRequest) {
        return ResultHelper.CREATED(modelMapperService.forResponse().map(animalService.save(modelMapperService.forRequest().map(animalSaveRequest,Animal.class)),AnimalResponse.class));
    }

    @PutMapping
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest){
        animalService.update(modelMapperService.forRequest().map(animalUpdateRequest,Animal.class));
        return ResultHelper.OK(modelMapperService.forResponse().map(animalService.update(modelMapperService.forRequest().map(animalUpdateRequest,Animal.class)),AnimalResponse.class));
    }

    @GetMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<AnimalResponse> getCustomerId(@PathVariable("id") long id) {
        return animalService.getCustomerId(id).stream().map(animal -> modelMapperService.forResponse().map(animal, AnimalResponse.class)).collect(Collectors.toList());
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AnimalResponse> getAnimalByName(@RequestParam String name) {
        return animalService.getAnimalByName(name).stream().map(animal -> modelMapperService.forResponse().map(animal, AnimalResponse.class)).collect(Collectors.toList());
    }

}
