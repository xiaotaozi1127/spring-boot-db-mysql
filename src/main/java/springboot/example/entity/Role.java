package springboot.example.entity;

import javax.persistence.*;

@Entity
@Table(name ="role")
public class Role implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Role() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }
}
