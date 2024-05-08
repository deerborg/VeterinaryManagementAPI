package art.dborg.vetapp.v1.business.abstracts;

import art.dborg.vetapp.v1.entities.Doctor;

public interface DoctorService {
    Doctor save(Doctor doctor);
    Doctor update(Doctor doctor);
    Doctor getId(long id);
    boolean delete(long id);
}
