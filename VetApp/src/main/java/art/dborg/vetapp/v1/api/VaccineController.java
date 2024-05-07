package art.dborg.vetapp.v1.api;

import art.dborg.vetapp.v1.business.abstracts.VaccineService;
import art.dborg.vetapp.v1.entities.Vaccine;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/vaccines")
public class VaccineController {

    private final VaccineService vaccineService;

    @PostMapping
    public Vaccine addVaccine(@RequestBody Vaccine vaccine){
        return vaccineService.addVaccine(vaccine);
    }
    @GetMapping("/{id}")
    public Vaccine getId(@PathVariable long id){
        return vaccineService.getId(id);
    }
}
