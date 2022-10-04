package com.company.L1.Services;

import com.company.L1.Controllers.Car;
import com.company.L1.Controllers.RequestDTO;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class Services {
    private List<ResponseDTO> datalist = new ArrayList<>();

    public String print(String msg){
        return msg;
    }
    public void setEnum(RequestDTO json,ResponseDTO user){

        for(Car x : json.getCarlist()){

            switch (x.getValue()){

                case 1:
                    user.setCarEnum(CarEnum.FORD);
                    break;
                case 2:
                    user.setCarEnum(CarEnum.AUDI);
                    break;

                default:
                    user.setCarEnum(CarEnum.NONE);

            }
        }

    }
    public void log(String type,ResponseDTO user){

        byte[] data = ("\nType: "+ type + "\nName: " + user.getName()+"\nId: "+ user.getId()).getBytes();
        String add1 = "\nTool A:" + user.getTool().getFirst()+"\nTool B: "+user.getTool().getSecond();
        String add2 = "\nCar: " + user.getCarEnum();

        try(FileOutputStream fileOutputStream = new FileOutputStream("log.dat",true);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream)){

            byteArrayOutputStream.write(data);
            byteArrayOutputStream.writeTo(fileOutputStream);
            dataOutputStream.writeUTF(add1);
            dataOutputStream.writeUTF(add2);



        }catch (IOException e){
            e.printStackTrace();
        }


    }
    public ResponseDTO create(RequestDTO json){
        ResponseDTO user = new ResponseDTO();
        user.setId(json.getId());
        user.setName(json.getName());
        user.setTool(json.getTool());
        setEnum(json,user);
        log("Created",user);
        datalist.add(user);
        return user;
    }
    public ResponseDTO read(Integer id){
        for (ResponseDTO x :datalist){
            if (x.getId().equals(id)){
                return x;
            }
        }
        return null;
    }
    public ResponseDTO update(Integer id,RequestDTO json){
        ResponseDTO user = read(id);
        if (user != null){
            user.setId(json.getId());
            user.setName(json.getName());
            user.setTool(json.getTool());
            setEnum(json,user);
            log("Updated",user);
            return user;
        }
        return null;
    }
    public Integer delete_A(Integer id){
        int index =-1;
        for (int i = 0;i<datalist.size();i++){
            if (datalist.get(i).getId().equals(id)){
                index = i;
            }
        }
        if (index != -1){
            datalist.remove(index);
            return index;
        }
        return null;
    }

    public Integer delete_B(Integer id){
        int index = -1;
        for (ResponseDTO x : datalist){
            if (x.getId().equals(id)){
                index = datalist.indexOf(x);
                datalist.remove(x);
                return index;
            }
        }
        return null;
    }
}
