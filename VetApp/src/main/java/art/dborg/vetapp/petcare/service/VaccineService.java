package art.dborg.vetapp.petcare.service;

import art.dborg.vetapp.common.result.ResultData;
import art.dborg.vetapp.petcare.model.dto.vaccine.VaccineSaveRequest;
import art.dborg.vetapp.petcare.model.dto.vaccine.VaccineUpdateRequest;
import art.dborg.vetapp.petcare.model.dto.vaccine.VaccinesResponse;
import art.dborg.vetapp.petcare.model.dto.vaccine.VaccineResponse;

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
