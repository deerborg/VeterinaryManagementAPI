package art.dborg.vetapp.v1.api;

import art.dborg.vetapp.v1.business.abstracts.VaccineService;
import art.dborg.vetapp.v1.core.result.Result;
import art.dborg.vetapp.v1.core.result.ResultData;
import art.dborg.vetapp.v1.core.config.modelMapper.ModelMapperService;
import art.dborg.vetapp.v1.core.utilities.ResultHelper;
import art.dborg.vetapp.v1.dto.request.vaccine.VaccineSaveRequest;
import art.dborg.vetapp.v1.dto.request.vaccine.VaccineUpdateRequest;
import art.dborg.vetapp.v1.dto.response.vaccine.VaccineGetAllResponse;
import art.dborg.vetapp.v1.dto.response.vaccine.VaccineResponse;
import art.dborg.vetapp.v1.entities.Vaccine;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller class for managing vaccines.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/vaccines")
public class VaccineController {

    private final VaccineService vaccineService;
    private final ModelMapperService mapperService;

    /**
     * Adds a new vaccine.
     *
     * @param vaccineSaveRequest The request object containing vaccine data.
     * @return ResultData containing the response object with saved vaccine data.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<VaccineResponse> addVaccine(@Valid @RequestBody VaccineSaveRequest vaccineSaveRequest) {
        // Add a new vaccine and map the result to a response object.
        return ResultHelper.CREATED(mapperService.forResponse().map(vaccineService.addVaccine(mapperService.forRequest().map(vaccineSaveRequest, Vaccine.class)), VaccineResponse.class));
    }

    /**
     * Updates an existing vaccine.
     *
     * @param vaccineUpdateRequest The request object containing updated vaccine data.
     * @return ResultData containing the response object with updated vaccine data.
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> update(@Valid @RequestBody VaccineUpdateRequest vaccineUpdateRequest) {
        // Update an existing vaccine and map the result to a response object.
        return ResultHelper.OK(mapperService.forResponse().map(vaccineService.update(mapperService.forRequest().map(vaccineUpdateRequest, Vaccine.class)), VaccineResponse.class));
    }

    /**
     * Updates a vaccine forcefully.
     *
     * This endpoint updates a vaccine in the system even if some fields might violate business rules.
     * It is intended for special cases where updates need to be made urgently or with less strict validation.
     *
     * @param vaccineUpdateRequest The request body containing the details of the vaccine update.
     * @return ResultData containing the response of the update operation.
     */
    @PutMapping("/force-update")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> forceUpdate(@Valid @RequestBody VaccineUpdateRequest vaccineUpdateRequest) {
        return ResultHelper.RESOLVE(mapperService.forResponse().map(vaccineService.forceUpdate(mapperService.forRequest().map(vaccineUpdateRequest, Vaccine.class)), VaccineResponse.class));
    }

    /**
     * Retrieves a vaccine by its ID.
     *
     * @param id The ID of the vaccine to retrieve.
     * @return ResultData containing the response object with the vaccine data.
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineGetAllResponse> getId(@PathVariable long id) {
        // Retrieve a vaccine by its ID and map the result to a response object.
        return ResultHelper.OK(mapperService.forResponse().map(vaccineService.getId(id), VaccineGetAllResponse.class));
    }

    /**
     * Deletes a vaccine by its ID.
     *
     * @param id The ID of the vaccine to delete.
     * @return Result indicating the success of the deletion operation.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {
        // Delete a vaccine by its ID.
        return ResultHelper.DELETE(vaccineService.delete(id));
    }

    /**
     * Retrieves a list of vaccines associated with a specific animal.
     *
     * @param id The ID of the animal.
     * @return ResultData containing the response object with the list of vaccines.
     */
    @GetMapping("/animal/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineGetAllResponse>> getAnimalVaccineList(@PathVariable("id") long id) {
        // Retrieve vaccines associated with a specific animal and map the result to response objects.
        return ResultHelper.OK(vaccineService.getAnimalVaccineList(id).stream().map(vaccine -> mapperService.forResponse().map(vaccine, VaccineGetAllResponse.class)).collect(Collectors.toList()));
    }

    /**
     * Retrieves a list of vaccines filtered by end date.
     *
     * @param endDate The end date to filter vaccines.
     * @return ResultData containing the response object with the list of filtered vaccines.
     */
    @GetMapping("/find-date") // 1
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineGetAllResponse>> getFilterByStartAndEndDate(@RequestParam("firstDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate firsDate, @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        // Retrieve a list of vaccines filtered by end date and map the result to response objects.
        return ResultHelper.OK(vaccineService.getFilterByStartAndEndDate(firsDate, endDate).stream().map(vaccine -> mapperService.forResponse().map(vaccine, VaccineGetAllResponse.class)).collect(Collectors.toList()));
    }
}