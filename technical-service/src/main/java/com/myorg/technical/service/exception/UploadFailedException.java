package com.myorg.technical.service.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class UploadFailedException extends Exception {
    private String code;
    private String errorMessage;

    public UploadFailedException(String code,String errorMessage){
        this.code = code;
        this.errorMessage = errorMessage;
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }

    @Override
    public void printStackTrace(PrintStream s) {
        super.printStackTrace(s);
    }

    @Override
    public void printStackTrace(PrintWriter s) {
        super.printStackTrace(s);
    }

    public String getCode(){
        return this.code;
    }

    public String getErrorMessage(){
        return this.errorMessage;
    }

}
