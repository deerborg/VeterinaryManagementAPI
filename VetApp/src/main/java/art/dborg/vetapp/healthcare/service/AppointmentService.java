package art.dborg.vetapp.healthcare.service;

import art.dborg.vetapp.common.result.ResultData;
import art.dborg.vetapp.healthcare.model.entity.dto.appointment.AppointmentSaveRequest;
import art.dborg.vetapp.healthcare.model.entity.dto.appointment.AppointmentUpdateRequest;
import art.dborg.vetapp.healthcare.model.entity.dto.appointment.AppointmentsResponse;
import art.dborg.vetapp.healthcare.model.entity.dto.appointment.AppointmentResponse;
import art.dborg.vetapp.petcare.model.entity.Animal;
import art.dborg.vetapp.healthcare.model.entity.Doctor;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {

    ResultData<AppointmentResponse> addAppointments(AppointmentSaveRequest appointment);

    ResultData<AppointmentResponse> updateAppointments(AppointmentUpdateRequest appointment);

    ResultData<AppointmentsResponse> getByAppointmentsId(long id);

    ResultData<List<AppointmentsResponse>> filterDateTimeAndDoctor(LocalDateTime startDate, LocalDateTime endDate, Doctor doctor);

    ResultData<List<AppointmentsResponse>> filterDateTimeAndAnimal(LocalDateTime startDate, LocalDateTime endDate, Animal animal);

    boolean delete(long id);
}
