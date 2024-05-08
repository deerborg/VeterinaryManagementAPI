package art.dborg.vetapp.v1.api;

import art.dborg.vetapp.v1.business.abstracts.AvailableDateService;
import art.dborg.vetapp.v1.core.Result.Result;
import art.dborg.vetapp.v1.core.Result.ResultData;
import art.dborg.vetapp.v1.core.config.modelMapper.ModelMapperService;
import art.dborg.vetapp.v1.core.utilities.ResultHelper;
import art.dborg.vetapp.v1.dto.request.availableDate.AvailableDateSaveRequest;
import art.dborg.vetapp.v1.dto.request.availableDate.AvailableDateUpdateRequest;
import art.dborg.vetapp.v1.dto.response.availableDate.AvailableDateResponse;
import art.dborg.vetapp.v1.entities.AvailableDate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/availableDates")
public class AvailableDateController {
    private final AvailableDateService availableDateService;
    private final ModelMapperService modelMapperService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AvailableDateResponse> save(@Valid @RequestBody AvailableDateSaveRequest availableDateSaveRequest){
        return ResultHelper.CREATED(modelMapperService.forResponse().map(availableDateService.save(modelMapperService.forRequest().map(availableDateSaveRequest, AvailableDate.class)),AvailableDateResponse.class));
    }
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> update(@Valid @RequestBody AvailableDateUpdateRequest availableDateUpdateRequest){
        return ResultHelper.OK(modelMapperService.forResponse().map(availableDateService.update(modelMapperService.forRequest().map(availableDateUpdateRequest, AvailableDate.class)),AvailableDateResponse.class));
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> getId(@PathVariable("id") long id){
        return ResultHelper.OK(modelMapperService.forResponse().map(availableDateService.getId(id),AvailableDateResponse.class));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id){
        return ResultHelper.OK(availableDateService.delete(id));
    }
}
