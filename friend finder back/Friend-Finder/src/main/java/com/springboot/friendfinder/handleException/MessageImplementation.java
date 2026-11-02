package com.springboot.friendfinder.handleException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.EnumUtils;

import java.util.Locale;

@Service
public class MessageImplementation {
    private CustomException customException;

    @Autowired
    public MessageImplementation(CustomException customException) {

        this.customException = customException;
    }
    public String getMessageEn(String message) {
        return customException.messageSource().getMessage(message, null, new Locale("en"));
    }
    public String getMessageAr(String message) {
        return customException.messageSource().getMessage(message, null,new Locale("ar"));
    }

    public ExceptionResponse getMessage(String message) {
        return new ExceptionResponse(getMessageEn(message),getMessageAr(message));
    };
}
