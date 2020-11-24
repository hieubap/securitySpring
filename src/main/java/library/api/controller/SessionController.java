package library.api.controller;

import library.api.entity.Session;
import library.api.exceptionhandle.responceEntity.EntityResponse;
import library.api.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
public class SessionController {// xu li trong service
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
    public EntityResponse<Session> create(@RequestBody Session session) {
        sessionService.create(session);
        return new EntityResponse<>(HttpStatus.OK,"create Session successful",session);
    }
    @RequestMapping(value = "/session/update", method = RequestMethod.POST)
    public EntityResponse<Session> update(@RequestBody Session session,@RequestParam Long id) {
        return new EntityResponse<>(HttpStatus.OK,"create Session successful",
                sessionService.update(session,id));
    }

    @RequestMapping(value = "/session/{id}", method = RequestMethod.DELETE)
    public EntityResponse<Object> delete(@PathVariable Long id) {
        sessionService.delete(id);
        return new EntityResponse<>(HttpStatus.OK, "delete successful",null);
    }
}
