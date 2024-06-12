package art.dborg.vetapp.v1.service.interfaces;

import art.dborg.vetapp.v1.core.result.Result;
import art.dborg.vetapp.v1.core.result.ResultData;
import art.dborg.vetapp.v1.dto.request.doctor.DoctorSaveRequest;
import art.dborg.vetapp.v1.dto.request.doctor.DoctorUpdateRequest;
import art.dborg.vetapp.v1.dto.response.doctor.DoctorsResponse;
import art.dborg.vetapp.v1.dto.response.doctor.DoctorOnlyIdResponse;
import art.dborg.vetapp.v1.dto.response.doctor.DoctorResponse;

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
