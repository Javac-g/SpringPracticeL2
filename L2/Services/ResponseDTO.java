package com.company.L2.Services;

import com.company.L2.Controllers.Tool;

public class ResponseDTO {
    private String name;
    private Integer id;
    private Tool tool;
    private CarNum carNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    public CarNum getCarNum() {
        return carNum;
    }

    public void setCarNum(CarNum carNum) {
        this.carNum = carNum;
    }
}
