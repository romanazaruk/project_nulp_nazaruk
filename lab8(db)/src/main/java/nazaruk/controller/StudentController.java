package nazaruk.controller;

import nazaruk.DTO.StudentDTO;
import nazaruk.domain.Student;
import nazaruk.exceptions.*;
import nazaruk.service.StudentService;
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
public class StudentController {
    @Autowired
    StudentService studentService;
// get student by class id
    @GetMapping(value = "/api/student/clazz/{class_id}")
    public ResponseEntity<List<StudentDTO>> getStudentsByClazzID(@PathVariable Long class_id) throws NoSuchClazzException, NoSuchStudentException, NoSuchSubjectException {
        List<Student> studentList = studentService.getStudentByClazzId(class_id);

        Link link = linkTo(methodOn(StudentController.class).getAllStudents()).withSelfRel();

        List<StudentDTO> studentsDTO = new ArrayList<>();
        for (Student entity : studentList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            StudentDTO dto = new StudentDTO(entity, selfLink);
            studentsDTO.add(dto);
        }

        return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
    }
// get student
    @GetMapping(value = "/api/student/{student_id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable Long student_id) throws NoSuchStudentException, NoSuchSubjectException {
        Student student = studentService.getStudent(student_id);
        Link link = linkTo(methodOn(StudentController.class).getStudent(student_id)).withSelfRel();

        StudentDTO studentDTO = new StudentDTO(student, link);

        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }
// get all students
    @GetMapping(value = "/api/student")
    public ResponseEntity<List<StudentDTO>> getAllStudents() throws NoSuchStudentException, NoSuchSubjectException {
        List<Student> studentList = studentService.getAllStudents();
        Link link = linkTo(methodOn(StudentController.class).getAllStudents()).withSelfRel();

        List<StudentDTO> studentsDTO = new ArrayList<>();
        for (Student entity : studentList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            StudentDTO dto = new StudentDTO(entity, selfLink);
            studentsDTO.add(dto);
        }

        return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
    }
// get student
    @GetMapping(value = "/api/student/subject/{subject_id}")
    public ResponseEntity<List<StudentDTO>> getStudentsBySubjectID(@PathVariable Long subject_id) throws NoSuchSubjectException, NoSuchStudentException {
        Set<Student> studentList = studentService.getStudentsBySubjectId(subject_id);
        Link link = linkTo(methodOn(StudentController.class).getAllStudents()).withSelfRel();

        List<StudentDTO> studentsDTO = new ArrayList<>();
        for (Student entity : studentList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            StudentDTO dto = new StudentDTO(entity, selfLink);
            studentsDTO.add(dto);
        }

        return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
    }
// add student
    @PostMapping(value = "/api/student/clazz/{class_id}")
    public  ResponseEntity<StudentDTO> addStudent(@RequestBody Student newStudent, @PathVariable Long class_id)
            throws NoSuchClazzException, NoSuchStudentException, NoSuchSubjectException {
        studentService.createStudent(newStudent, class_id);
        Link link = linkTo(methodOn(StudentController.class).getStudent(newStudent.getId())).withSelfRel();

        StudentDTO studentDTO = new StudentDTO(newStudent, link);

        return new ResponseEntity<>(studentDTO, HttpStatus.CREATED);
    }
//update student
    @PutMapping(value = "/api/student/{student_id}/clazz/{clazz_id}")
    public  ResponseEntity<StudentDTO> updateStudent(@RequestBody Student uStudent,
                                                     @PathVariable Long student_id, @PathVariable Long class_id)
            throws NoSuchClazzException, NoSuchStudentException, NoSuchSubjectException {
        studentService.updateStudent(uStudent, student_id, class_id);
        Student student = studentService.getStudent(student_id);
        Link link = linkTo(methodOn(StudentController.class).getStudent(student_id)).withSelfRel();

        StudentDTO studentDTO = new StudentDTO(student, link);

        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/student/{student_id}")
    public  ResponseEntity deleteStudent(@PathVariable Long student_id) throws NoSuchStudentException, ExistsSubjectsForStudentsException {
        studentService.deleteStudent(student_id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/api/student/{student_id}/subject/{subject_id}")
    public  ResponseEntity<StudentDTO> addSubjectForstudent(@PathVariable Long student_id, @PathVariable Long subject_id)
            throws NoSuchStudentException, NoSuchSubjectException, AlreadyExistsSubjectInStudentException, SubjectAbsentException {
        studentService.addSubjectForStudent(student_id,subject_id);
        Student student = studentService.getStudent(student_id);
        Link link = linkTo(methodOn(StudentController.class).getStudent(student_id)).withSelfRel();

        StudentDTO studentDTO = new StudentDTO(student, link);

        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/student/{student_id}//{subject_id}")
    public  ResponseEntity<StudentDTO> removeSubjectForStudent(@PathVariable Long student_id, @PathVariable Long subject_id)
            throws NoSuchStudentException, NoSuchSubjectException, StudentHasNotSubjectException {
        studentService.removeSubjectForStudent(student_id,subject_id);
        Student student = studentService.getStudent(student_id);
        Link link = linkTo(methodOn(StudentController.class).getStudent(student_id)).withSelfRel();

        StudentDTO studentDTO = new StudentDTO(student, link);

        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

}

