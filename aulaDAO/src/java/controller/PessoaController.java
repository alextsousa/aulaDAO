
package controller;

import dao.PessoaDao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import model.Pessoa;

@ManagedBean
@RequestScoped
public class PessoaController {
    
    private List<Pessoa> listaPessoas;
    private Pessoa pessoa;
    
    @ManagedProperty ("#{pessoaDao}")
    private PessoaDao pessoaDao;
    
    @PostConstruct
    public void init(){
        pessoa = new Pessoa();
        listaPessoas = pessoaDao.listaTodos();
    }
    
    public void cadastrar(){
        pessoaDao.insere(pessoa);
        listaPessoas = pessoaDao.listaTodos();
        
    }

    public PessoaDao getPessoaDao() {
        return pessoaDao;
    }

    public void setPessoaDao(PessoaDao pessoaDao) {
        this.pessoaDao = pessoaDao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    
    
    

    public List<Pessoa> getListaPessoas() {
        return listaPessoas;
    }

    public void setListaPessoas(List<Pessoa> listaPessoas) {
        this.listaPessoas = listaPessoas;
    }
    
    
    
}
