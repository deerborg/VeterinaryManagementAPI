package art.dborg.vetapp.v1.controller;

import art.dborg.vetapp.v1.service.interfaces.AppointmentService;
import art.dborg.vetapp.v1.core.result.Result;
import art.dborg.vetapp.v1.core.result.ResultData;
import art.dborg.vetapp.v1.core.utilities.ResultHelper;
import art.dborg.vetapp.v1.dto.request.appointment.AppointmentSaveRequest;
import art.dborg.vetapp.v1.dto.request.appointment.AppointmentUpdateRequest;
import art.dborg.vetapp.v1.dto.response.appointment.AppointmentsResponse;
import art.dborg.vetapp.v1.dto.response.appointment.AppointmentResponse;
import art.dborg.vetapp.v1.entities.Animal;
import art.dborg.vetapp.v1.entities.Doctor;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller class for managing appointments.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AppointmentResponse> save(@Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest) {
        return appointmentService.addAppointments(appointmentSaveRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> update(@Valid @RequestBody AppointmentUpdateRequest appointmentUpdateRequest) {
        return appointmentService.updateAppointments(appointmentUpdateRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentsResponse> getId(@PathVariable("id") long id){
        return appointmentService.getByAppointmentsId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id){
        return ResultHelper.DELETE(appointmentService.delete(id));
    }

    @GetMapping("/appointments-date-doctor")
    public ResultData<List<AppointmentsResponse>> filterDateTimeAndDoctor(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                                          @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
                                                                          @RequestParam("doctorId") Doctor id){
        return appointmentService.filterDateTimeAndDoctor(startDate,endDate,id);
    }

    @GetMapping("/appointments-date-animal")
    public ResultData<List<AppointmentsResponse>> filterDateTimeAndAnimal(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                                          @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
                                                                          @RequestParam("animalId") Animal id){

        return appointmentService.filterDateTimeAndAnimal(startDate,endDate,id);
    }
}
