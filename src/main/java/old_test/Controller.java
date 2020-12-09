//package library.controller;
//
//import library.service.ServiceProcess;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
///*
//truyền đường dẫn
//!!!!!!!!!!!!!!!!!!!
//
// */
//
//@RestController
//@ResponseBody
//public class Controller{
//    @Autowired
//    ServiceProcess serviceProcess;
//
//    @RequestMapping(value = "/",method = RequestMethod.GET)
//    public String getList(){
//        return "!!! Hello !!! \n" +
//                "Server is running\n" +
//                "Book    : /1\n" +
//                "Card    : /2\n" +
//                "Session : /3\n" +
//                "Book    : /4\n";
//    }
//    @RequestMapping(value = "/{type}",method = RequestMethod.GET)
//    public List<Object> getList(@PathVariable Integer type){
//        return serviceProcess.getAll(type);
//    }
//    @RequestMapping(value = "/{type}/{id}",method = RequestMethod.GET)
//    public Object getID(@PathVariable Integer type,@PathVariable Long id){
//        return serviceProcess.getbyID(type,id);
//    }
//
//    @RequestMapping(value = "/{type}",method = RequestMethod.POST)
//    public String create(@RequestBody Object object, @PathVariable Integer type){
//        return serviceProcess.create(type,object);
//    }
//    @RequestMapping(value = "/bookback/{id}",method = RequestMethod.PUT)
//    public void update(@PathVariable Long id){
//        serviceProcess.update(ServiceProcess.SESSION,id);
//    }
//
//
//    @RequestMapping(value = "/{type}/{id}",method = RequestMethod.DELETE)
//    public String delete(@PathVariable Long id,@PathVariable Integer type){
//        serviceProcess.delete(type,id);
//        return "delete successful";
//    }
//}