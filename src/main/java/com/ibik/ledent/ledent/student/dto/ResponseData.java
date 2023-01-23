package com.ibik.ledent.ledent.student.dto;

import java.util.ArrayList;
import java.util.List;

public class ResponseData<T> {
    private boolean result;
    private List<String> message = new ArrayList<>();
    private T data;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public T getdata() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
