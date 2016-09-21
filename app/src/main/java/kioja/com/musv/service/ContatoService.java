package kioja.com.musv.service;

import java.util.List;


import kioja.com.musv.entity.perfil.contato.Contato;
import kioja.com.musv.exception.RegraNegocioException;


public interface ContatoService extends InterfaceGeneric {
    public boolean salvarOuAtualizar(Contato m) throws RegraNegocioException;

    public boolean deletar(Contato m) throws RegraNegocioException;

    public Contato buscarPorId(Integer id);

    public List<Contato> listar(String nomeColunaOrderBy, boolean ordenacaoAscendente);

    public List<Contato> listarPorTipo(Integer idTipo);

    //public List<Contato> buscarPorParametroConsulta(PontoParametroConsulta parametroConsulta);

    public long count();
}
