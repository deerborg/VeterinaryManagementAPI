package art.dborg.vetapp.v1.service.abstracts;

import art.dborg.vetapp.v1.core.result.ResultData;
import art.dborg.vetapp.v1.dto.request.appointment.AppointmentSaveRequest;
import art.dborg.vetapp.v1.dto.request.appointment.AppointmentUpdateRequest;
import art.dborg.vetapp.v1.dto.response.appointment.AppointmentGetAllResponse;
import art.dborg.vetapp.v1.dto.response.appointment.AppointmentResponse;
import art.dborg.vetapp.v1.entities.Animal;
import art.dborg.vetapp.v1.entities.Appointment;
import art.dborg.vetapp.v1.entities.Doctor;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {

    ResultData<AppointmentResponse> addAppointments(AppointmentSaveRequest appointment);

    ResultData<AppointmentResponse> updateAppointments(AppointmentUpdateRequest appointment);

    ResultData<AppointmentGetAllResponse> getByAppointmentsId(long id);

    ResultData<List<AppointmentGetAllResponse>> filterDateTimeAndDoctor(LocalDateTime startDate, LocalDateTime endDate, Doctor doctor);

    ResultData<List<AppointmentGetAllResponse>> filterDateTimeAndAnimal(LocalDateTime startDate, LocalDateTime endDate, Animal animal);

    boolean delete(long id);
}
