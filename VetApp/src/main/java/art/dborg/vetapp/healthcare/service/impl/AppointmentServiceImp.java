package art.dborg.vetapp.healthcare.service.impl;

import art.dborg.vetapp.common.exception.*;
import art.dborg.vetapp.healthcare.service.AppointmentService;
import art.dborg.vetapp.common.config.modelMapper.ModelMapperService;
import art.dborg.vetapp.common.result.ResultData;
import art.dborg.vetapp.common.utilities.ResultHelper;
import art.dborg.vetapp.healthcare.model.entity.dto.appointment.AppointmentSaveRequest;
import art.dborg.vetapp.healthcare.model.entity.dto.appointment.AppointmentUpdateRequest;
import art.dborg.vetapp.healthcare.model.entity.dto.appointment.AppointmentsResponse;
import art.dborg.vetapp.healthcare.model.entity.dto.appointment.AppointmentResponse;
import art.dborg.vetapp.common.utilities.Message;
import art.dborg.vetapp.petcare.repository.AnimalRepository;
import art.dborg.vetapp.healthcare.repository.AppointmentRepository;
import art.dborg.vetapp.healthcare.repository.AvailableRepository;
import art.dborg.vetapp.healthcare.repository.DoctorRepository;
import art.dborg.vetapp.petcare.model.entity.Animal;
import art.dborg.vetapp.healthcare.model.entity.Appointment;
import art.dborg.vetapp.healthcare.model.entity.AvailableDate;
import art.dborg.vetapp.healthcare.model.entity.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AppointmentServiceImp implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final AvailableRepository availableRepository;
    private final AnimalRepository animalRepository;
    private final ModelMapperService mapperService;


    @Override
    public ResultData<AppointmentResponse> addAppointments(AppointmentSaveRequest appointment) {
        if (!doctorRepository.existsById(appointment.getDoctor().getId()) || !animalRepository.existsById(appointment.getAnimal().getId())) {
            throw new NotFoundException(Message.NOT_FOUND_ID);
        }
        if (availableRepository.findByAvailableIdInEndDateAndDoctorId(appointment.getDateTime().toLocalDate(), appointment.getDoctor().getId()) == null) {
            throw new DoctorDaysConflictException(Message.DAYS_CONFLICT);
        }
        Long availableId = availableRepository.findByAvailableIdInEndDateAndDoctorId(appointment.getDateTime().toLocalDate(), appointment.getDoctor().getId());
        if (availableRepository.existsByIdAndDateAndDoctors_Id(availableId, appointment.getDateTime().toLocalDate(), appointment.getDoctor().getId())) {

            for (int i = 0; i < appointmentRepository.findAll().size(); i++) {
                if (appointmentRepository.existsByDoctor_Id(appointment.getDoctor().getId())) {
                    if (Duration.between(appointment.getDateTime(), appointmentRepository.findAll().get(i).getDateTime()).toHours() == 0) { // Section 18 - Save an appointment
                        throw new AppointmentConflictException(Message.APPOINTMENT_CONFLICT);
                    }
                }
            }
            AvailableDate availableDate = availableRepository.findById(availableId).orElseThrow();
            appointment.setAvailableDate(availableDate);
            return ResultHelper.CREATED(mapperService.forResponse().map(appointmentRepository.save(mapperService.forRequest().map(appointment, Appointment.class)), AppointmentResponse.class));
        }
        throw new DoctorDaysConflictException(Message.DAYS_CONFLICT);
    }

    @Override
    public ResultData<AppointmentResponse> updateAppointments(AppointmentUpdateRequest appointment) {
        getByAppointmentsId(appointment.getId());
        return ResultHelper.OK(mapperService.forResponse().map(addAppointments(mapperService.forRequest().map(appointment, AppointmentSaveRequest.class)), AppointmentResponse.class));
    }

    @Override
    public ResultData<AppointmentsResponse> getByAppointmentsId(long id) {
        return ResultHelper.OK(mapperService.forResponse().map(appointmentRepository.findById(id).orElseThrow(), AppointmentsResponse.class));
    }

    @Override
    public boolean delete(long id) {
        getByAppointmentsId(id);
        return true;
    }

    @Override
    public ResultData<List<AppointmentsResponse>> filterDateTimeAndDoctor(LocalDateTime startDate, LocalDateTime endDate, Doctor doctor) { // Section 20 - filter by doctor id and date
        if (doctorRepository.findById(doctor.getId()).isEmpty()) {
            throw new NotFoundDoctorException(Message.NOT_FOUND_DOCTOR);
        }
        if (!appointmentRepository.existsByDateTimeBetween(startDate, endDate)) {
            throw new NotFoundAppointment(Message.NOT_FOUND_APPOINTMENT);
        }
        Doctor doctor1 = doctorRepository.findById(doctor.getId()).orElseThrow();
        return ResultHelper.OK(appointmentRepository.findByDateTimeBetweenAndDoctor(startDate,endDate,doctor1).stream().map(appointment -> mapperService.forResponse().map(appointment, AppointmentsResponse.class)).collect(Collectors.toList()));
    }

    @Override
    public ResultData<List<AppointmentsResponse>> filterDateTimeAndAnimal(LocalDateTime startDate, LocalDateTime endDate, Animal animal) { // Section 20 - filter by animal id and date
        if (animalRepository.findById(animal.getId()).isEmpty()) {
            throw new NotFoundAnimalException(Message.NOT_FOUND_ANIMAL);
        }
        if (!appointmentRepository.existsByDateTimeBetween(startDate, endDate)) {
            throw new NotFoundAppointment(Message.NOT_FOUND_APPOINTMENT);
        }
        Animal animal1 = animalRepository.findById(animal.getId()).orElseThrow();
        return ResultHelper.OK(appointmentRepository.findByDateTimeBetweenAndAnimal(startDate,endDate,animal1).stream().map(appointment -> mapperService.forResponse().map(appointment, AppointmentsResponse.class)).collect(Collectors.toList()));
    }
}
