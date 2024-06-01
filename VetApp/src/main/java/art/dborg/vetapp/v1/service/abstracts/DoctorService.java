package art.dborg.vetapp.v1.service.abstracts;

import art.dborg.vetapp.v1.core.result.ResultData;
import art.dborg.vetapp.v1.dto.request.doctor.DoctorSaveRequest;
import art.dborg.vetapp.v1.dto.request.doctor.DoctorUpdateRequest;
import art.dborg.vetapp.v1.dto.response.animal.AnimalResponse;
import art.dborg.vetapp.v1.dto.response.customer.CustomerAllResponse;
import art.dborg.vetapp.v1.dto.response.customer.CustomerOnlyIdResponse;
import art.dborg.vetapp.v1.dto.response.customer.CustomerResponse;
import art.dborg.vetapp.v1.dto.response.doctor.DoctorAllResponse;
import art.dborg.vetapp.v1.dto.response.doctor.DoctorOnlyIdResponse;
import art.dborg.vetapp.v1.dto.response.doctor.DoctorResponse;

import java.util.List;

public interface DoctorService {
    ResultData<DoctorResponse> addDoctor(DoctorSaveRequest doctor);

    ResultData<DoctorResponse> updateDoctor(DoctorUpdateRequest doctor);

    ResultData<DoctorResponse> getDoctorById(long id);

    boolean delete(long id);

    ResultData<List<DoctorResponse>> getByCustomerName(String name);

    ResultData <List<DoctorOnlyIdResponse>> getOnlyId();

    ResultData<List<DoctorAllResponse>> getAllCustomers();

    boolean deleteByName(String name);
}
