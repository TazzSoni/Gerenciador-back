/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gerenciador.apigerenciador.config;

import com.gerenciador.apigerenciador.models.Pessoa;
import com.gerenciador.apigerenciador.repository.PessoaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tasso
 */
@Repository
public class ImplementsUserDetailsService implements UserDetailsService {
    
    @Autowired
    private PessoaRepository pr;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Pessoa pessoa = pr.findByLogin(login);
        
        if(pessoa == null){
            throw  new UsernameNotFoundException("Usuário não encontrado!");
        }
        
        //Lista que seta as ROLES dos usuários
     //  List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
       List<GrantedAuthority> authorityListuser = AuthorityUtils.createAuthorityList("ROLE_USER");
        
        return new User(login, pessoa.getPassword(), authorityListuser);
    }
    
}
