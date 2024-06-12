package art.dborg.vetapp.healthcare.service.impl;

import art.dborg.vetapp.healthcare.service.AvailableDateService;
import art.dborg.vetapp.common.config.modelMapper.ModelMapperService;
import art.dborg.vetapp.common.result.ResultData;
import art.dborg.vetapp.common.utilities.ResultHelper;
import art.dborg.vetapp.healthcare.model.entity.dto.availableDate.AvailableDateSaveRequest;
import art.dborg.vetapp.healthcare.model.entity.dto.availableDate.AvailableDateUpdateRequest;
import art.dborg.vetapp.healthcare.model.entity.dto.availableDate.AvailableDatesResponse;
import art.dborg.vetapp.healthcare.model.entity.dto.availableDate.AvailableDateResponse;
import art.dborg.vetapp.common.exception.AppointmentAlreadyExists;
import art.dborg.vetapp.common.exception.NotFoundDoctorException;
import art.dborg.vetapp.common.exception.NotFoundException;
import art.dborg.vetapp.common.exception.SameValuesException;
import art.dborg.vetapp.common.utilities.Message;
import art.dborg.vetapp.healthcare.repository.AppointmentRepository;
import art.dborg.vetapp.healthcare.repository.AvailableRepository;
import art.dborg.vetapp.healthcare.repository.DoctorRepository;
import art.dborg.vetapp.healthcare.model.entity.AvailableDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AvailableDateServiceImp implements AvailableDateService {
    private final AvailableRepository availableRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final ModelMapperService mapperService;

    @Override
    public ResultData<AvailableDateResponse> addAvailableDate(AvailableDateSaveRequest availableDate) {
        if(doctorRepository.findById(availableDate.getDoctors().getId()).isEmpty()){
            throw new NotFoundDoctorException(Message.NOT_FOUND_DOCTOR);
        }
        if(availableRepository.existsByDateAndDoctors_Id(availableDate.getDate(),availableDate.getDoctors().getId())){
            throw new SameValuesException(Message.SAME_VALUES);
        }
        return ResultHelper.CREATED(mapperService.forResponse().map(availableRepository.save(mapperService.forRequest().map(availableDate, AvailableDate.class)),AvailableDateResponse.class));
    }

    @Override
    public ResultData<AvailableDateResponse> updateAvailableDate(AvailableDateUpdateRequest availableDate) {
        if(appointmentRepository.existsByAvailableDate_Id(availableDate.getId())){
            throw new AppointmentAlreadyExists(Message.EXISTING_APPOINTMENT);
        }
        availableRepository.findById(availableDate.getId()).orElseThrow(()-> new NotFoundException(Message.NOT_FOUND_ID));
        if(doctorRepository.findById(availableDate.getDoctors().getId()).isEmpty()){
            throw new NotFoundDoctorException(Message.NOT_FOUND_DOCTOR);
        }
        if(availableRepository.existsByDateAndDoctors_Id(availableDate.getDate(),availableDate.getDoctors().getId())){
            throw new SameValuesException(Message.SAME_VALUES);
        }
        return ResultHelper.OK(mapperService.forResponse().map(availableRepository.save(mapperService.forRequest().map(availableDate, AvailableDate.class)),AvailableDateResponse.class));
    }

    @Override
    public ResultData<AvailableDatesResponse> getByAvailableId(long id) {
        return ResultHelper.OK(mapperService.forResponse().map(availableRepository.findById(id).orElseThrow(()-> new NotFoundException(Message.NOT_FOUND_ID)), AvailableDatesResponse.class));
    }

    @Override
    public boolean delete(long id) {
        if(appointmentRepository.existsByAvailableDate_Id(id)){
            throw new AppointmentAlreadyExists(Message.EXISTING_APPOINTMENT);
        }
        availableRepository.delete(availableRepository.findById(id).orElseThrow(()-> new NotFoundException(Message.NOT_FOUND_ID)));
        return true;
    }

    @Override
    public boolean forceDelete(long id) {
        availableRepository.delete(availableRepository.findById(id).orElseThrow(()-> new NotFoundException(Message.NOT_FOUND_ID)));
        return true;
    }

}
