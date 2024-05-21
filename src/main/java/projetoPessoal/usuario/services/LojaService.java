package projetoPessoal.usuario.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projetoPessoal.usuario.LoginResponse;
import projetoPessoal.usuario.dto.LoginDto;
import projetoPessoal.usuario.dto.LojaDto;
import projetoPessoal.usuario.entities.Loja;
import projetoPessoal.usuario.entities.Role;
import projetoPessoal.usuario.mappers.LojaMapper;
import projetoPessoal.usuario.repositories.ILojaRepository;
import projetoPessoal.usuario.repositories.IRoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LojaService implements ILojaService {

    private final ILojaRepository repository;
    private final PasswordEncoder passwordEncoder;

    private final IRoleRepository roleRepository;


    public LojaService(ILojaRepository repository, IRoleRepository roleRepository ,PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<LojaDto> listarLojas() {
        return repository.findAll().stream()
                .map(LojaMapper::toDto)
                .collect(Collectors.toList());
    }

    public LojaDto obterLoja(int id) {
        return LojaMapper.toDto(repository.findById(id).orElse(null));
    }

    private Role criarRoleSeNaoExistir(String roleName) {
        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            role = new Role();
            role.setName(roleName);
            role = roleRepository.save(role);
        }
        return role;
    }

    public LojaDto criarLoja(LojaDto lojaDto) {
        Role roleLoja = criarRoleSeNaoExistir("ROLE_LOJA");
        Loja novaLoja = LojaMapper.toEntity(lojaDto);
        String senhaEncoded = passwordEncoder.encode(lojaDto.getSenha());
        novaLoja.setSenha(senhaEncoded);
        novaLoja.setRole(roleLoja);
        return LojaMapper.toDto(repository.save(novaLoja));
    }

    public LojaDto atualizarLoja(int id, LojaDto lojaDto) {
        Loja lojaExistente = repository.findById(id).orElse(null);
        if (lojaExistente != null) {
            lojaExistente.setNomeLoja(lojaDto.getNomeLoja());
            lojaExistente.setDataAtualizacaoDados(lojaDto.getDataAtualizacaoDados());
            lojaExistente.setCnpj(lojaDto.getCnpj());
            String senhaEncoded = passwordEncoder.encode(lojaDto.getSenha());
            lojaExistente.setSenha(senhaEncoded);
            return LojaMapper.toDto(repository.save(lojaExistente));
        }
        return null;
    }

    public void deletarLoja(int id) {
        repository.deleteById(id);
    }

    @Override
    public LoginResponse loginLoja(LoginDto loginDto) {
        String cnpj = loginDto.getCnpj();
        String senha = loginDto.getSenha();

        Loja loja = repository.findByCnpj(cnpj);

        if (loja != null) {
            String senhaEncoded = loja.getSenha();
            if (passwordEncoder.matches(senha, senhaEncoded)) {
                return new LoginResponse("Login bem-sucedido", true);
            } else {
                return new LoginResponse("Dados incorretos", false);
            }
        } else {
            return new LoginResponse("Loja não encontrada", false);
        }
    }
}
