package kioja.com.musv.entity.perfil.convidadoevento;

/**
 * Created by Henrique on 19/06/2015.
 */
public class ConvidadoEvento {
    private Long idConvidadoEvento;
    private String assinatura;
    private Integer idEvento;
    private Integer idPerfil;

    public Long getIdConvidadoEvento() {
        return idConvidadoEvento;
    }

    public void setIdConvidadoEvento(Long idConvidadoEvento) {
        this.idConvidadoEvento = idConvidadoEvento;
    }

    public String getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(String assinatura) {
        this.assinatura = assinatura;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }
}
