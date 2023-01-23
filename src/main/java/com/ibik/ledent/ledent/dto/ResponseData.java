package com.ibik.ledent.ledent.dto;

import java.util.ArrayList;
import java.util.List;

// import com.ibik.ledent.ledent.student.Student;

public class ResponseData<T> {
    private boolean result;
    private List<String> message = new ArrayList<>();
    private Iterable<T> data;

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

    public Iterable<T> getdata() {
        return data;
    }

    public void setData(Iterable<T> data) {
        this.data = data;
    }
}
