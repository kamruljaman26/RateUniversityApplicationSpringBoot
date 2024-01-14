package feedback.application.feedback.service;

import feedback.application.feedback.model.Student;
import feedback.application.feedback.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder =
            new BCryptPasswordEncoder(); // password encryption from spring security
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> findStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Student findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    public Student registerStudent(Student student) {
        // Check if email is already in use
        Optional<Student> existingStudent = Optional.ofNullable(studentRepository.findByEmail(student.getEmail()));
        if (existingStudent.isPresent()) {
            throw new IllegalStateException("Email already in use");
        }
        // Encrypt the password before saving (use a proper encryption mechanism)
        student.setPassword(encryptPassword(student.getPassword()));
        return studentRepository.save(student);
    }

//    public boolean authenticateStudent(String email, String password) {
//        Optional<Student> student = Optional.ofNullable(studentRepository.findByEmail(email));
//        return student.filter(value -> checkPassword(password, value.getPassword())).isPresent(); // Compare encrypted password
//    }

    // Utility methods for password encryption
    private String encryptPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    // Utility methods for encrypted password checking
    private boolean checkPassword(String rawPassword, String encryptedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encryptedPassword);
    }
}

