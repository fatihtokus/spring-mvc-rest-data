package hello;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SpitterRepository extends JpaRepository<Spitter, Long>,SpitterSweeper {
	List<Spitter> findByUserName(String userName);
    List<Spitter> findByLastName(String lastName);
    List<Spitter> readByFirstNameOrLastName(String first, String last);
    //List<Spitter> readByFirstNameIgnoringCaseOrLastNameIgnoresCase(String first, String last);
    @Query("select s from Spitter s where s.userName like '%John'")
    List<Spitter> findAllGmailSpitters();
}
