package riccardogulin.u5d8.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NewUserPayload {
	private String name;
	private String surname;
	private String email;
	private String password;
}
