package tg.schoolapi.model.dto;

public class ContentDTO {

    private Long id;

    private String title;

    private String subtitle;

    private String image;

    private String text;

    private String footer;

    public ContentDTO() {
    }

    public ContentDTO(Long id, String title, String subtitle, String image, String text, String footer) {

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