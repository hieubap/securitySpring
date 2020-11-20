package library.controller;

import library.entity.CardLibrary;
import library.exceptionhandle.responceEntity.EntityResponse;
import library.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
public class CardController{
    @Autowired
    CardService cardService;

    @RequestMapping(value = "/card/showall",method = RequestMethod.GET)
    public EntityResponse<List<CardLibrary>> getList(){
        return new EntityResponse<>(HttpStatus.OK,"find all card",cardService.getAll());
    }
    @RequestMapping(value = "/card/{id}",method = RequestMethod.GET)
    public EntityResponse<CardLibrary> getID(@PathVariable Long id){
        return new EntityResponse<>(HttpStatus.OK,"get by id = " + id,cardService.getbyID(id));
    }

    @RequestMapping(value = "/card/create",method = RequestMethod.POST)
    public void create(@RequestBody CardLibrary cardLibrary){
        cardService.create(cardLibrary);
    }
    @RequestMapping(value = "/bookback/{id}",method = RequestMethod.PUT)
    public void update(@PathVariable Long id){
        cardService.update(id);
    }


    @RequestMapping(value = "/card/{id}",method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id){
        cardService.delete(id);
        return "delete successful";
    }
}