package kioja.com.musv.entity.perfil.contato;

import kioja.com.musv.dao.EntityBase;

/**
 * Created by Henrique on 19/06/2015.
 */
public class Contato extends EntityBase {

    private Long idContato;
    private String abreviacao;
    private Integer idPerfil;
    private String link;
    private String numContato;
    private String tipo;


    public Long getIdContato() {
        return idContato;
    }

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNumContato() {
        return numContato;
    }

    public void setNumContato(String numContato) {
        this.numContato = numContato;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setIdContato(Long idContato) {
        this.idContato = idContato;
    }

    public String getAbreviacao() {
        return abreviacao;
    }

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }
}
