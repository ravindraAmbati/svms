package svms.repository;

import svms.entity.Class;

import java.util.List;

public interface ClassRepository {

    List<Class> findAllClasses();

    Class findClassById(Long classId);

    List<Class> findClassesByEmployeeId(Long employeeId);

    List<Class> findClassesByStatus(String status);

    int saveClass(Class aClass);

    int deleteClass(Long classId);

    int updateClassStatus(Long classId, String status);

    int updateEmployeeId(Long classId, String status);

    int cleanUp();

}
