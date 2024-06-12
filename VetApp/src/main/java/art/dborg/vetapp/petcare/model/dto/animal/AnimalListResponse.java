package art.dborg.vetapp.petcare.model.dto.animal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimalListResponse {
    private Long id;
    private String name;
    private Integer age;
}
