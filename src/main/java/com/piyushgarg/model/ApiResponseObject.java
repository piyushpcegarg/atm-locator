package com.piyushgarg.model;

import java.util.List;

public class ApiResponseObject<E> {

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    private List<E> list;
}
