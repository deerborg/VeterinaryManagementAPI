package art.dborg.vetapp.v1.service.abstracts;

import art.dborg.vetapp.v1.core.result.ResultData;
import art.dborg.vetapp.v1.dto.request.doctor.DoctorSaveRequest;
import art.dborg.vetapp.v1.dto.request.doctor.DoctorUpdateRequest;
import art.dborg.vetapp.v1.dto.response.doctor.DoctorResponse;

public interface DoctorService {
    ResultData<DoctorResponse> addDoctor(DoctorSaveRequest doctor);

    ResultData<DoctorResponse> updateDoctor(DoctorUpdateRequest doctor);

    ResultData<DoctorResponse> getDoctorById(long id);

    boolean delete(long id);
}
