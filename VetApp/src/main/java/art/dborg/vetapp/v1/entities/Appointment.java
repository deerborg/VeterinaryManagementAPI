package art.dborg.vetapp.v1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "appointments")
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private long id; // Unique identifier for the appointment

    @Column(name = "appointment_date")
    private LocalDateTime dateTime; // Date and time of the appointment

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appointment_doctor_id",referencedColumnName = "doctor_id")
    private Doctor doctor; // Doctor associated with the appointment

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appointment_animal_id",referencedColumnName = "animal_id")
    private Animal animal; // Animal associated with the appointment

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appointment_available_date_id",referencedColumnName = "available_date_id")
    private AvailableDate availableDate; // Available date associated with the appointment
}
