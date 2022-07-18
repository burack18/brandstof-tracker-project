package com.example.brandstoftracker.utilities.languageLocalization;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class TruckerMessageSource implements MessageSource {
    private final MessageCreater messageCreater;
    @Override
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return args!=null?messageCreater.getMessage(code,args):messageCreater.getMessage(code);
    }

    @Override
    public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
        return args!=null?messageCreater.getMessage(code,args):messageCreater.getMessage(code);
    }

    @Override
    public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        return null;
    }
}
