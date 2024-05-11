package art.dborg.vetapp.v1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "doctors")
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id", columnDefinition = "serial")
    private long id; // Unique identifier for the doctor

    @Column(name = "doctor_name")
    private String name; // Name of the doctor

    @Column(name = "doctor_phone", unique = true)
    private String phone; // Phone number of the doctor (unique)

    @Column(name = "doctor_mail", unique = true)
    private String mail; // Email address of the doctor (unique)

    @Column(name = "doctor_address")
    private String address; // Address of the doctor

    @Column(name = "doctor_city")
    private String city; // City of the doctor

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Appointment> appointments; // Appointments associated with the doctor

    @OneToMany(mappedBy = "doctors", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<AvailableDate> availableDates; // Available dates for the doctor
}
