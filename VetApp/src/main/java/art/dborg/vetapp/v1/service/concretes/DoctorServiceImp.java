package art.dborg.vetapp.v1.service.concretes;

import art.dborg.vetapp.v1.core.config.modelMapper.ModelMapperService;
import art.dborg.vetapp.v1.core.result.ResultData;
import art.dborg.vetapp.v1.core.utilities.ResultHelper;
import art.dborg.vetapp.v1.dto.request.doctor.DoctorSaveRequest;
import art.dborg.vetapp.v1.dto.request.doctor.DoctorUpdateRequest;
import art.dborg.vetapp.v1.dto.response.doctor.DoctorResponse;
import art.dborg.vetapp.v1.service.abstracts.DoctorService;
import art.dborg.vetapp.v1.core.exception.ForUpdateNotFoundIdException;
import art.dborg.vetapp.v1.core.exception.NotFoundException;
import art.dborg.vetapp.v1.core.exception.NotUniqueValues;
import art.dborg.vetapp.v1.core.utilities.Message;
import art.dborg.vetapp.v1.dao.DoctorRepository;
import art.dborg.vetapp.v1.entities.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * This class implements the DoctorService interface and provides methods for managing doctor entities.
 */
@RequiredArgsConstructor
@Service
public class DoctorServiceImp implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapperService mapperService;

    @Override
    public ResultData<DoctorResponse> addDoctor(DoctorSaveRequest doctor) { // Section 15 - Registering a doctor
        if(doctorRepository.existsByMailOrPhone(doctor.getMail(),doctor.getPhone())){
            throw new NotUniqueValues(Message.NOT_UNIQUE);
        }
        return ResultHelper.CREATED(mapperService.forResponse().map(doctorRepository.save(mapperService.forRequest().map(doctor,Doctor.class)),DoctorResponse.class));
    }

    @Override
    public ResultData<DoctorResponse> updateDoctor(DoctorUpdateRequest doctor) {
        doctorRepository.findById(doctor.getId()).orElseThrow(()-> new ForUpdateNotFoundIdException(Message.UPDATE_NOT_FOUND_ID));
        return ResultHelper.OK(mapperService.forResponse().map(doctorRepository.save(mapperService.forRequest().map(doctor,Doctor.class)),DoctorResponse.class));
    }

    @Override
    public ResultData<DoctorResponse> getDoctorById(long id) {
        return ResultHelper.OK(mapperService.forResponse().map(doctorRepository.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND_ID)),DoctorResponse.class));
    }

    @Override
    public boolean delete(long id) {
        doctorRepository.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND_ID));
        return true;
    }
}
