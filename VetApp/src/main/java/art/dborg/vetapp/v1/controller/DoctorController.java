package art.dborg.vetapp.v1.controller;

import art.dborg.vetapp.v1.dto.response.doctor.DoctorAllResponse;
import art.dborg.vetapp.v1.service.abstracts.DoctorService;
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

import java.util.List;

/**
 * Controller class for managing doctors.
 */
@RestController
@RequestMapping("/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<DoctorResponse> addDoctor(@Valid @RequestBody DoctorSaveRequest doctorSaveRequest) {
        return doctorService.addDoctor(doctorSaveRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> updateDoctor(@Valid @RequestBody DoctorUpdateRequest doctorUpdateRequest) {
        return doctorService.updateDoctor(doctorUpdateRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> getByDoctorId(@PathVariable("id") long id) {
        return doctorService.getDoctorById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {
        return ResultHelper.OK(doctorService.delete(id));
    }
    @GetMapping("/all-doctor")
    public ResultData<List<DoctorAllResponse>> getAllDoctor(){
        return doctorService.getAllDoctor();
    }
    @DeleteMapping("/name/{name}")
    public Result deleteDoctor(@PathVariable("name") String name) {
        return doctorService.deleteByName(name);
    }
}
