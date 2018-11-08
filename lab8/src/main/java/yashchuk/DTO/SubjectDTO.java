package yashchuk.DTO;

import yashchuk.controller.StudentController;
import yashchuk.domain.Subject;
import yashchuk.exceptions.NoSuchSubjectException;
import yashchuk.exceptions.NoSuchStudentException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class SubjectDTO extends ResourceSupport {
    Subject subject;
    public SubjectDTO(Subject subject, Link selfLink) throws NoSuchSubjectException, NoSuchStudentException {
        this.subject = subject;
        add(selfLink);
        add(linkTo(methodOn(StudentController.class).getStudentsBySubjectID(subject.getId())).withRel("students"));
    }

    public Long getSubjectId() {
        return subject.getId();
    }

    public String getSubjectName() {
        return subject.getSubjectName();
    }

}
