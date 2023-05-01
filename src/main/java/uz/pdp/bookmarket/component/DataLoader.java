package uz.pdp.bookmarket.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.bookmarket.entity.Role;
import uz.pdp.bookmarket.entity.User;
import uz.pdp.bookmarket.entity.enums.PermissionName;
import uz.pdp.bookmarket.entity.enums.RoleName;
import uz.pdp.bookmarket.repository.RoleRepository;
import uz.pdp.bookmarket.repository.UserRepository;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    private String initMode ;


    @Override
    public void run(String... args) throws Exception {

        if (initMode.equals("always")){
            PermissionName[] permissionNames = PermissionName.values();
            Role admin = roleRepository.save(new Role(
                    RoleName.ROLE_ADMIN.name(),
                    "Bu admin, sistema egasi",
                    Arrays.asList(permissionNames)
            ));

            Role user = roleRepository.save(new Role(
                    RoleName.ROLE_USER.name(),
                    "bu user, oddiy foydalanuvchi",
                    Arrays.asList(PermissionName.VIEW_BOOK, PermissionName.VIEW_BASKET, PermissionName.ADD_BASKET)
            ));

            userRepository.save(new User(
                    "Admin",
                    "Adminov",
                    "admin",
                    passwordEncoder.encode( "admin123"),
                    admin,
                    true

            ));

            userRepository.save(new User(
                    "User",
                    "Userov",
                    "user",
                    passwordEncoder.encode( "user123"),
                    user,
                    true

            ));
        }

    }
}
