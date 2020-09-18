package StudioMedico.Service;

import StudioMedico.Model.User;
import StudioMedico.Repository.AuthoritieRepository;
import StudioMedico.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthoritieRepository authoritieRepository;
    private BCryptPasswordEncoder encoder;

    /**
     * register new user
     *
     * @param user
     */
    public void register(User user) {

        // encoding passsword for user
        encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword().trim()));
        user.setAuthoritie(authoritieRepository.findByAuthoritie("USER"));
        userRepository.save(user);

    }

}
