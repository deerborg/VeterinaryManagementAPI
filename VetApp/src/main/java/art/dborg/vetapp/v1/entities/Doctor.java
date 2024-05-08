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
    @Column(name = "doctor_id",columnDefinition = "serial")
    private long id;

    @Column(name = "doctor_name")
    private String name;

    @Column(name = "doctor_phone",unique = true)
    private String phone;

    @Column(name = "doctor_mail",unique = true)
    private String mail;

    @Column(name = "doctor_address")
    private String address;

    @Column(name = "doctor_city")
    private String city;

    @OneToMany(mappedBy = "doctor",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "doctors", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<AvailableDate> availableDates;
}
