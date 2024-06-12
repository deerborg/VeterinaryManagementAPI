package art.dborg.vetapp.v1.dto.response.animal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class AnimalListResponse {
    private Long id;
    private String name;
    private Integer age;
}
