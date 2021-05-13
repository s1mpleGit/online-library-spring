package by.library.onlinelibrary.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"author", "cards"})
@ToString(exclude = {"author", "cards"})

@Entity
@Table(name = "BOOKS")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;
    private String imageUri;

    public Book(String title) {
        this.title = title;
    }

    public Book(String title, String description, String imageUri) {
        this.title = title;
        this.description = description;
        this.imageUri = imageUri;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @OneToMany(mappedBy = "book")
    private Set<Card> cards;

}
