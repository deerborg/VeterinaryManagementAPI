package art.dborg.vetapp.v1.controller;

import art.dborg.vetapp.v1.service.interfaces.AvailableDateService;
import art.dborg.vetapp.v1.core.result.Result;
import art.dborg.vetapp.v1.core.result.ResultData;
import art.dborg.vetapp.v1.core.utilities.ResultHelper;
import art.dborg.vetapp.v1.dto.request.availableDate.AvailableDateSaveRequest;
import art.dborg.vetapp.v1.dto.request.availableDate.AvailableDateUpdateRequest;
import art.dborg.vetapp.v1.dto.response.availableDate.AvailableDatesResponse;
import art.dborg.vetapp.v1.dto.response.availableDate.AvailableDateResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/available-dates")
public class AvailableDateController {
    private final AvailableDateService availableDateService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AvailableDateResponse> save(@Valid @RequestBody AvailableDateSaveRequest availableDateSaveRequest){
        return availableDateService.addAvailableDate(availableDateSaveRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> update(@Valid @RequestBody AvailableDateUpdateRequest availableDateUpdateRequest){
        return availableDateService.updateAvailableDate(availableDateUpdateRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDatesResponse> getId(@PathVariable("id") long id){
        return availableDateService.getByAvailableId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id){
        return ResultHelper.DELETE(availableDateService.delete(id));
    }
    @DeleteMapping("/force-delete/{id}")
    public Result forceDelete(@PathVariable("id") long id){
        return ResultHelper.DELETE(availableDateService.forceDelete(id));
    }
}
