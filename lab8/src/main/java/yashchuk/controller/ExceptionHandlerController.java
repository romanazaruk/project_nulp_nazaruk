package yashchuk.controller;

import yashchuk.DTO.MessageDTO;
import yashchuk.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchClazzException.class)
    ResponseEntity<MessageDTO> handleNoSushClazzException(){
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such class not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchStudentException.class)
    ResponseEntity<MessageDTO> handleNoSushStudentException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such student not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchSubjectException.class)
    ResponseEntity<MessageDTO> handleNoSushSubjectException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such subject not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExistsStudentsForClazzException.class)
    ResponseEntity<MessageDTO> handleExistsStudentsForClazzException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Delete imposible. There are student for this class"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ExistsSubjectsForStudentsException.class)
    ResponseEntity<MessageDTO> handleExistsSubjectsForStudentException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Delete imposible. There are subject for this student"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ExistsStudentForSubjectException.class)
    ResponseEntity<MessageDTO> handleExistsStudentsForSubjectException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Delete imposible. There are students for this subject"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AlreadyExistsSubjectInStudentException.class)
    ResponseEntity<MessageDTO> handleAlreadyExistsSubjectInStudentExceptionException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Add imposible. The student already contain this subject"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(SubjectAbsentException.class)
    ResponseEntity<MessageDTO> handleSubjectAbsentException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Now this subject is absent"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StudentHasNotSubjectException.class)
    ResponseEntity<MessageDTO> handleStudentHasNotSubjectException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("The student hasn't this subject"), HttpStatus.NOT_FOUND);
    }


}
