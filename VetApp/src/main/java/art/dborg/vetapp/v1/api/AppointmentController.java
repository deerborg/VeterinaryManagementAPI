package art.dborg.vetapp.v1.api;

import art.dborg.vetapp.v1.business.abstracts.AnimalService;
import art.dborg.vetapp.v1.business.abstracts.AppointmentService;
import art.dborg.vetapp.v1.business.abstracts.DoctorService;
import art.dborg.vetapp.v1.core.result.Result;
import art.dborg.vetapp.v1.core.result.ResultData;
import art.dborg.vetapp.v1.core.config.modelMapper.ModelMapperService;
import art.dborg.vetapp.v1.core.utilities.ResultHelper;
import art.dborg.vetapp.v1.dto.request.appointment.AppointmentSaveRequest;
import art.dborg.vetapp.v1.dto.request.appointment.AppointmentUpdateRequest;
import art.dborg.vetapp.v1.dto.response.appointment.AppointmentGetAllResponse;
import art.dborg.vetapp.v1.dto.response.appointment.AppointmentResponse;
import art.dborg.vetapp.v1.entities.Animal;
import art.dborg.vetapp.v1.entities.Appointment;
import art.dborg.vetapp.v1.entities.Doctor;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller class for managing appointments.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final ModelMapperService modelMapperService;
    private final AnimalService animalService;

    /**
     * Saves a new appointment.
     * @param appointmentSaveRequest The request object containing appointment data.
     * @return ResultData containing the response object with saved appointment data.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AppointmentResponse> save(@Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest) {
        // Add a new appointment and map the result to a response object.
        return ResultHelper.CREATED(modelMapperService.forResponse().map(appointmentService.addAppointments(modelMapperService.forRequest().map(appointmentSaveRequest,Appointment.class)),AppointmentResponse.class));
    }

    /**
     * Updates an existing appointment.
     * @param appointmentUpdateRequest The request object containing updated appointment data.
     * @return ResultData containing the response object with updated appointment data.
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> update(@Valid @RequestBody AppointmentUpdateRequest appointmentUpdateRequest) {
        // Update an existing appointment and map the result to a response object.
        return ResultHelper.OK(modelMapperService.forResponse().map(appointmentService.update(modelMapperService.forRequest().map(appointmentUpdateRequest,Appointment.class)),AppointmentResponse.class));
    }

    /**
     * Retrieves an appointment by its ID.
     * @param id The ID of the appointment to retrieve.
     * @return ResultData containing the response object with the appointment data.
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentGetAllResponse> getId(@PathVariable("id") long id){
        // Retrieve an appointment by its ID and map the result to a response object.
        return ResultHelper.OK(modelMapperService.forResponse().map(appointmentService.getId(id),AppointmentGetAllResponse.class));
    }

    /**
     * Deletes an appointment by its ID.
     * @param id The ID of the appointment to delete.
     * @return Result indicating the success of the deletion operation.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id){
        // Delete an appointment by its ID.
        return ResultHelper.DELETE(appointmentService.delete(id));
    }

    /**
     * Filters appointments by date and doctor.
     * @param startDate The start date of the filter range.
     * @param endDate The end date of the filter range.
     * @param id The ID of the doctor.
     * @return ResultData containing the response object with filtered appointment data.
     */
    @GetMapping("/appointments-date-doctor")
    public ResultData<List<AppointmentGetAllResponse>> filterDateTimeAndDoctor(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                                               @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
                                                                               @RequestParam("doctorId") long id){
        Doctor doctor = doctorService.getId(id);
        // Filter appointments by date and doctor, then map the results to response objects.
        return ResultHelper.OK(appointmentService.filterDateTimeAndDoctor(startDate,endDate,doctor).stream().map(appointment -> modelMapperService.forResponse().map(appointment,AppointmentGetAllResponse.class)).collect(Collectors.toList()));
    }

    /**
     * Filters appointments by date and animal.
     * @param startDate The start date of the filter range.
     * @param endDate The end date of the filter range.
     * @param id The ID of the animal.
     * @return ResultData containing the response object with filtered appointment data.
     */
    @GetMapping("/appointments-date-animal")
    public ResultData<List<AppointmentGetAllResponse>> filterDateTimeAndAnimal(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                                               @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
                                                                               @RequestParam("animalId") long id){
        Animal animal = animalService.getId(id);
        // Filter appointments by date and animal, then map the results to response objects.
        return ResultHelper.OK(appointmentService.filterDateTimeAndAnimal(startDate,endDate,animal).stream().map(appointment -> modelMapperService.forResponse().map(appointment,AppointmentGetAllResponse.class)).collect(Collectors.toList()));
    }
}
