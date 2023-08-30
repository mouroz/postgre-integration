package modelDb;

//Frankly, automating this to be abled to easily swap out for new databases
//with different col sizes seem too dificult, however chatgpt seems to abled
//to do that for you in a few tries if informed correctly
public class User {
	//for this example, the db contains simple user data with some set restrictions
	//public variables could also work - in case
	
    static public int colSize = 7;
    static public String selectionQuery = " (codigo, login, senha," +
    		" nome, profissao, idade, sexo) ";//spaces before and after are a must
    static public String[] attributeNames = {"codigo", "login", "senha", 
    		"nome", "profissao", "idade", "sexo"};

    private int codigo;
    private String login;
    private String senha;

    private String nome;
    private String profissao;
    private int idade;
    private char sexo;

    public User() {
    	//for using single sets on a for statement
    }
    public User(int codigo, String login, String senha, 
                     String nome, String profissao, int idade, char sexo) {
        this.codigo = codigo;
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.profissao = profissao;
        this.idade = idade;
        this.sexo = sexo;
    }

    public String toString() {
        return "Usuario [codigo=" + codigo + 
               ", login=" + login + 
               ", senha=" + senha + 
               ", sexo=" + sexo + 
               ", nome=" + nome +
               ", profissao=" + profissao +
               ", idade=" + idade +
               ", sexo=" + sexo +
               "]";
    }
    
    public String sqlQueryAll() {
        return "(" +
               getCodigo() + ", " +
               "'" + getLogin() + "', " +
               "'" + getSenha() + "', " +
               "'" + getNome() + "', " +
               "'" + getProfissao() + "', " +
               getIdade() + ", " +
               "'" + getSexo() + "'" +
               ")";
    }
    
    public void printAll() {
    	System.out.println(sqlQueryAll());
    }
    

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    } //setting the codigo variable could be dangerous as its a pk
   

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    
}
