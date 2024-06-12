package art.dborg.vetapp.healthcare.model.entity;

import art.dborg.vetapp.petcare.model.entity.Animal;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private long id;

    @Column(name = "appointment_date")
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appointment_doctor_id",referencedColumnName = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appointment_animal_id",referencedColumnName = "animal_id")
    private Animal animal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appointment_available_date_id",referencedColumnName = "available_date_id")
    private AvailableDate availableDate;

    public Appointment() {
    }

    public Appointment(long id, LocalDateTime dateTime, Doctor doctor, Animal animal, AvailableDate availableDate) {
        this.id = id;
        this.dateTime = dateTime;
        this.doctor = doctor;
        this.animal = animal;
        this.availableDate = availableDate;
    }
}
