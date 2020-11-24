package library.api.controller;

import library.api.entity.CardLibrary;
import library.api.exceptionhandle.responceEntity.EntityResponse;
import library.api.service.CardService;
import library.api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
public class CardController{
    @Autowired
    CardService cardService;
    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/card/showall",method = RequestMethod.GET)
    public EntityResponse<List<CardLibrary>> getList(){
        return new EntityResponse<>(HttpStatus.OK,"find all card",cardService.getAll());
    }
    @RequestMapping(value = "/card/{id}",method = RequestMethod.GET)
    public EntityResponse<CardLibrary> getID(@PathVariable Long id){
        return new EntityResponse<>(HttpStatus.OK,"get by id = " + id,cardService.getbyID(id));
    }
    @RequestMapping(value = "/card/mssv={mssv}",method = RequestMethod.GET)
    public EntityResponse<CardLibrary> getByMssv(@PathVariable String mssv){
        return new EntityResponse<>(HttpStatus.OK,"get by id = " + mssv,cardService.getByMssv(mssv));
    }

    @RequestMapping(value = "/card/add",method = RequestMethod.POST)
    public EntityResponse<CardLibrary> create(@RequestBody CardLibrary cardLibrary){
        cardService.add(cardLibrary);
        return new EntityResponse<>(HttpStatus.OK,"create card successful",cardLibrary);
    }

    @RequestMapping(value = "/card/update/{id}",method = RequestMethod.DELETE)
    public EntityResponse<Object> update(@RequestBody CardLibrary cardLibrary,@PathVariable Long id){
        return new EntityResponse<>(HttpStatus.OK,"delete successful",cardService.update(cardLibrary,id));
    }

    @RequestMapping(value = "/card/delete/{id}",method = RequestMethod.DELETE)
    public EntityResponse<Object> delete(@PathVariable Long id){
        cardService.delete(id);
        return new EntityResponse<>(HttpStatus.OK,"delete successful",null);
    }

    @RequestMapping(value = "/bookback/{id}",method = RequestMethod.PUT)
    public void back(@PathVariable Long id){
        cardService.back(id);
    }
}