package projetoPessoal.usuario.services;

import projetoPessoal.usuario.LoginResponse;
import projetoPessoal.usuario.dto.LoginDto;
import projetoPessoal.usuario.dto.UsuarioDto;

import java.util.List;

public interface IUsuarioService {
    UsuarioDto criarUsuario(UsuarioDto usuario);
    List<UsuarioDto> listarUsuarios();
    UsuarioDto obterUsuario(int id);
    UsuarioDto atualizarUsuario(int id, UsuarioDto usuario);
    void deletarUsuario(int id);
    LoginResponse loginUsuario(LoginDto loginDTO);

}
