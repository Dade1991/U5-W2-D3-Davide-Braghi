package davidebraghi.U5_W2_D4_Davide_Braghi.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NewAuthorPayload {
    private String name;
    private String surname;
    private String email;
    private String dateOfBirth;
}
