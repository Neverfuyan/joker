package com.wj.springcloud.entity;


import java.io.InputStream;

public class ZipParam {
    public String name;
    public InputStream inputstream;


    public ZipParam(String name, InputStream inputstream) {
        this.name = name;
        this.inputstream = inputstream;
    }

    public ZipParam() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InputStream getInputstream() {
        return inputstream;
    }

    public void setInputstream(InputStream inputstream) {
        this.inputstream = inputstream;
    }
}