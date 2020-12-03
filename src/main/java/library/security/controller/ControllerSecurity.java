package library.security.controller;

import library.api.responceEntity.EntityResponse;
import library.security.model.User;
import library.security.userdetail.UserDetailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerSecurity {
    @Autowired
    UserDetailServices serviceUser;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public EntityResponse<Object> login(){
        return new EntityResponse<>(HttpStatus.OK,"login successful",null);
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public EntityResponse<Object> register(@RequestBody User user){
        serviceUser.save(user);
        return new EntityResponse<>(HttpStatus.OK,"register successful",null);
    }


}