package yashchuk.controller;

import yashchuk.DTO.ClazzDTO;
import yashchuk.domain.Clazz;
import yashchuk.exceptions.ExistsStudentsForClazzException;
import yashchuk.exceptions.NoSuchSubjectException;
import yashchuk.exceptions.NoSuchClazzException;
import yashchuk.exceptions.NoSuchStudentException;
import yashchuk.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class ClazzController {
    @Autowired
    ClazzService clazzService;

    @GetMapping(value = "/api/clazz")
    public ResponseEntity<List<ClazzDTO>> getAllClazz() throws NoSuchStudentException, NoSuchSubjectException, NoSuchClazzException {
        List<Clazz> clazzList = clazzService.getAllClazz();
        Link link = linkTo(methodOn(ClazzController.class).getAllClazz()).withSelfRel();

        List<ClazzDTO> clazzDTO = new ArrayList<>();
        for (Clazz entity : clazzList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            ClazzDTO dto = new ClazzDTO(entity, selfLink);
            clazzDTO.add(dto);
        }

        return new ResponseEntity<>(clazzDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/clazz/{class_id}")
    public ResponseEntity<ClazzDTO> getClazz(@PathVariable Long class_id) throws NoSuchClazzException, NoSuchStudentException, NoSuchSubjectException {
        Clazz clazz = clazzService.getClazz(class_id);
        Link link = linkTo(methodOn(ClazzController.class).getClazz(class_id)).withSelfRel();

        ClazzDTO clazzDTO = new ClazzDTO(clazz, link);

        return new ResponseEntity<>(clazzDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/clazz/{class_id}")
    public  ResponseEntity<ClazzDTO> addClazz(@RequestBody Clazz newClazz) throws NoSuchClazzException, NoSuchStudentException, NoSuchSubjectException {
        clazzService.createClazz(newClazz);
        Link link = linkTo(methodOn(ClazzController.class).getClazz(newClazz.getId())).withSelfRel();

        ClazzDTO clazzDTO = new ClazzDTO(newClazz, link);

        return new ResponseEntity<>(clazzDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/clazz/{class_id}")
    public  ResponseEntity<ClazzDTO> updateClazz(@RequestBody Clazz uСlazz, @PathVariable Long class_id) throws NoSuchClazzException, NoSuchStudentException, NoSuchSubjectException {
        clazzService.updateClazz(uСlazz, class_id);
        Clazz clazz = clazzService.getClazz(class_id);
        Link link = linkTo(methodOn(ClazzController.class).getClazz(class_id)).withSelfRel();

        ClazzDTO clazzDTO = new ClazzDTO(clazz, link);

        return new ResponseEntity<>(clazzDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/clazz/{class_id}")
    public  ResponseEntity deleteClazz(@PathVariable Long class_id) throws NoSuchClazzException, ExistsStudentsForClazzException {
        clazzService.deleteClazz(class_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
