package davidebraghi.U5_W2_D3_Davide_Braghi.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NewBlogpostPayload {
    private String category;
    private String title;
    private String cover;
    private String content;
    private double readingTime;
}
