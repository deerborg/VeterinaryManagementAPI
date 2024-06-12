package art.dborg.vetapp.v1.dto.request.animal;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimalNameUpdateRequest {
    private Long id;
    private String name;

    public AnimalNameUpdateRequest() {
    }

    public AnimalNameUpdateRequest(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
