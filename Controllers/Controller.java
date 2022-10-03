package com.company.Controllers;

import com.company.Services.ResponseDTO;
import com.company.Services.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/Main")
public class Controller {

    @Autowired
    private Services services;


    @PostMapping(value = "/a",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseDTO createUser(@RequestBody RequestDTO user){
        return services.create(user);
    }

    @GetMapping(value = "/f",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseDTO findUser(@RequestParam  Integer id){
        return services.read(id);
    }
    @PutMapping(value = "u",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseDTO updateUser(@RequestParam Integer id,@RequestBody RequestDTO user){
        return services.update(id, user);
    }
    @RequestMapping(value = "/d",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deleteUser(@RequestParam Integer id){
        if (id > 3){
            return services.print("Was deleted person N:" + services.delete_A(id));
        } else if (id <= 2) {
            return services.print("Was deleted person N:") + services.delete_B(id);
        }
        return "Nobody was deleted";
    }
}
