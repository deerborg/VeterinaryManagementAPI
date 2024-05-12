package art.dborg.vetapp.v1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "availableDate")
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "available_date_id")
    private long id; // Unique identifier for the available date

    @Column(name = "available_date")
    @Temporal(TemporalType.DATE)
    private LocalDate date; // Date of availability

    @ManyToOne(fetch = FetchType.EAGER) // Section - 9 : Relationships between entities
    @JoinColumn(name = "available_date_doctor_id",referencedColumnName = "doctor_id")
    private Doctor doctors; // Doctor associated with the available date

    @OneToMany(mappedBy = "availableDate",cascade = {CascadeType.REMOVE,CascadeType.MERGE},fetch = FetchType.LAZY) // Section - 9 : Relationships between entities
    @JsonIgnore
    private List<Appointment> appointments; // Appointments scheduled for the available date
}
