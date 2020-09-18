/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudioMedico.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Chahir Chalouati
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
@Entity
@Table(name = "Elements", uniqueConstraints = @UniqueConstraint(name = "name", columnNames = "name"))
public class Element implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFood;
    @Column(nullable = false)
    @NotBlank(message = "name must not be blank")
    private String name;
    @ManyToOne(targetEntity = TypeFood.class)
    private TypeFood typeFood;
    @Column(nullable = false)
    @NotBlank(message = "quantity must not be blank")
    private Double quantity;
    @Column(nullable = false)
    @NotBlank(message = "minmum must not be blank")
    private Double minmum;
    @Column(nullable = false)
    @NotBlank(message = "maximum must not be blank")
    private Double maximum;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createdAt;
}
