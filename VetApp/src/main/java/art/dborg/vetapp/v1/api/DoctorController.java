package art.dborg.vetapp.v1.api;

import art.dborg.vetapp.v1.business.abstracts.DoctorService;
import art.dborg.vetapp.v1.core.result.Result;
import art.dborg.vetapp.v1.core.result.ResultData;
import art.dborg.vetapp.v1.core.config.modelMapper.ModelMapperService;
import art.dborg.vetapp.v1.core.utilities.ResultHelper;
import art.dborg.vetapp.v1.dto.request.doctor.DoctorSaveRequest;
import art.dborg.vetapp.v1.dto.request.doctor.DoctorUpdateRequest;
import art.dborg.vetapp.v1.dto.response.doctor.DoctorResponse;
import art.dborg.vetapp.v1.entities.Doctor;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for managing doctors.
 */
@RestController
@RequestMapping("/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final ModelMapperService modelMapperService;
    private final DoctorService doctorService;

    /**
     * Saves a new doctor.
     * @param doctorSaveRequest The request object containing doctor data.
     * @return ResultData containing the response object with saved doctor data.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<DoctorResponse> save(@Valid @RequestBody DoctorSaveRequest doctorSaveRequest){
        // Save a new doctor and map the result to a response object.
        return ResultHelper.CREATED(modelMapperService.forResponse().map(doctorService.save(modelMapperService.forRequest().map(doctorSaveRequest,Doctor.class)),DoctorResponse.class));
    }

    /**
     * Updates an existing doctor.
     * @param doctorUpdateRequest The request object containing updated doctor data.
     * @return ResultData containing the response object with updated doctor data.
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> update(@Valid @RequestBody DoctorUpdateRequest doctorUpdateRequest){
        // Update an existing doctor and map the result to a response object.
        return ResultHelper.OK(modelMapperService.forResponse().map(doctorService.update(modelMapperService.forRequest().map(doctorUpdateRequest,Doctor.class)),DoctorResponse.class));
    }

    /**
     * Retrieves a doctor by its ID.
     * @param id The ID of the doctor to retrieve.
     * @return ResultData containing the response object with the doctor data.
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> getId(@PathVariable("id") long id){
        // Retrieve a doctor by its ID and map the result to a response object.
        return ResultHelper.OK(modelMapperService.forResponse().map(doctorService.getId(id),DoctorResponse.class));
    }

    /**
     * Deletes a doctor by its ID.
     * @param id The ID of the doctor to delete.
     * @return Result indicating the success of the deletion operation.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id){
        // Delete a doctor by its ID.
        return ResultHelper.OK(doctorService.delete(id));
    }
}
