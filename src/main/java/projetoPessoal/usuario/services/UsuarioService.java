package projetoPessoal.usuario.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import projetoPessoal.usuario.LoginResponse;
import projetoPessoal.usuario.dto.LoginDto;
import projetoPessoal.usuario.dto.UsuarioDto;
import projetoPessoal.usuario.entities.Usuario;
import projetoPessoal.usuario.mappers.UsuarioMapper;
import projetoPessoal.usuario.repositories.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService implements IUsuarioService {

    private final IUsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UsuarioDto criarUsuario(UsuarioDto usuario) {
        Usuario novoUsuario = UsuarioMapper.toEntity(usuario);
        String senhaEncoded = passwordEncoder.encode(usuario.getSenha());
        novoUsuario.setSenha(senhaEncoded);
        return UsuarioMapper.toDto(repository.save(novoUsuario));
    }

    @Override
    public List<UsuarioDto> listarUsuarios() {
        return repository.findAll().stream().map(UsuarioMapper::toDto).toList();
    }

    @Override
    public UsuarioDto obterUsuario(int id) {
        return UsuarioMapper.toDto(repository.findById(id).orElse(null));
    }

    @Override
    public UsuarioDto atualizarUsuario(int id, UsuarioDto usuario) {
        Usuario usuarioExistente = repository.findById(id).orElse(null);
        if (usuarioExistente != null) {
            usuarioExistente.setUsername(usuario.getUsername());
            String senhaEncoded = passwordEncoder.encode(usuario.getSenha());
            usuarioExistente.setSenha(senhaEncoded);
            return UsuarioMapper.toDto(repository.save(usuarioExistente));
        }
        return null;
    }

    @Override
    public void deletarUsuario(int id) {
        repository.deleteById(id);
    }

    @Override
    public LoginResponse loginUsuario(LoginDto loginDTO) {
        String username = loginDTO.getUsername();
        String senha = loginDTO.getSenha();

        Usuario usuario = repository.findByUsername(username);

        if (usuario != null) {
            String senhaEncoded = usuario.getSenha();
            if (passwordEncoder.matches(senha, senhaEncoded)) {
                return new LoginResponse("Login bem-sucedido", true);
            } else {
                return new LoginResponse("Senha incorreta", false);
            }
        } else {
            return new LoginResponse("Usuário não encontrado", false);
        }
    }
}
