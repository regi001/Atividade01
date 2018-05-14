package atividade.celer.com.br.atividade.Pessoa;

/**
 * Created by regia on 11/05/2018.
 */

public class Pessoa {

    private Long id;
    private String nome;
    private String endereco;
    private String telefone;
    private String site;

    final static String ID = "_id";
    final static String NOME = "nome";
    final static String ENDERECO = "endereco";
    final static String TELEFONE = "telefone";
    final static String SITE = "site";

    final static String TABELA = "tbl_pessoa";
    final static String [] COLUNAS = new String []{ID,NOME,ENDERECO,TELEFONE,SITE};

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getEndereco() {return endereco;}

    public void setEndereco(String endereco) {this.endereco = endereco;}

    public String getTelefone() {return telefone;}

    public void setTelefone(String telefone) {this.telefone = telefone;}

    public String getSite() {return site;}

    public void setSite(String site) {this.site = site;}
}
