package art.dborg.vetapp.petcare.service.impl;

import art.dborg.vetapp.common.config.modelMapper.ModelMapperService;
import art.dborg.vetapp.common.exception.*;
import art.dborg.vetapp.common.result.ResultData;
import art.dborg.vetapp.common.utilities.ResultHelper;
import art.dborg.vetapp.petcare.model.dto.vaccine.VaccineSaveRequest;
import art.dborg.vetapp.petcare.model.dto.vaccine.VaccineUpdateRequest;
import art.dborg.vetapp.petcare.model.dto.vaccine.VaccinesResponse;
import art.dborg.vetapp.petcare.model.dto.vaccine.VaccineResponse;
import art.dborg.vetapp.petcare.service.VaccineService;
import art.dborg.vetapp.common.utilities.Message;
import art.dborg.vetapp.petcare.repository.AnimalRepository;
import art.dborg.vetapp.petcare.repository.VaccineRepository;
import art.dborg.vetapp.petcare.model.entity.Vaccine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VaccineServiceImp implements VaccineService {
    private final VaccineRepository vaccineRepository;
    private final AnimalRepository animalRepository;
    private final ModelMapperService mapperService;

    @Override
    public ResultData<VaccineResponse> addVaccine(VaccineSaveRequest vaccine) {
        if (animalRepository.findById(vaccine.getAnimal().getId()).isEmpty()) {
            throw new NotFoundAnimalException(Message.NOT_FOUND_ANIMAL);
        }
        if (vaccineRepository.existsVaccineByCodeAndNameAndAnimal_id(vaccine.getCode(), vaccine.getName(), vaccine.getAnimal().getId())) {
            if(vaccineRepository.findByEndDateAfterOrderByEndDate(vaccine.getStartDate()).isEmpty()){
                if (ChronoUnit.DAYS.between(vaccine.getStartDate(), vaccine.getEndDate()) < 0) {
                    throw new NoneSenseInformationException(Message.BAD_DATE);
                }
                return ResultHelper.CREATED(mapperService.forResponse().map(vaccineRepository.save(mapperService.forRequest().map(vaccine, Vaccine.class)), VaccineResponse.class));
            }
            throw new DateMistmatchException(Message.DATE_MISMATCH);
        }
        if (ChronoUnit.DAYS.between(vaccine.getStartDate(), vaccine.getEndDate()) < 0) {
            throw new NoneSenseInformationException(Message.BAD_DATE);
        }
        return ResultHelper.CREATED(mapperService.forResponse().map(vaccineRepository.save(mapperService.forRequest().map(vaccine, Vaccine.class)), VaccineResponse.class));
    }

    @Override
    public ResultData<VaccinesResponse> getVaccineById(long id) {
        return ResultHelper.OK(mapperService.forResponse().map(vaccineRepository.findById(id).orElseThrow(()-> new NotFoundException(Message.NOT_FOUND_ID)), VaccinesResponse.class));
    }

    @Override
    public ResultData<VaccineResponse> vaccineUpdate(VaccineUpdateRequest vaccine) {
        getVaccineById(vaccine.getId());
        if (vaccineRepository.existsVaccineByCodeAndNameAndAnimal_id(vaccine.getCode(), vaccine.getName(), vaccine.getAnimal().getId())) {
            if(vaccineRepository.findByEndDateAfterOrderByEndDate(vaccine.getStartDate()).isEmpty()){
                if (ChronoUnit.DAYS.between(vaccine.getStartDate(), vaccine.getEndDate()) < 0) {
                    throw new NoneSenseInformationException(Message.BAD_DATE);
                }
                return ResultHelper.OK(mapperService.forResponse().map(vaccineRepository.save(mapperService.forRequest().map(vaccine, Vaccine.class)), VaccineResponse.class));
            }
            throw new ForceUpdateException(Message.FORCE_UPDATE);
        }
        return ResultHelper.OK(mapperService.forResponse().map(addVaccine(mapperService.forRequest().map(vaccine,VaccineSaveRequest.class)),VaccineResponse.class));
    }

    @Override
    public ResultData<VaccineResponse> vaccineForceUpdate(VaccineUpdateRequest vaccine) {
        getVaccineById(vaccine.getId());
        if(!animalRepository.existsById(vaccine.getAnimal().getId())){
            throw new NotFoundAnimalException(Message.NOT_FOUND_ANIMAL);
        }
        if (vaccine.getEndDate().isBefore(vaccine.getStartDate())) {
            throw new NoneSenseInformationException(Message.BAD_DATE);
        }
        return ResultHelper.OK(mapperService.forResponse().map(vaccineRepository.save(mapperService.forRequest().map(vaccine, Vaccine.class)), VaccineResponse.class));
    }

    @Override
    public boolean delete(long id) {
        vaccineRepository.delete(vaccineRepository.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND_ID)));
        return true;
    }

    @Override
    public ResultData<List<VaccinesResponse>> getAnimalVaccineList(long id) {
        if (vaccineRepository.findByAnimal_Id(id).isEmpty()) {
            throw new NotFoundAnimalException(Message.NOT_FOUND_ANIMAL);
        }
        return ResultHelper.OK(vaccineRepository.findByAnimal_Id(id).stream().map(vaccine -> mapperService.forResponse().map(vaccine, VaccinesResponse.class)).collect(Collectors.toList()));
    }

    @Override
    public ResultData<List<VaccinesResponse>> getFilterByStartAndEndDate(LocalDate firstDate, LocalDate endDate) {
        if (vaccineRepository.findByEndDateBetween(firstDate,endDate).isEmpty()) {
            throw new NotFoundObjectRequest(Message.NOT_FOUND);
        }
        return ResultHelper.OK(vaccineRepository.findByEndDateBetween(firstDate, endDate).stream().map(vaccine -> mapperService.forResponse().map(vaccine, VaccinesResponse.class)).collect(Collectors.toList()));
    }

    @Override
    public ResultData<List<VaccinesResponse>> getAllVaccine() {
        return ResultHelper.OK(vaccineRepository.findAll().stream().map(vaccine -> mapperService.forResponse().map(vaccine, VaccinesResponse.class)).collect(Collectors.toList()));
    }
}
