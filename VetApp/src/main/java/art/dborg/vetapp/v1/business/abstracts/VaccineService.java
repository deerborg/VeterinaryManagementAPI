package art.dborg.vetapp.v1.business.abstracts;

import art.dborg.vetapp.v1.entities.Vaccine;

public interface VaccineService {
    Vaccine addVaccine(Vaccine vaccine);
    Vaccine getId(long id);
}
