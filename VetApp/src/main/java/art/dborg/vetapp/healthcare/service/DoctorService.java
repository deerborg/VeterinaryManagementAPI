package art.dborg.vetapp.healthcare.service;

import art.dborg.vetapp.common.result.Result;
import art.dborg.vetapp.common.result.ResultData;
import art.dborg.vetapp.healthcare.model.entity.dto.doctor.DoctorSaveRequest;
import art.dborg.vetapp.healthcare.model.entity.dto.doctor.DoctorUpdateRequest;
import art.dborg.vetapp.healthcare.model.entity.dto.doctor.DoctorsResponse;
import art.dborg.vetapp.healthcare.model.entity.dto.doctor.DoctorOnlyIdResponse;
import art.dborg.vetapp.healthcare.model.entity.dto.doctor.DoctorResponse;

import java.util.List;

public interface DoctorService {
    ResultData<DoctorResponse> addDoctor(DoctorSaveRequest doctor);

    ResultData<DoctorResponse> updateDoctor(DoctorUpdateRequest doctor);

    ResultData<DoctorResponse> getDoctorById(long id);

    boolean delete(long id);

    ResultData<List<DoctorResponse>> getByDoctorName(String name);

    ResultData <List<DoctorOnlyIdResponse>> getOnlyId();

    ResultData<List<DoctorsResponse>> getAllDoctor();

    Result deleteByName(String name);

    ResultData<List<DoctorOnlyIdResponse>> getByDoctorsId();
}
