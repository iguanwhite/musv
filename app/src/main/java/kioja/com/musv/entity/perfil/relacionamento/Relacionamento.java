package kioja.com.musv.entity.perfil.relacionamento;

import java.util.Date;

/**
 * Created by Henrique on 19/06/2015.
 */
public class Relacionamento {

    private Long idRelacionamento;
    private boolean aceito;
    private Date dataAceite;
    private Date dataSolicitacao;
    private Integer idPerfilSeguido;
    private Integer idPerfilSeguidor;

    public Long getIdRelacionamento() {
        return idRelacionamento;
    }

    public void setIdRelacionamento(Long idRelacionamento) {
        this.idRelacionamento = idRelacionamento;
    }

    public boolean isAceito() {
        return aceito;
    }

    public void setAceito(boolean aceito) {
        this.aceito = aceito;
    }

    public Date getDataAceite() {
        return dataAceite;
    }

    public void setDataAceite(Date dataAceite) {
        this.dataAceite = dataAceite;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public Integer getIdPerfilSeguido() {
        return idPerfilSeguido;
    }

    public void setIdPerfilSeguido(Integer idPerfilSeguido) {
        this.idPerfilSeguido = idPerfilSeguido;
    }

    public Integer getIdPerfilSeguidor() {
        return idPerfilSeguidor;
    }

    public void setIdPerfilSeguidor(Integer idPerfilSeguidor) {
        this.idPerfilSeguidor = idPerfilSeguidor;
    }
}
