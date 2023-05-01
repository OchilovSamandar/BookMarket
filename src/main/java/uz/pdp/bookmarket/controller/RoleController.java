package uz.pdp.bookmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.bookmarket.entity.Role;
import uz.pdp.bookmarket.payload.ApiResponse;

import uz.pdp.bookmarket.payload.RoleDto;
import uz.pdp.bookmarket.service.RoleService;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    RoleService roleService;


    @PreAuthorize(value = "hasAnyAuthority('ADD_ROLE')")
    @PostMapping()
    public HttpEntity<?> addRole(@Valid @RequestBody RoleDto roleDto) {
        ApiResponse apiResponse = roleService.addRole(roleDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('EDIT_ROLE')")
    @PutMapping("/{id}")
    public HttpEntity<?> editRole(@PathVariable Long id,
                                  @Valid @RequestBody RoleDto roleDto) {
        ApiResponse apiResponse = roleService.editRole(id, roleDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('DELETE_ROLE')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteRole(@PathVariable Long id) {
        ApiResponse apiResponse = roleService.deleteRole(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('VIEW_ROLE')")
    @GetMapping
    public HttpEntity<List<Role>> getRoles() {
        List<Role> roleList = roleService.getRoles();
        return ResponseEntity.ok(roleList);
    }

    @PreAuthorize(value = "hasAnyAuthority('VIEW_ROLE')")
    @GetMapping("{id}")
    public HttpEntity<?> getRole(@PathVariable Long id){
        ApiResponse apiResponse = roleService.getRole(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
