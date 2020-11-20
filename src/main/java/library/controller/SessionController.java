package library.controller;

import library.entity.Session;
import library.exceptionhandle.responceEntity.EntityResponse;
import library.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
public class SessionController {
    @Autowired
    SessionService sessionService;

    @RequestMapping(value = "/session/showall", method = RequestMethod.GET)
    public EntityResponse<List<Session>> getList() {
        return new EntityResponse<>(HttpStatus.OK, "find all session", sessionService.getAll());
    }

    @RequestMapping(value = "/session/{id}", method = RequestMethod.GET)
    public EntityResponse<Session> getID(@PathVariable Long id) {
        return new EntityResponse<>(HttpStatus.OK, "get by id = " + id, sessionService.getbyID(id));
    }

    @RequestMapping(value = "/session/create", method = RequestMethod.POST)
    public void create(@RequestBody Session session) {
        sessionService.create(session);
    }

    @RequestMapping(value = "/session/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id) {
        sessionService.delete(id);
        return "delete successful";
    }
}
