package svms.repository;

import svms.entity.Student;

import java.util.List;

public interface StudentRepository {

    List<Student> findAllStudents();

    Student findStudentById(Long studentId);

    List<Student> findStudentByStatus(String status);

    int saveStudent(Student student);

    int deleteStudent(Long studentId);

    int updateStudentStatus(Long studentId, String status);

    int updateStudentFee(Long studentId, Long fee);

    int cleanUp();

}
