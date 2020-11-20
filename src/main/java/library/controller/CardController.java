package library.controller;

import library.entity.CardLibrary;
import library.exception.ApiRequestException;
import library.exceptionhandle.responceEntity.EntityResponse;
import library.service.CardService;
import library.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
        if(!cardService.isExist(id)){
            throw new ApiRequestException("this id is not exist ! check id");
        }
        return new EntityResponse<>(HttpStatus.OK,"get by id = " + id,cardService.getbyID(id));
    }
    @RequestMapping(value = "/card/mssv={mssv}",method = RequestMethod.GET)
    public EntityResponse<CardLibrary> getByMssv(@PathVariable String mssv){
        if(!cardService.isExist(mssv)){
            throw new ApiRequestException("this student don't have card library ! check id");
        }
        return new EntityResponse<>(HttpStatus.OK,"get by id = " + mssv,cardService.getByMssv(mssv));
    }


    @RequestMapping(value = "/card/create",method = RequestMethod.POST)
    public EntityResponse<CardLibrary> create(@RequestBody CardLibrary cardLibrary){
        if(cardLibrary.getMssv() == null){
            throw new ApiRequestException( "mssv cant null.");
        }

        if(!studentService.isExist(cardLibrary.getMssv()))
            throw new ApiRequestException( "this student is not exist");

        if (cardService.isExist(cardLibrary.getMssv())){
            System.out.println("This student already has a library card ( mssv : " + cardLibrary.getMssv() + ")");
            throw new ApiRequestException( "This student already has a library card ( mssv : " + cardLibrary.getMssv() + ")");
        }

        cardLibrary.setStatus("con han");

        long year = TimeUnit.MILLISECONDS.convert(365,TimeUnit.DAYS);
        cardLibrary.setExpiration_date(new Timestamp(System.currentTimeMillis()+year));
        String idstudent = cardLibrary.getMssv();
        cardLibrary.setStudentId(studentService.getByMssv(idstudent).getId());

        cardService.update(cardLibrary);
        return new EntityResponse<>(HttpStatus.OK,"create card successful",cardLibrary);
    }

    @RequestMapping(value = "/card/update/{id}",method = RequestMethod.DELETE)
    public EntityResponse<Object> update(@RequestBody CardLibrary cardLibrary,@PathVariable Long id){
        if (!cardService.isExist(id)){
            throw new ApiRequestException("this id is not exist");
        }
        CardLibrary cardLibrary1 = cardService.getbyID(id);
        cardLibrary1.set(cardLibrary);
        cardService.update(cardLibrary1);
        return new EntityResponse<>(HttpStatus.OK,"delete successful",cardLibrary1);
    }

    @RequestMapping(value = "/card/delete/{id}",method = RequestMethod.DELETE)
    public EntityResponse<Object> delete(@PathVariable Long id){
        cardService.delete(id);
        return new EntityResponse<>(HttpStatus.OK,"delete successful",null);
    }


    @RequestMapping(value = "/bookback/{id}",method = RequestMethod.PUT)
    public void back(@PathVariable Long id){

//        cardService.update(id);
    }
}