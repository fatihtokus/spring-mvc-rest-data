package hello;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Spitter {
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
private Long id;
@NotNull
@Size(min=2, max=16)
private String firstName;
@NotNull
@Size(min=2, max=16)
private String lastName;
@NotNull
@Size(min=2, max=16)
private String userName;
@NotNull
@Size(min=2, max=25)
private String password;

private Date createDate;

public Spitter(){
	this(null,null,null,null,null,null);
}


public Spitter(Long id, String firstName, String lastName) {
	this(id,firstName,lastName,null,null,null);
}




public Spitter(Long id, String firstName, String lastName, String userName, String password, Date createDate) {
	super();
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.userName = userName;
	this.password = password;
	this.createDate = createDate;
}


@Override
public String toString() {
	return "Spitter [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
			+ ", password=" + password + ", createDate=" + createDate + "]";
}


public Date getCreateDate() {
	return createDate;
}


public void setCreateDate(Date createDate) {
	this.createDate = createDate;
}


public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getUserName() {
	return userName;
}
public void setUserName(String username) {
	this.userName = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

}
