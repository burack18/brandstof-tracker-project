package com.example.brandstoftracker.utilities.languageLocalization;

import com.example.brandstoftracker.exceptionHandler.exceptions.NotSupportedLanguageException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class MessageCreater {
    private final LocaleResolver resolver;
    private final HttpServletRequest request;
    private final MessageSource messageSource;

    public String getMessage(String code, @Nullable Object[] args){

        String lang="";
        Locale locale=resolver.resolveLocale(request);
        Enumeration<String> headers = request.getHeaders("acceptLanguage");
        try {
            lang=headers.nextElement();
        }catch (Exception exception){
            lang=locale.getLanguage();
        }
            locale=new Locale(lang);
        String response="";
        try {
            response=messageSource.getMessage(code, args,locale);
        }catch (NoSuchMessageException e){
            response=code;
        }
        return response;
    }
    public String getMessage(String code){
        String lang="";
        Locale locale=resolver.resolveLocale(request);
        Enumeration<String> headers = request.getHeaders("acceptLanguage");
        try {
            lang=headers.nextElement();
        }catch (Exception exception){
            lang=locale.getLanguage();
        }
            locale=new Locale(lang);
        String response="";
        try {
            response=messageSource.getMessage(code, null,locale);
        }catch (NoSuchMessageException e){
            response=code;
        }
        return response;
    }
}