package yashchuk.service;

import yashchuk.Repository.ClazzRepository;
import yashchuk.Repository.StudentRepository;
import yashchuk.domain.Clazz;
import yashchuk.exceptions.ExistsStudentsForClazzException;
import yashchuk.exceptions.NoSuchClazzException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClazzService {
    @Autowired
    ClazzRepository clazzRepository;
    private boolean ascending;

    @Autowired
    StudentRepository studentRepository;

    public List<Clazz> getAllClazz() {
        return clazzRepository.findAll();
    }

    public Clazz getClazz(Long class_id) throws NoSuchClazzException {
//        Clazz clazz =diagnosisRepository.findOne(diagnosis_id);//1.5.9
        Clazz clazz = clazzRepository.findById(class_id).get();//2.0.0.M7
        if (clazz == null) throw new NoSuchClazzException();
        return clazz;
    }

    @Transactional
    public void createClazz(Clazz clazz) {
        clazzRepository.save(clazz);
    }

    @Transactional
    public void updateClazz(Clazz uClazz, Long class_id) throws NoSuchClazzException {
//        Clazz clazz = diagnosisRepository.findOne(diagnosis_id);//1.5.9
        Clazz clazz = clazzRepository.findById(class_id).get();//2.0.0.M7

        if (clazz == null) throw new NoSuchClazzException();
        clazz.setClazz(uClazz.getClazz());
        clazzRepository.save(clazz);
    }

    @Transactional
    public void deleteClazz(Long class_id) throws NoSuchClazzException, ExistsStudentsForClazzException {
//        Clazz clazz = diagnosisRepository.findOne(diagnosis_id);//1.5.9
        Clazz clazz = clazzRepository.findById(class_id).get();//2.0.0.M7
        if (clazz == null) throw new NoSuchClazzException();
        if (clazz.getStudents().size() != 0) throw new ExistsStudentsForClazzException();
        clazzRepository.delete(clazz);
    }


}
