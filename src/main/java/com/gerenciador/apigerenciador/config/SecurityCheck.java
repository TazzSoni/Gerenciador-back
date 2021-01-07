/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
   // more logic ... 
   return false;
 }
}