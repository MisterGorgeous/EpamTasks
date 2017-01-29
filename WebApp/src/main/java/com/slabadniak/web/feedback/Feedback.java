package com.slabadniak.web.feedback;


public class Feedback {
    private boolean written;
    private String message;

    public Feedback() {
    }

    public void setMessage(String message){
        this.message = message;
        written = true;
    }

    public boolean isWritten(){
        return written;
    }

    public String getMessage(){
        return message;
    }
}
