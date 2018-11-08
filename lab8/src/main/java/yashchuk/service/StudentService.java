package yashchuk.service;

import yashchuk.Repository.SubjectRepository;
import yashchuk.Repository.ClazzRepository;
import yashchuk.Repository.StudentRepository;
import yashchuk.domain.Student;
import yashchuk.domain.Subject;
import yashchuk.domain.Clazz;
import yashchuk.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ClazzRepository clazzRepository;

    @Autowired
    SubjectRepository subjectRepository;

    public List<Student> getStudentByClazzId(Long class_id) throws NoSuchClazzException {
//        Clazz clazz = diagnosisRepository.findOne(diagnosis_id);//1.5.9
        Clazz clazz = clazzRepository.findById(class_id).get();//2.0.0.M7
        if (clazz == null) throw new NoSuchClazzException();
        return clazz.getStudents();
    }

    public Student getStudent(Long student_id) throws NoSuchStudentException {
//        Student student = patientRepository.findOne(patient_id);//1.5.9
        Student student = studentRepository.findById(student_id).get();//2.0.0.M7
        if (student == null) throw new NoSuchStudentException();
        return student;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Set<Student> getStudentsBySubjectId(Long subject_id) throws NoSuchSubjectException {
//        Subject subject = medicineRepository.findOne(medicine_id);//1.5.9
        Subject subject = subjectRepository.findById(subject_id).get();//2.0.0.M7
        if (subject == null) throw new NoSuchSubjectException();
        return subject.getStudents();
    }

    @Transactional
    public void createStudent(Student student, Long clazz_id) throws NoSuchClazzException {
        if (clazz_id > 0) {
//            Clazz clazz = diagnosisRepository.findOne(diagnosis_id);//1.5.9
            Clazz clazz = clazzRepository.findById(clazz_id).get();//2.0.0.M7
            if (clazz == null) throw new NoSuchClazzException();
            student.setClazz(clazz);
        }
        studentRepository.save(student);
    }

    @Transactional
    public void updateStudent(Student uStudent, Long student_id, Long class_id) throws NoSuchClazzException, NoSuchStudentException {
//        Clazz clazz = diagnosisRepository.findOne(diagnosis_id);//1.5.9
        Clazz clazz = clazzRepository.findById(class_id).get();//2.0.0.M7
        if (class_id > 0) {
            if (clazz == null) throw new NoSuchClazzException();
        }
//        Student student = patientRepository.findOne(patient_id);//1.5.9
        Student student = studentRepository.findById(student_id).get();//2.0.0.M7
        if (student == null) throw new NoSuchStudentException();
        //update
        student.setSurname(uStudent.getSurname());
        student.setName(uStudent.getName());
        student.setSecond_name(uStudent.getSecond_name());
        if (class_id > 0) student.setClazz(clazz);
        else student.setClazz(null);
        studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(Long student_id) throws NoSuchStudentException, ExistsSubjectsForStudentsException {
//        Student student = patientRepository.findOne(patient_id);//1.5.9
        Student student = studentRepository.findById(student_id).get();//2.0.0.M7
        if (student == null) throw new NoSuchStudentException();
        if (student.getSubjects().size() != 0) throw new ExistsSubjectsForStudentsException();
        studentRepository.delete(student);
    }

    @Transactional
    public void addSubjectForStudent(Long student_id, Long subject_id)
            throws NoSuchStudentException, NoSuchSubjectException, AlreadyExistsSubjectInStudentException, SubjectAbsentException {
//        Student student = patientRepository.findOne(patient_id);//1.5.9
        Student student = studentRepository.findById(student_id).get();//2.0.0.M7
        if (student == null) throw new NoSuchStudentException();
//        Subject subject = medicineRepository.findOne(medicine_id);//1.5.9
        Subject subject = subjectRepository.findById(subject_id).get();//2.0.0.M7
        if (subject == null) throw new NoSuchSubjectException();
        if (student.getSubjects().contains(subject) == true) throw new AlreadyExistsSubjectInStudentException();
        student.getSubjects().add(subject);
        studentRepository.save(student);
    }

    @Transactional
    public void removeSubjectForStudent(Long student_id, Long subject_id)
            throws NoSuchStudentException, NoSuchSubjectException, StudentHasNotSubjectException {
//        Student student = patientRepository.findOne(patient_id);//1.5.9
        Student student = studentRepository.findById(student_id).get();//2.0.0.M7
        if (student == null) throw new NoSuchStudentException();
//        Subject subject = medicineRepository.findOne(medicine_id);//1.5.9
        Subject subject = subjectRepository.findById(subject_id).get();//2.0.0.M7
        if (subject == null) throw new NoSuchSubjectException();
        if (student.getSubjects().contains(subject) == false) throw new StudentHasNotSubjectException();
        student.getSubjects().remove(subject);
        studentRepository.save(student);
    }
}
