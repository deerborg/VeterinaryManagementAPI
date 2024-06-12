package art.dborg.vetapp.v1.service.interfaces;

import art.dborg.vetapp.v1.core.result.ResultData;
import art.dborg.vetapp.v1.dto.request.appointment.AppointmentSaveRequest;
import art.dborg.vetapp.v1.dto.request.appointment.AppointmentUpdateRequest;
import art.dborg.vetapp.v1.dto.response.appointment.AppointmentsResponse;
import art.dborg.vetapp.v1.dto.response.appointment.AppointmentResponse;
import art.dborg.vetapp.v1.entities.Animal;
import art.dborg.vetapp.v1.entities.Doctor;

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
