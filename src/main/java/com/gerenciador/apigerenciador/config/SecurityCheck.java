package com.gerenciador.apigerenciador.config;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class SecurityCheck {


public boolean check(String login, Authentication authentication) {
    if(login == null){
        return false;
    }
    
   if(login.equals(authentication.getName())){
       return true;
   }   
   return false;
 }
}