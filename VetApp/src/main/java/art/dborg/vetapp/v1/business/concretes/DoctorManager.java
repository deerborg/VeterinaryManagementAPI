package art.dborg.vetapp.v1.business.concretes;

import art.dborg.vetapp.v1.business.abstracts.DoctorService;
import art.dborg.vetapp.v1.core.config.modelMapper.ModelMapperService;
import art.dborg.vetapp.v1.core.exception.NotFoundException;
import art.dborg.vetapp.v1.core.utilities.Message;
import art.dborg.vetapp.v1.dao.DoctorRepository;
import art.dborg.vetapp.v1.entities.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DoctorManager implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor update(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor getId(long id) {
        return doctorRepository.findById(id).orElseThrow(()-> new NotFoundException(Message.NOT_FOUND_ID));
    }

    @Override
    public boolean delete(long id) {
        doctorRepository.delete(getId(id));
        return true;
    }
}
