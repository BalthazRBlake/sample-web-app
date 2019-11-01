package co.com.fhhf.deploymentfullapp.model;

/**
 *
 * @author FHHF
 */
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Person implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_person")
    private Integer idPerson;
    
    @Column(name="name")
    @NotEmpty
    @Size(min=3, max=30)
    private String name;
    
    @NotEmpty
    @Size(min=3, max=30)
    private String surname;
    
    @NotEmpty
    @Email
    private String email;
    
    private String phone;
    
    public Person(){}
    
    public Person(Integer idPersona){
        this.idPerson = idPersona;
    }
    
    public Person(String name, String surname, String email, String phone){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }
    
    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person{" + "idPerson=" + idPerson + ", name=" + name + ", surname=" + surname + ", email=" + email + ", phone=" + phone + '}';
    }
    
}