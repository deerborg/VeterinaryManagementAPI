package art.dborg.vetapp.healthcare.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "availableDate")
@Getter
@Setter
public class AvailableDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "available_date_id")
    private long id;

    @Column(name = "available_date")
    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "available_date_doctor_id",referencedColumnName = "doctor_id")
    private Doctor doctors;

    @OneToMany(mappedBy = "availableDate",cascade = {CascadeType.REMOVE,CascadeType.MERGE},fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Appointment> appointments;

    public AvailableDate() {
    }

    public AvailableDate(long id, LocalDate date, Doctor doctors, List<Appointment> appointments) {
        this.id = id;
        this.date = date;
        this.doctors = doctors;
        this.appointments = appointments;
    }
}
