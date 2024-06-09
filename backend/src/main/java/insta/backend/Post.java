package insta.backend.model;

import jakarta.persistence.*;
import insta.backend.model.User;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String head;

    private String text;

    private int likes; // Dodane pole likes

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Pozostała część klasy bez zmian

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLikes() { // Getter dla pola likes
        return likes;
    }

    public void setLikes(int likes) { // Setter dla pola likes
        this.likes = likes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
