package art.dborg.vetapp.petcare.controller;

import art.dborg.vetapp.petcare.service.VaccineService;
import art.dborg.vetapp.common.result.Result;
import art.dborg.vetapp.common.result.ResultData;
import art.dborg.vetapp.common.utilities.ResultHelper;
import art.dborg.vetapp.petcare.model.dto.vaccine.VaccineSaveRequest;
import art.dborg.vetapp.petcare.model.dto.vaccine.VaccineUpdateRequest;
import art.dborg.vetapp.petcare.model.dto.vaccine.VaccinesResponse;
import art.dborg.vetapp.petcare.model.dto.vaccine.VaccineResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/vaccines")
public class VaccineController {

    private final VaccineService vaccineService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<VaccineResponse> addVaccine(@Valid @RequestBody VaccineSaveRequest vaccineSaveRequest) {
        return vaccineService.addVaccine(vaccineSaveRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> update(@Valid @RequestBody VaccineUpdateRequest vaccineUpdateRequest) {
        return vaccineService.vaccineUpdate(vaccineUpdateRequest);
    }

    @PutMapping("/force-update")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> forceUpdate(@Valid @RequestBody VaccineUpdateRequest vaccineUpdateRequest) {
        return vaccineService.vaccineForceUpdate(vaccineUpdateRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccinesResponse> getId(@PathVariable long id) {
        return vaccineService.getVaccineById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {
        return ResultHelper.DELETE(vaccineService.delete(id));
    }

    @GetMapping("/animal/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccinesResponse>> getAnimalVaccineList(@PathVariable("id") long id) {
        return vaccineService.getAnimalVaccineList(id);
    }

    @GetMapping("/find-date") // 1
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccinesResponse>> getFilterByStartAndEndDate(@RequestParam("firstDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate firsDate, @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return vaccineService.getFilterByStartAndEndDate(firsDate,endDate);
    }
    @GetMapping("/all-vaccine")
    public ResultData<List<VaccinesResponse>> getAllVaccineList() {
        return vaccineService.getAllVaccine();
    }
}