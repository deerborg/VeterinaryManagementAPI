package art.dborg.vetapp.v1.service.abstracts;

import art.dborg.vetapp.v1.core.result.ResultData;
import art.dborg.vetapp.v1.dto.request.availableDate.AvailableDateSaveRequest;
import art.dborg.vetapp.v1.dto.request.availableDate.AvailableDateUpdateRequest;
import art.dborg.vetapp.v1.dto.response.availableDate.AvailableDateGetAllResponse;
import art.dborg.vetapp.v1.dto.response.availableDate.AvailableDateResponse;


public interface AvailableDateService {

    ResultData<AvailableDateResponse> addAvailableDate(AvailableDateSaveRequest availableDate);

    ResultData<AvailableDateResponse> updateAvailableDate(AvailableDateUpdateRequest availableDate);

    ResultData<AvailableDateGetAllResponse> getByAvailableId(long id);

    boolean delete(long id);

    boolean forceDelete(long id);
}
