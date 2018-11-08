package yashchuk.DTO;

import yashchuk.controller.StudentController;
import yashchuk.domain.Clazz;
import yashchuk.exceptions.NoSuchSubjectException;
import yashchuk.exceptions.NoSuchClazzException;
import yashchuk.exceptions.NoSuchStudentException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ClazzDTO extends ResourceSupport {
    Clazz clazz;
    public ClazzDTO(Clazz clazz, Link selfLink) throws NoSuchStudentException, NoSuchSubjectException, NoSuchClazzException {
        this.clazz = clazz;
        add(selfLink);
        add(linkTo(methodOn(StudentController.class).getStudentsByClazzID(clazz.getId())).withRel("students"));
    }

    public Long getClazzId() { return clazz.getId(); }

    public String getClazz() {
        return clazz.getClazz();
    }
}
