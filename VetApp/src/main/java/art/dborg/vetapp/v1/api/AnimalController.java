package art.dborg.vetapp.v1.api;

import art.dborg.vetapp.v1.business.abstracts.AnimalService;
import art.dborg.vetapp.v1.core.result.Result;
import art.dborg.vetapp.v1.core.result.ResultData;
import art.dborg.vetapp.v1.core.config.modelMapper.ModelMapperService;
import art.dborg.vetapp.v1.core.utilities.ResultHelper;
import art.dborg.vetapp.v1.dto.request.animal.AnimalSaveRequest;
import art.dborg.vetapp.v1.dto.request.animal.AnimalUpdateRequest;
import art.dborg.vetapp.v1.dto.response.animal.AnimalGetAllResponse;
import art.dborg.vetapp.v1.dto.response.animal.AnimalResponse;
import art.dborg.vetapp.v1.entities.Animal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller class for handling animal-related endpoints.
 */
@RestController
@RequestMapping("/v1/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;
    private final ModelMapperService modelMapperService;

    /**
     * Retrieve animal by its ID.
     *
     * @param id The ID of the animal to retrieve.
     * @return The response containing the animal details.
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalGetAllResponse> getId(@PathVariable("id") long id) {
        return ResultHelper.OK(modelMapperService.forResponse().map(animalService.getId(id),AnimalGetAllResponse.class));
    }

    /**
     * Save a new animal.
     *
     * @param animalSaveRequest The request containing the animal details to be saved.
     * @return The response containing the saved animal details.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody() AnimalSaveRequest animalSaveRequest) {
        return ResultHelper.CREATED(modelMapperService.forResponse().map(animalService.save(modelMapperService.forRequest().map(animalSaveRequest, Animal.class)), AnimalResponse.class));
    }

    /**
     * Update an existing animal.
     *
     * @param animalUpdateRequest The request containing the updated animal details.
     * @return The response containing the updated animal details.
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest) {
        animalService.update(modelMapperService.forRequest().map(animalUpdateRequest, Animal.class));
        return ResultHelper.OK(modelMapperService.forResponse().map(animalService.update(modelMapperService.forRequest().map(animalUpdateRequest, Animal.class)), AnimalResponse.class));
    }

    /**
     * Retrieve animals by customer ID.
     *
     * @param id The ID of the customer to retrieve animals for.
     * @return The list of animals associated with the customer.
     */
    @GetMapping("/by-customer-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<AnimalGetAllResponse> getCustomerId(@PathVariable("id") long id) {
        return animalService.getCustomerId(id).stream().map(animal -> modelMapperService.forResponse().map(animal, AnimalGetAllResponse.class)).collect(Collectors.toList());
    }

    /**
     * Retrieve animals by name.
     *
     * @param name The name of the animal to search for.
     * @return The list of animals matching the provided name.
     */
    @GetMapping("/by-name")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalGetAllResponse>> getAnimalByName(@RequestParam String name) {
        return ResultHelper.OK(animalService.getAnimalByName(name).stream().map(animal -> modelMapperService.forResponse().map(animal, AnimalGetAllResponse.class)).collect(Collectors.toList()));
    }

    /**
     * Retrieve animals by customer name.
     *
     * @param name The name of the customer to retrieve animals for.
     * @return The list of animals associated with the customer.
     */
    @GetMapping("/by-customer-name")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalGetAllResponse>> getByCustomerName(@RequestParam String name) {
        return ResultHelper.OK(animalService.getCustomerName(name).stream().map(animal -> modelMapperService.forResponse().map(animal, AnimalGetAllResponse.class)).collect(Collectors.toList()));
    }

    /**
     * Delete an animal by its ID.
     *
     * @param id The ID of the animal to delete.
     * @return The result of the delete operation.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable long id){
        return ResultHelper.DELETE(animalService.delete(id));
    }
}
