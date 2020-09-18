package StudioMedico.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Users")
@Table(name = "Users")
@NamedQueries({
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM Users u WHERE u.email=:email"),
    @NamedQuery(name = "User.findByIdUser", query = "SELECT u FROM Users u WHERE u.idUser=:idUser"),
    @NamedQuery(name = "User.findByUserName", query = "SELECT u FROM Users u WHERE u.username=:username")

})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    @NotBlank(message = "firstname must not be blank")
    private String firstname;
    @NotBlank(message = "lastname must not be blank")
    private String lastname;
    @NotBlank(message = "username must not be blank")
    @Column(unique = true)
    private String username;
    @NotBlank(message = "email must not be blank")
    @Email(message = "Invalid email")
    @Column(unique = true)
    private String email;
    @JsonProperty(access = Access.WRITE_ONLY)
    @Lob
    @NotBlank(message = "password must not be blank")
    private String password;
    @ManyToOne(targetEntity = Authoritie.class)
    @JsonProperty(access = Access.READ_ONLY)
    private Authoritie authoritie;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(access = Access.READ_ONLY)
    private Date createdAt;

}
