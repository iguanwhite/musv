package kioja.com.musv.dao;



import java.io.Serializable;
import java.util.Date;

/**
 * Classe abstrata responsável por conter os atributos comuns de uma entidade persistente.
 * <p/>
 * Created by Bruno Leonardo on 06/12/2014.
 */
public abstract class EntityBase implements Serializable {


    private Date dataCadastro;


    private Date dataAlteracao;


    private Integer usuarioCadastro;


    private Integer usuarioAlteracao;

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public Integer getUsuarioCadastro() {
        return usuarioCadastro;
    }

    public void setUsuarioCadastro(Integer usuarioCadastro) {
        this.usuarioCadastro = usuarioCadastro;
    }

    public Integer getUsuarioAlteracao() {
        return usuarioAlteracao;
    }

    public void setUsuarioAlteracao(Integer usuarioAlteracao) {
        this.usuarioAlteracao = usuarioAlteracao;
    }
}
