package uz.pdp.bookmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uz.pdp.bookmarket.service.UserService;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

//    @PreAuthorize(value = "hasAnyAuthority('ADD_USER')")
//    @PostMapping("/add")
//    public HttpEntity<?> addUser
}
