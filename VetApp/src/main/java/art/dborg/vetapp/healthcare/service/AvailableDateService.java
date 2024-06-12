package art.dborg.vetapp.healthcare.service;

import art.dborg.vetapp.common.result.ResultData;
import art.dborg.vetapp.healthcare.model.entity.dto.availableDate.AvailableDateSaveRequest;
import art.dborg.vetapp.healthcare.model.entity.dto.availableDate.AvailableDateUpdateRequest;
import art.dborg.vetapp.healthcare.model.entity.dto.availableDate.AvailableDatesResponse;
import art.dborg.vetapp.healthcare.model.entity.dto.availableDate.AvailableDateResponse;


public interface AvailableDateService {

    ResultData<AvailableDateResponse> addAvailableDate(AvailableDateSaveRequest availableDate);

    ResultData<AvailableDateResponse> updateAvailableDate(AvailableDateUpdateRequest availableDate);

    ResultData<AvailableDatesResponse> getByAvailableId(long id);

    boolean delete(long id);

    boolean forceDelete(long id);
}
