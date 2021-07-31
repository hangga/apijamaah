package id.muhtadien.apijamaah.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "jamaah")
public class JamaahEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    //@Column(name = "nama", nullable = false)
    private String nama;
    //@Column(name = "alamat", nullable = false)
    private String alamat;
    //@Column(name = "status", nullable = false)
    private String status;
    //@Column(name = "skill", nullable = false)
    private String skill;


    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "JamaahEntity{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", alamat='" + alamat + '\'' +
                ", status='" + status + '\'' +
                ", skill='" + skill + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
