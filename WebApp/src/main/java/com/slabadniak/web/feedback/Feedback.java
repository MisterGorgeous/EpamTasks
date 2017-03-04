package com.slabadniak.web.feedback;



/**
 * This class is used to give message to the
 * user concerning the result of his operations.
 * @author Slabadniak Sergei
 * @version 1.0
 */
public class Feedback {
    private boolean written;
    private String message;

    public Feedback() {
    }

    /**
     * To set text of feedback.
     * @param message is information about the
     *                result of operations.
     */
    public void setMessage(String message){
        this.message = message;
        written = true;
    }

    /**
     * To check if feedback has been written.
     * @return
     */
    public boolean isWritten(){
        return written;
    }

    /**
     * To take feedback text.
     * @return
     */
    public String getMessage(){
        return message;
    }
}
