package com.dev.christopher.smartjug.result;

/**
 * Created by Christopher on 29/06/16.
 */
public class ErrorResult {

    String message;

    public ErrorResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorResult{" +
                "message='" + message + '\'' +
                '}';
    }
}
