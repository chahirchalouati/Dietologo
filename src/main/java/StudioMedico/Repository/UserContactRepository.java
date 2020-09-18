package StudioMedico.Repository;

import StudioMedico.Model.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContactRepository extends JpaRepository<UserContact,Long> {
}
