package uz.pdp.bookmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.bookmarket.entity.Role;
import uz.pdp.bookmarket.payload.ApiResponse;
import uz.pdp.bookmarket.payload.RoleDto;
import uz.pdp.bookmarket.repository.RoleRepository;


import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;


    public ApiResponse addRole(RoleDto roleDto) {
        if(roleRepository.existsByName(roleDto.getName()))
            return new ApiResponse("bunday role mavjud",false);

        Role role = new Role(
                roleDto.getName(),
                roleDto.getDescription(),
                roleDto.getPermissionNameList()  );
        roleRepository.save(role);

        return new ApiResponse("saqlandi" , true);
    }

    public ApiResponse editRole(Long id, RoleDto roleDto) {
        boolean existsById = roleRepository.existsById(id);
        if (existsById){
            Optional<Role> byId = roleRepository.findById(id);
            Role role = byId.get();
            role.setName(roleDto.getName());
            role.setDescription(roleDto.getDescription());
            role.setPermissionList(roleDto.getPermissionNameList());
            roleRepository.save(role);

            return new ApiResponse("successfully edited",true);

        }

        return new ApiResponse("not found role",false);
    }

    public ApiResponse deleteRole(Long id) {
        boolean existsById = roleRepository.existsById(id);
        if (existsById){
            roleRepository.deleteById(id);
            return new ApiResponse("Role successfully deleted",true);
        }
        return new ApiResponse("Not found role",false);

    }

    public List<Role> getRoles() {
        List<Role> roleList = roleRepository.findAll();

        return roleList;
    }

    public ApiResponse getRole(Long id) {
        try {
            Optional<Role> byId = roleRepository.findById(id);
            return new ApiResponse("Success",true,byId);
        }catch (Exception e){
            return new ApiResponse("not success",false);

        }
    }
}
