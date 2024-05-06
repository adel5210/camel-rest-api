package com.adel.camelrestapi;

import org.springframework.stereotype.Component;

/**
 * @author Adel.Albediwy
 */
@Component
public class SomeService {

    public void onSend(final Integer count){
        System.out.println(count);
    }

}
