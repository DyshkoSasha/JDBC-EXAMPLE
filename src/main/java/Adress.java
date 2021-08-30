import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
@Data
@AllArgsConstructor
public class Adress {
   private UUID id_adress;
   private String city;
   private String street;
   private Integer house;

}
