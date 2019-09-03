package svms.Repository;

import svms.Entity.Person;

import java.util.List;

public interface PersonRepository {

    List<Person> findAllPersons();

    Person findPersonById(Long personId);

    Person findPersonByFirstName(String username);

    List<Person> findPersonByStatus(String status);

    List<Person> findPersonByGender(String gender);

    int savePerson(Person person);

    int deletePerson(Long personId);

    int updatePersonStatus(Long personId, String status);

    int cleanUp();

}
