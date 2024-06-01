package art.dborg.vetapp.v1.dto.request.animal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalNameUpdateRequest {
    private Long id;
    private String name;
}
