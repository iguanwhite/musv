package kioja.com.musv.entity.perfil.perfil;

/**
 * Created by Henrique on 18/06/2015.
 */
// Armor.java
//import com.parse.ParseObject;
//import com.parse.ParseClassName;

//@ParseClassName("Perfil")
public class Perfil { //extends ParseObject {

    private Long idPerfil;
    private boolean artista;
    private boolean ativo;
    private boolean autoral;
    private String biografia;
    private String celular;
    private String cidade;
    private String estado;
    private String nome;
    private String senha;
    private char   tipo;

    public Long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public boolean isArtista() {
        return artista;
    }

    public void setArtista(boolean artista) {
        this.artista = artista;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isAutoral() {
        return autoral;
    }

    public void setAutoral(boolean autoral) {
        this.autoral = autoral;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
}