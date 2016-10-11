package hello;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... strings) throws Exception {

        log.info("Creating tables");

        jdbcTemplate.execute("DROP TABLE spitter IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE spitter(" +
                "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255),user_name VARCHAR(255), password VARCHAR(255), create_date Date)");

        // Split up the array of whole names into an array of first/last names
        List<Object[]> splitUpNames = Arrays.asList("John Woo user1 Woo 2001-01-01", "Jeff Dean user2 Woo 2001-01-01", "Josh Bloch user3 Woo 2001-01-01", "Josh Long user4 Woo 2001-01-01").stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());
      
        
        
        for (Object[] objects : splitUpNames) {
        	System.out.println("FFFFFFFFFFFFFFFFFFFFFFFf1 : "+LocalDate.parse(objects[4].toString()));
        	objects[4]=LocalDate.parse(objects[4].toString()).toString();
        	System.out.println("FFFFFFFFFFFFFFFFFFFFFFFf2 : "+LocalDate.parse(objects[4].toString()));
        	
		}
     // Use a Java 8 stream to print out each tuple of the list
        splitUpNames.forEach(name -> log.info(String.format("Inserting spitter record for %s %s %s %s %s", name[0], name[1],name[2],name[3],name[4])));
        
        
        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO spitter(first_name, last_name,user_name,password,create_date) VALUES (?,?,?,?,?)", splitUpNames);

        log.info("Querying for spitter records where first_name = 'Josh':");
        jdbcTemplate.query(
                "SELECT id, first_name, last_name, user_name, password,create_date FROM spitter WHERE first_name = ?", new Object[] { "Josh" },
                (rs, rowNum) -> new Spitter(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"),rs.getString("user_name"),rs.getString("password"),rs.getDate("create_date"))
        ).forEach(customer -> log.info(customer.toString()));
    }
}
