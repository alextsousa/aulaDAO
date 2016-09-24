package controller;

import dao.PessoaDao;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Pessoa;

@ManagedBean
@ViewScoped
public class PessoaController {

    private List<Pessoa> listaPessoas;
    private Pessoa pessoa;

    private String nomePesquisa;
    private Integer codPesquisa;
    private List<Pessoa> listaPessoasPesquisa;
    private Pessoa pessoaSelecionada;

    @ManagedProperty("#{pessoaDao}")
    private PessoaDao pessoaDao;

    @PostConstruct
    public void init() {
        pessoa = new Pessoa();
        listaPessoas = pessoaDao.listaTodos();
    }

    public void cadastrar() {
        pessoaDao.insere(pessoa);
        listaPessoas = pessoaDao.listaTodos();

    }

    public void pesquisaPessoa() {

        if ((codPesquisa == null) && (nomePesquisa.isEmpty())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "É necessário informar código ou nome", null));
        } else if (codPesquisa != null) {
            listaPessoasPesquisa = pessoaDao.listaPorCodigo(codPesquisa);
        } else {
            listaPessoasPesquisa = pessoaDao.listaPorNome(nomePesquisa);
        }

    }

    public void atualizaPessoa() {
        pessoaDao.atualiza(pessoaSelecionada);
        listaPessoas = pessoaDao.listaTodos();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Atualização registrada com sucesso!", null));
    }
    
   public void removePessoa() {
        pessoaDao.remove(pessoaSelecionada);
        listaPessoasPesquisa.remove(pessoaSelecionada);
        listaPessoas = pessoaDao.listaTodos();
        FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Registro removido com sucesso!", null));
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

    public Pessoa getPessoaSelecionada() {
        return pessoaSelecionada;
    }

    public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
        this.pessoaSelecionada = pessoaSelecionada;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getNomePesquisa() {
        return nomePesquisa;
    }

    public void setNomePesquisa(String nomePesquisa) {
        this.nomePesquisa = nomePesquisa;
    }

    public Integer getCodPesquisa() {
        return codPesquisa;
    }

    public void setCodPesquisa(Integer codPesquisa) {
        this.codPesquisa = codPesquisa;
    }

    public List<Pessoa> getListaPessoasPesquisa() {
        return listaPessoasPesquisa;
    }

    public void setListaPessoasPesquisa(List<Pessoa> listaPessoasPesquisa) {
        this.listaPessoasPesquisa = listaPessoasPesquisa;
    }

    public List<Pessoa> getListaPessoas() {
        return listaPessoas;
    }

    public void setListaPessoas(List<Pessoa> listaPessoas) {
        this.listaPessoas = listaPessoas;
    }

}
