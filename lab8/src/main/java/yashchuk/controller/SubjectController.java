package yashchuk.controller;

import yashchuk.DTO.SubjectDTO;
import yashchuk.domain.Subject;
import yashchuk.exceptions.ExistsStudentForSubjectException;
import yashchuk.exceptions.NoSuchSubjectException;
import yashchuk.exceptions.NoSuchStudentException;
import yashchuk.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @GetMapping(value = "/api/subject/student/{student_id}")
    public ResponseEntity<List<SubjectDTO>> getSubjectsByStudentID(@PathVariable Long student_id) throws NoSuchStudentException, NoSuchSubjectException {
        Set<Subject> subjectList = subjectService.getSubjectsByStudentId(student_id);
        Link link = linkTo(methodOn(SubjectController.class).getAllSubjects()).withSelfRel();

        List<SubjectDTO> subjectsDTO = new ArrayList<>();
        for (Subject entity : subjectList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            SubjectDTO dto = new SubjectDTO(entity, selfLink);
            subjectsDTO.add(dto);
        }

        return new ResponseEntity<>(subjectsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/subject/{subject_id}")
    public ResponseEntity<SubjectDTO> getSubject(@PathVariable Long subject_id) throws NoSuchSubjectException, NoSuchStudentException {
        Subject subject = subjectService.getSubject(subject_id);
        Link link = linkTo(methodOn(SubjectController.class).getSubject(subject_id)).withSelfRel();

        SubjectDTO subjectDTO = new SubjectDTO(subject, link);

        return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/subject")
    public ResponseEntity<List<SubjectDTO>> getAllSubjects() throws NoSuchSubjectException, NoSuchStudentException {
        List<Subject> subjectList = subjectService.getAllSubjects();
        Link link = linkTo(methodOn(SubjectController.class).getAllSubjects()).withSelfRel();

        List<SubjectDTO> subjectsDTO = new ArrayList<>();
        for (Subject entity : subjectList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            SubjectDTO dto = new SubjectDTO(entity, selfLink);
            subjectsDTO.add(dto);
        }

        return new ResponseEntity<>(subjectsDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/subject")
    public ResponseEntity<SubjectDTO> addSubject(@RequestBody Subject newSubject) throws NoSuchSubjectException, NoSuchStudentException {
        subjectService.createSubject(newSubject);
        Link link = linkTo(methodOn(SubjectController.class).getSubject(newSubject.getId())).withSelfRel();

        SubjectDTO subjectDTO = new SubjectDTO(newSubject, link);

        return new ResponseEntity<>(subjectDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/subject/{subject_id}")
    public ResponseEntity<SubjectDTO> updateSubject(@RequestBody Subject uSubject, @PathVariable Long subject_id) throws NoSuchSubjectException, NoSuchStudentException {
        subjectService.updateSubject(uSubject, subject_id);
        Subject subject = subjectService.getSubject(subject_id);
        Link link = linkTo(methodOn(SubjectController.class).getSubject(subject_id)).withSelfRel();

        SubjectDTO subjectDTO = new SubjectDTO(subject, link);

        return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/subject/{subject_id}")
    public  ResponseEntity deleteSubject(@PathVariable Long subject_id) throws ExistsStudentForSubjectException, NoSuchSubjectException {
        subjectService.deleteSubject(subject_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
