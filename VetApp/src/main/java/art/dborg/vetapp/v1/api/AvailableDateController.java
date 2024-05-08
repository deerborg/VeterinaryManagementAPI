package art.dborg.vetapp.v1.api;

import art.dborg.vetapp.v1.business.abstracts.AvailableDateService;
import art.dborg.vetapp.v1.core.Result.ResultData;
import art.dborg.vetapp.v1.core.config.modelMapper.ModelMapperService;
import art.dborg.vetapp.v1.core.utilities.ResultHelper;
import art.dborg.vetapp.v1.dto.request.availableDate.AvailableDateSaveRequest;
import art.dborg.vetapp.v1.dto.response.availableDate.AvailableDateResponse;
import art.dborg.vetapp.v1.entities.AvailableDate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/availableDates")
public class AvailableDateController {
    private final AvailableDateService availableDateService;
    private final ModelMapperService modelMapperService;

    @PostMapping
    public ResultData<AvailableDateResponse> save(@RequestBody AvailableDateSaveRequest availableDateSaveRequest){
        return ResultHelper.CREATED(modelMapperService.forResponse().map(availableDateService.save(modelMapperService.forRequest().map(availableDateSaveRequest, AvailableDate.class)),AvailableDateResponse.class));
    }

}
