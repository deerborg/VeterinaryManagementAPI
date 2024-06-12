package art.dborg.vetapp.healthcare.service.impl;

import art.dborg.vetapp.healthcare.service.DoctorService;
import art.dborg.vetapp.common.config.modelMapper.ModelMapperService;
import art.dborg.vetapp.common.result.Result;
import art.dborg.vetapp.common.result.ResultData;
import art.dborg.vetapp.common.utilities.ResultHelper;
import art.dborg.vetapp.healthcare.model.entity.dto.doctor.DoctorSaveRequest;
import art.dborg.vetapp.healthcare.model.entity.dto.doctor.DoctorUpdateRequest;
import art.dborg.vetapp.healthcare.model.entity.dto.doctor.DoctorsResponse;
import art.dborg.vetapp.healthcare.model.entity.dto.doctor.DoctorOnlyIdResponse;
import art.dborg.vetapp.healthcare.model.entity.dto.doctor.DoctorResponse;
import art.dborg.vetapp.common.exception.ForUpdateNotFoundIdException;
import art.dborg.vetapp.common.exception.NotFoundException;
import art.dborg.vetapp.common.exception.NotUniqueValues;
import art.dborg.vetapp.common.utilities.Message;
import art.dborg.vetapp.healthcare.repository.DoctorRepository;
import art.dborg.vetapp.healthcare.model.entity.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class DoctorServiceImp implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapperService mapperService;

    @Override
    public ResultData<DoctorResponse> addDoctor(DoctorSaveRequest doctor) { // Section 15 - Registering a doctor
        if (doctorRepository.existsByMailOrPhone(doctor.getMail(), doctor.getPhone())) {
            throw new NotUniqueValues(Message.NOT_UNIQUE);
        }
        return ResultHelper.CREATED(mapperService.forResponse().map(doctorRepository.save(mapperService.forRequest().map(doctor, Doctor.class)), DoctorResponse.class));
    }

    @Override
    public ResultData<DoctorResponse> updateDoctor(DoctorUpdateRequest doctor) {
        doctorRepository.findById(doctor.getId()).orElseThrow(() -> new ForUpdateNotFoundIdException(Message.UPDATE_NOT_FOUND_ID));
        return ResultHelper.OK(mapperService.forResponse().map(doctorRepository.save(mapperService.forRequest().map(doctor, Doctor.class)), DoctorResponse.class));
    }

    @Override
    public ResultData<DoctorResponse> getDoctorById(long id) {
        return ResultHelper.OK(mapperService.forResponse().map(doctorRepository.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND_ID)), DoctorResponse.class));
    }

    @Override
    public boolean delete(long id) {
        doctorRepository.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND_ID));
        return true;
    }

    @Override
    public ResultData<List<DoctorResponse>> getByDoctorName(String name) {
        return null;
    }

    @Override
    public ResultData<List<DoctorOnlyIdResponse>> getOnlyId() {
        return null;
    }

    @Override
    public ResultData<List<DoctorsResponse>> getAllDoctor() {
        return ResultHelper.OK(doctorRepository.findAll().stream().map(doctor -> mapperService.forResponse().map(doctor, DoctorsResponse.class)).collect(Collectors.toList()));
    }

    @Override
    public Result deleteByName(String name) {
        doctorRepository.delete(doctorRepository.findByName(name));
        return new Result(true,"Deleted","201");
    }

    @Override
    public ResultData<List<DoctorOnlyIdResponse>> getByDoctorsId() {
        return ResultHelper.OK(doctorRepository.findAll().stream().map(doctor -> mapperService.forResponse().map(doctor, DoctorOnlyIdResponse.class)).collect(Collectors.toList()));
    }
}
