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
    private long id;

    @Column(name = "appointment_date")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "appointment_doctor_id",referencedColumnName = "doctor_id")
    private Doctor doctor;

}
