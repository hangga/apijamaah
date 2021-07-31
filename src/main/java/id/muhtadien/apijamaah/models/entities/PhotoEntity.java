package id.muhtadien.apijamaah.models.entities;


import javax.persistence.*;

@Entity
@Table(name = "photo")
public class PhotoEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private JamaahEntity jamaah;
    @Lob
    private String base64;
    private String contentType;

    public JamaahEntity getJamaah() {
        return jamaah;
    }

    public void setJamaah(JamaahEntity jamaah) {
        this.jamaah = jamaah;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
