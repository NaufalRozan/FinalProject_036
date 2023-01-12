/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject.finalproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rozan
 * NIM 20200140036
 */
@RestController 
@CrossOrigin
public class myController {
    
    //deklarasi JPAController ke myController
    Finalproject data = new Finalproject();
    FinalprojectJpaController dctrl = new FinalprojectJpaController();
    
    //membuat Method GET
    @RequestMapping("/GET")
    @ResponseBody
    public List<Finalproject> getDatabyID(){
        List<Finalproject> datas = new ArrayList<>();
        try {datas = dctrl.findFinalprojectEntities();}
        catch(Exception error) {}        
        return datas;
    }
    
    
    //membuat Method POST
    @RequestMapping(value ="/POST", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    @ResponseBody
    public String sendData(HttpEntity<String> kiriman) throws Exception{
        String message="no action";
        String json_receive = kiriman.getBody();
        ObjectMapper mapper = new ObjectMapper();
        Finalproject data = new Finalproject(); 
        data = mapper.readValue(json_receive, Finalproject.class);
        dctrl.create(data);
        message ="Data " + data.getName()+" telah diSimpan";
        return message;
    }
    
    
    //membuat Method PUT
    @RequestMapping(value ="/PUT", method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public String editData(HttpEntity<String> kiriman) throws Exception{
        String message="no action";
        String json_receive = kiriman.getBody();
        ObjectMapper mapper = new ObjectMapper();
        Finalproject newdatas = new Finalproject(); 
        
        newdatas = mapper.readValue(json_receive, Finalproject.class);
        try {dctrl.edit(newdatas);} catch (Exception e){}
        message ="Data " + newdatas.getName()+" telah diUbah";
        return message;
    }
    
    
    //membuat Method DELETE
    @RequestMapping(value ="/DELETE", method = RequestMethod.DELETE, consumes = APPLICATION_JSON_VALUE)
    public String deleteData(HttpEntity<String> kiriman) throws Exception{
        String message="no action";
        String json_receive = kiriman.getBody();
        ObjectMapper mapper = new ObjectMapper();
        Finalproject newdatas = new Finalproject();     
        newdatas = mapper.readValue(json_receive, Finalproject.class);
        dctrl.destroy(newdatas.getId());
        return  "Data telah Dihapus";
    }
    
}
