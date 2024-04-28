package com.example.demo.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Map;



public class ExceptionResponse {
    private String message;
    @JsonIgnore
    private String timeStamp;
    @JsonIgnore
    private String trace;
    @JsonIgnore
    private String path;

    public ExceptionResponse() {

    }

    public ExceptionResponse(Map<String, Object> errorAttributes) {
        this.setPath((String) errorAttributes.get("path"));
        this.setMessage((String) errorAttributes.get("message"));
        this.setTimeStamp(errorAttributes.get("timestamp").toString());
        this.setTrace((String) errorAttributes.get("trace"));
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}