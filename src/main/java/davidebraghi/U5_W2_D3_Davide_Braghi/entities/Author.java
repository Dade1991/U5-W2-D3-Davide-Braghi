package davidebraghi.U5_W2_D4_Davide_Braghi.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Author {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private int id;
    private String name;
    private String surname;
    private String email;
    private String dateOfBirth;
    @Column(name = "avatar_url")
    private String avatar;

    public Author(String name, String surname, String email, String dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }
}