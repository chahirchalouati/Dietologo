/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudioMedico.Repository;

import StudioMedico.Model.BloodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Chahir Chalouati
 */
public interface BloodTypeRepository extends JpaRepository<BloodType, Long> {

    BloodType findByBloodType(@Param("bloodType") String bloodType);

}
