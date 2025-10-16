package davidebraghi.U5_W2_D4_Davide_Braghi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "blogposts")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Blogpost {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private int id;
    private String category;
    private String title;
    private String cover;
    private String content;
    private double readingTime;

    public Blogpost(String category, String title, String cover, String content, double readingTime) {
        this.category = category;
        this.title = title;
        this.cover = cover;
        this.content = content;
        this.readingTime = readingTime;
    }
}
