package nazaruk.service;

import nazaruk.Repository.SubjectRepository;
import nazaruk.Repository.StudentRepository;
import nazaruk.domain.Student;
import nazaruk.domain.Subject;
import nazaruk.exceptions.ExistsStudentForSubjectException;
import nazaruk.exceptions.NoSuchSubjectException;
import nazaruk.exceptions.NoSuchStudentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    StudentRepository studentRepository;

    public Set<Subject> getSubjectsByStudentId(Long student_id) throws NoSuchStudentException {
//        Student student = patientRepository.findOne(patient_id);//1.5.9
        Student student = studentRepository.findById(student_id).get();//2.0.0.M7
        if (student == null) throw new NoSuchStudentException();
        return student.getSubjects();
    }

    public Subject getSubject(Long subject_id) throws NoSuchSubjectException {
//        Subject subject = medicineRepository.findOne(medicine_id);//1.5.9
        Subject subject = subjectRepository.findById(subject_id).get();//2.0.0.M7
        if (subject == null) throw new NoSuchSubjectException();
        return subject;
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Transactional
    public void createSubject(Subject subject) {
        subjectRepository.save(subject);
    }

    @Transactional
    public void updateSubject(Subject uSubject, Long subject_id) throws NoSuchSubjectException {
//        Subject subject = medicineRepository.findOne(medicine_id);//1.5.9
        Subject subject = subjectRepository.findById(subject_id).get();//2.0.0.M7
        if (subject == null) throw new NoSuchSubjectException();
        //update
        subject.setSubjectName(uSubject.getSubjectName());
    }

    @Transactional
    public void deleteSubject(Long subject_id) throws NoSuchSubjectException, ExistsStudentForSubjectException {
//        Subject subject = medicineRepository.findOne(medicine_id);//1.5.9
        Subject subject = subjectRepository.findById(subject_id).get();//2.0.0.M7

        if (subject == null) throw new NoSuchSubjectException();
        if (subject.getStudents().size() != 0) throw new ExistsStudentForSubjectException();
        subjectRepository.delete(subject);
    }
}
