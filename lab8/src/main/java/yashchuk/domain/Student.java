package yashchuk.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", nullable = false)
    private Long id;
    @Column(name = "last_name", nullable = false, length = 20)
    private String surname;
    @Column(name = "first_name", nullable = false, length = 20)
    private String name;
    @Column(name = "second_name", nullable = false, length = 20)
    private String second_name;
    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "class_id")
    private Clazz clazz;
    @ManyToMany
    @JoinTable(name = "student_debt",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "student_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "subject_id", nullable = false))
    private Set<Subject> subjects;

    Student() {
    }

    Student(String surname, String name, String second_name) {
        this.surname = surname;
        this.name = name;
        this.second_name = second_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long idStudent) {
        this.id = idStudent;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student that = (Student) o;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (second_name != null ? !second_name.equals(that.second_name) : that.second_name != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (second_name != null ? second_name.hashCode() : 0);
        return result;
    }
}
