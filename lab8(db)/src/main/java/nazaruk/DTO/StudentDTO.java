package nazaruk.DTO;

import nazaruk.controller.SubjectController;
import nazaruk.domain.Student;
import nazaruk.exceptions.NoSuchSubjectException;
import nazaruk.exceptions.NoSuchStudentException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class StudentDTO extends ResourceSupport {
    Student student;
    public StudentDTO(Student student, Link selfLink) throws NoSuchStudentException, NoSuchSubjectException {
        this.student = student;
        add(selfLink);
        add(linkTo(methodOn(SubjectController.class).getSubjectsByStudentID(student.getId())).withRel("subjects"));
    }

    public Long getStudentId() {
        return student.getId();
    }

    public String getSurname() {
        return student.getSurname();
    }

    public String getName() {
        return student.getName();
    }

    public String getSecond_name() {
        return student.getSecond_name();
    }

    public String getClazz() {
        if(student.getClazz()==null) return "";
        return student.getClazz().getClazz();
    }

}
