package library.security.controller;


import library.api.responceEntity.EntityResponse;
import library.security.service.RoleService;
import library.security.userdetail.UserDetailServiceAlter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/manager")
public class ManagerController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserDetailServiceAlter userDetailServices;

    @GetMapping(value = "/role/all")
    public EntityResponse<Object> getRoleAll(){
        return new EntityResponse<>(HttpStatus.OK,"successfull",roleService.getAll());
    }

    @GetMapping(value = "/user/all")
    public EntityResponse<Object> getUserAll(){
        return new EntityResponse<>(HttpStatus.OK,"successfull", userDetailServices.getAll());
    }

}
