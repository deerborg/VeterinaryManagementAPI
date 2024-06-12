package art.dborg.vetapp.v1.service.interfaces;

import art.dborg.vetapp.v1.core.result.ResultData;
import art.dborg.vetapp.v1.dto.request.vaccine.VaccineSaveRequest;
import art.dborg.vetapp.v1.dto.request.vaccine.VaccineUpdateRequest;
import art.dborg.vetapp.v1.dto.response.vaccine.VaccinesResponse;
import art.dborg.vetapp.v1.dto.response.vaccine.VaccineResponse;

import java.time.LocalDate;
import java.util.List;

public interface VaccineService {

    ResultData<VaccineResponse> addVaccine(VaccineSaveRequest vaccine);

    ResultData<VaccinesResponse> getVaccineById(long id);

    ResultData<VaccineResponse> vaccineUpdate(VaccineUpdateRequest vaccine);

    ResultData<VaccineResponse> vaccineForceUpdate(VaccineUpdateRequest vaccine);

    boolean delete(long id);

    ResultData<List<VaccinesResponse>> getAnimalVaccineList(long id);

    ResultData<List<VaccinesResponse>> getFilterByStartAndEndDate(LocalDate startDate, LocalDate endDate);

    ResultData<List<VaccinesResponse>> getAllVaccine();
}
