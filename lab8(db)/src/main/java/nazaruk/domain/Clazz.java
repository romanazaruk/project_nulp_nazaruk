package nazaruk.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Class")
public class Clazz {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id", nullable = false)
    private Long id;
    @Column(name = "number_class", nullable = false, length = 20)
    private String number_class;
    @Column(name = "school", nullable = false, length = 20)
    private String school;
    @OneToMany(mappedBy = "clazz")
    private List<Student> students;

    Clazz(){}
    Clazz(String number_class){
        this.number_class=number_class;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long idClazz) {
        this.id = idClazz;
    }

    public String getClazz() {
        return number_class;
    }
    public void setClazz(String number_class) {
        this.number_class = number_class;
    }

    public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school = school;
    }

    public List<Student> getStudents() {
        return students;
    }
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clazz that = (Clazz) o;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (number_class != null ? !number_class.equals(that.number_class) : that.number_class != null) return false;
        if (school != null ? !school.equals(that.school) : that.school != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (number_class != null ? number_class.hashCode() : 0);
        result = 31 * result + (school != null ? school.hashCode() : 0);
        return result;
    }
}
