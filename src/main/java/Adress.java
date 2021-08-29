import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
@Data
@AllArgsConstructor
public class Adress {
    UUID id_adress;
    String city;
    String street;
    String house;

}
