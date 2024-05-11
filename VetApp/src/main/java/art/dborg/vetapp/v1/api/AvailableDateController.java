package art.dborg.vetapp.v1.api;

import art.dborg.vetapp.v1.business.abstracts.AvailableDateService;
import art.dborg.vetapp.v1.core.result.Result;
import art.dborg.vetapp.v1.core.result.ResultData;
import art.dborg.vetapp.v1.core.config.modelMapper.ModelMapperService;
import art.dborg.vetapp.v1.core.utilities.ResultHelper;
import art.dborg.vetapp.v1.dto.request.availableDate.AvailableDateSaveRequest;
import art.dborg.vetapp.v1.dto.request.availableDate.AvailableDateUpdateRequest;
import art.dborg.vetapp.v1.dto.response.availableDate.AvailableDateGetAllResponse;
import art.dborg.vetapp.v1.dto.response.availableDate.AvailableDateResponse;
import art.dborg.vetapp.v1.entities.AvailableDate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for managing available dates.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/available-dates")
public class AvailableDateController {
    private final AvailableDateService availableDateService;
    private final ModelMapperService modelMapperService;

    /**
     * Saves a new available date.
     * @param availableDateSaveRequest The request object containing available date data.
     * @return ResultData containing the response object with saved available date data.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AvailableDateResponse> save(@Valid @RequestBody AvailableDateSaveRequest availableDateSaveRequest){
        // Save a new available date and map the result to a response object.
        return ResultHelper.CREATED(modelMapperService.forResponse().map(availableDateService.save(modelMapperService.forRequest().map(availableDateSaveRequest, AvailableDate.class)),AvailableDateResponse.class));
    }

    /**
     * Updates an existing available date.
     * @param availableDateUpdateRequest The request object containing updated available date data.
     * @return ResultData containing the response object with updated available date data.
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> update(@Valid @RequestBody AvailableDateUpdateRequest availableDateUpdateRequest){
        // Update an existing available date and map the result to a response object.
        return ResultHelper.OK(modelMapperService.forResponse().map(availableDateService.update(modelMapperService.forRequest().map(availableDateUpdateRequest, AvailableDate.class)),AvailableDateResponse.class));
    }

    /**
     * Retrieves an available date by its ID.
     * @param id The ID of the available date to retrieve.
     * @return ResultData containing the response object with the available date data.
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateGetAllResponse> getId(@PathVariable("id") long id){
        // Retrieve an available date by its ID and map the result to a response object.
        return ResultHelper.OK(modelMapperService.forResponse().map(availableDateService.getId(id),AvailableDateGetAllResponse.class));
    }

    /**
     * Deletes an available date by its ID.
     * @param id The ID of the available date to delete.
     * @return Result indicating the success of the deletion operation.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id){
        // Delete an available date by its ID.
        return ResultHelper.DELETE(availableDateService.delete(id));
    }
}
