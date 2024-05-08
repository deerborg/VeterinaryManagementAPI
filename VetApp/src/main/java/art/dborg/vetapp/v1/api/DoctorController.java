package art.dborg.vetapp.v1.api;

import art.dborg.vetapp.v1.business.abstracts.DoctorService;
import art.dborg.vetapp.v1.core.Result.ResultData;
import art.dborg.vetapp.v1.core.config.modelMapper.ModelMapperService;
import art.dborg.vetapp.v1.core.utilities.ResultHelper;
import art.dborg.vetapp.v1.dto.request.doctor.DoctorSaveRequest;
import art.dborg.vetapp.v1.dto.request.doctor.DoctorUpdateRequest;
import art.dborg.vetapp.v1.dto.response.doctor.DoctorResponse;
import art.dborg.vetapp.v1.entities.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final ModelMapperService modelMapperService;
    private final DoctorService doctorService;

    @PostMapping
    public ResultData<DoctorResponse> save(@RequestBody DoctorSaveRequest doctorSaveRequest){
        return ResultHelper.CREATED(modelMapperService.forResponse().map(doctorService.save(modelMapperService.forRequest().map(doctorSaveRequest,Doctor.class)),DoctorResponse.class));
    }
    @PutMapping
    public ResultData<DoctorResponse> update(@RequestBody DoctorUpdateRequest doctorUpdateRequest){
        return ResultHelper.CREATED(modelMapperService.forResponse().map(doctorService.update(modelMapperService.forRequest().map(doctorUpdateRequest,Doctor.class)),DoctorResponse.class));
    }
}
