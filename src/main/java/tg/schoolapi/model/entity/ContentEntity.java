package tg.schoolapi.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "content")
public class ContentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "subtitle")
    private String subtitle;

    @Column(name = "image", columnDefinition = "LONGTEXT")
    private String image;

    @Column(name = "text", columnDefinition = "LONGTEXT")
    private String text;

    @Column(name = "footer")
    private String footer;

    public ContentEntity() {
    }

    public ContentEntity(Long id, String title, String subtitle, String image, String text, String footer) {

        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.image = image;
        this.text = text;
        this.footer = footer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }
}