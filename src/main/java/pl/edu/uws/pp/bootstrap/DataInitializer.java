package pl.edu.uws.pp.bootstrap;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.uws.pp.domain.entity.User;
import pl.edu.uws.pp.domain.enums.Role;
import pl.edu.uws.pp.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(@NonNull ApplicationArguments args){
        if (userRepository.existsByRole(Role.HOUSING_MANAGER)) {
            return;
        }

        User user = User.builder()
                .name("Patryk")
                .surname("Rusakowicz")
                .pesel("01240612335")
                .email("patrykrusakowicz@housing.local")
                .password(passwordEncoder.encode("admin123"))
                .role(Role.HOUSING_MANAGER)
                .build();
        userRepository.save(user);
    }
}
