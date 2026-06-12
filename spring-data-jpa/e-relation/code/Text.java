import jakarta.persistence.Entity;
import jakarta.persistence.Lob;

@Entity
public class Text extends Resource {

    @Lob
    private String content;

    // Getters and Setters
}