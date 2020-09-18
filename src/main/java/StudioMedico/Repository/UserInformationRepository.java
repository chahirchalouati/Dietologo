/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudioMedico.Repository;

import StudioMedico.Model.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Chahir Chalouati
 */
public interface UserInformationRepository extends JpaRepository<UserInformation, Long> {

    UserInformation findBySecurityCode(@Param("securityCode") String securityCode);

    @Query(value = "SELECT CASE  WHEN count(u)> 0 THEN true ELSE false END FROM UserInformation u WHERE u.securityCode=:securityCode ")
    boolean existBySecurityCode(@Param("securityCode") String securityCode);
}
