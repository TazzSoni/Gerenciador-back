package com.gerenciador.apigerenciador.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nome;

    private String login;

    private String senha;

    private double carteira;

    private double despesas;

    private double receita;

    private String senhaDeco;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Receita> receitas;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Conta> contas;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Banco> bancos;

    public Pessoa() {
        this.contas = new ArrayList<Conta>();
        this.bancos = new ArrayList<Banco>();
    }

    public void deleteConta(Conta conta) {
        List<Conta> toRemove = new ArrayList<Conta>();
        for (Conta c : contas) {
            if (c.getId() == conta.getId()) {
                toRemove.add(c);
                this.despesas -= conta.getValor();
            }
        }
        contas.removeAll(toRemove);
    }
    
    public void deleteBanco(Banco banco) {
        List<Banco> toRemove = new ArrayList<Banco>();
        for (Banco b : bancos) {
            if (b.getId() == banco.getId()) {
                toRemove.add(b);
                this.carteira -= banco.getValor();
            }
        }
        bancos.removeAll(toRemove);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public String getSenhaDeco() {
        return senhaDeco;
    }

    public void setSenhaDeco(String senhaDeco) {
        this.senhaDeco = senhaDeco;
    }

    public void setSenha(String senha) {
        senhaDeco = senha;
        senha = new BCryptPasswordEncoder().encode(senha);
        System.out.println(senha);
        this.senha = senha;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(ArrayList<Conta> contas) {
        this.contas = contas;
    }

    public void addContas(Conta ct) {
        this.contas.add(ct);
    }

    public List<Banco> getBancos() {
        return bancos;
    }

    public void setBancos(ArrayList<Banco> bancos) {
        this.bancos = bancos;
    }

    public void addBancos(Banco bc) {
        this.bancos.add(bc);
    }

    public void setReceitas(ArrayList<Receita> receitas) {
        this.receitas = receitas;
    }

    public void addReceitas(Receita rc) {
        this.receitas.add(rc);
    }
        
    public void deleteReceita(Receita receita) {
        List<Receita> toRemove = new ArrayList<Receita>();
        for (Receita r : receitas) {
            if (r.getId() == receita.getId()) {
                toRemove.add(r);
                this.receita -= receita.getValor();
            }
        }
        receitas.removeAll(toRemove);
    }

    public double getReceita() {
        return receita;
    }

    public List<Receita> getReceitas() {
        return receitas;
    }

    public double getCarteira() {
        return carteira;
    }

    public void setReceita() {
        double rec = 0;
        for (Receita r : receitas) {
            rec += r.getValor();
        }
        this.receita = rec;
    }

    public void setCarteira() {
        double cart = 0;
        for (Banco b : bancos) {
            cart += b.getValor();
        }
        this.carteira = cart;
    }

    public double getDespesas() {
        return despesas;
    }

    public void atualizaConta(Conta conta) {

        for (Conta c : contas) {
            if (conta.getId() == c.getId()) {
                c.setDescricao(conta.getDescricao());
                c.setValor(conta.getValor());
                c.setData(conta.getData());
            }
        }
        setDespesas();
    }
    
    public void atualizaBanco(Banco banco) {
        for (Banco b : bancos) {
            if (banco.getId() == b.getId()) {
                b.setNome(banco.getNome());
                b.setValor(banco.getValor());
            }
        }
        setDespesas();
    }
    
    public void atualizaReceita(Receita receita) {
        for (Receita r : receitas) {
            if (receita.getId() == r.getId()) {
                r.setOrigem(receita.getOrigem());
                r.setValor(receita.getValor());
            }
        }
        setReceita();
    }

    public void setDespesas() {
        double disp = 0;
        for (Conta c : contas) {
            disp += c.getValor();
        }
        this.despesas = disp;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority("USER"));
        return auth;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "id=" + id + ", nome=" + nome + ", login=" + login + ", senha=" + senha + ", contas=" + contas + ", bancos=" + bancos + ", carteira=" + carteira + '}';
    }



}
