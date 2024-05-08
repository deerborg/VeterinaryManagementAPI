package art.dborg.vetapp.v1.business.concretes;

import art.dborg.vetapp.v1.business.abstracts.AvailableDateService;
import art.dborg.vetapp.v1.core.exception.NotFoundDoctorException;
import art.dborg.vetapp.v1.core.exception.SameValuesException;
import art.dborg.vetapp.v1.core.utilities.Message;
import art.dborg.vetapp.v1.dao.AvailableRepository;
import art.dborg.vetapp.v1.dao.DoctorRepository;
import art.dborg.vetapp.v1.entities.AvailableDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvailableDateManager implements AvailableDateService {
    private final AvailableRepository availableRepository;
    private final DoctorRepository doctorRepository;
    @Override
    public AvailableDate save(AvailableDate availableDate) {
        if(doctorRepository.findById(availableDate.getDoctors().getId()).isEmpty()){
            throw new NotFoundDoctorException(Message.NOT_FOUND_DOCTOR);
        }
        if(availableRepository.existsByDateAndDoctors_Id(availableDate.getDate(),availableDate.getDoctors().getId())){
            throw new SameValuesException(Message.SAME_VALUES);
        }
        return availableRepository.save(availableDate);
    }
}
