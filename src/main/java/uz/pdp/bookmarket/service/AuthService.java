package uz.pdp.bookmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.bookmarket.entity.User;
import uz.pdp.bookmarket.entity.enums.RoleName;
import uz.pdp.bookmarket.exception.ResourceNotFoundException;
import uz.pdp.bookmarket.payload.ApiResponse;
import uz.pdp.bookmarket.payload.RegisterDto;
import uz.pdp.bookmarket.repository.RoleRepository;
import uz.pdp.bookmarket.repository.UserRepository;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

   @Autowired
   PasswordEncoder passwordEncoder;



    public ApiResponse register(RegisterDto registerDto) {
        if (!registerDto.getPrePassword().equals(registerDto.getPassword())){
            return new ApiResponse("parollar mos emas",false);
        }

        boolean existsedByUsername = userRepository.existsByUsername(registerDto.getUsername());
        if (existsedByUsername){
            return new ApiResponse("bunday username mavjud",false);
        }

        User user = new User(
                registerDto.getFirstname(),
                registerDto.getLastname(),
                registerDto.getUsername(),
                passwordEncoder.encode( registerDto.getPassword()),
                roleRepository.findByName(RoleName.ROLE_USER.name()).orElseThrow(() ->new ResourceNotFoundException("role","name", RoleName.ROLE_USER)),
                true
        );
        userRepository.save(user);
        return  new ApiResponse("Muvoffaqiyatli ro'yxatdan o'tdingiz",true);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
