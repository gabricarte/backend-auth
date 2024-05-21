package projetoPessoal.usuario.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projetoPessoal.usuario.LoginResponse;
import projetoPessoal.usuario.dto.AdminDto;
import projetoPessoal.usuario.entities.Admin;
import projetoPessoal.usuario.entities.Loja;
import projetoPessoal.usuario.entities.Role;
import projetoPessoal.usuario.repositories.IAdminRepository;
import projetoPessoal.usuario.repositories.ILojaRepository;

@Service
public class AdminService implements IAdminService{

    private final IAdminRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(IAdminRepository adminRepository, PasswordEncoder passwordEncoder){
        this.repository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }
    private Admin criarAdminSeNaoExistir() {
        Admin admin = repository.findByNome("admin");
        String senhaEncoded = passwordEncoder.encode("admin");
        if (admin == null) {
            admin = new Admin();
            admin.setNome("admin");
            admin.setSenha(senhaEncoded);
            admin.setId(1);
            admin = repository.save(admin);
        }
        return admin;
    }

    @Override
    public LoginResponse loginAdmin(AdminDto adminDto) {

        criarAdminSeNaoExistir();

        String nome = adminDto.getNome();
        String senha = adminDto.getSenha();

        Admin admin = repository.findByNome(nome);

        if (admin != null) {
            String senhaEncoded = admin.getSenha();
            if (passwordEncoder.matches(senha, senhaEncoded)) {
                return new LoginResponse("Login bem-sucedido", true);
            } else {
                return new LoginResponse("Dados incorretos", false);
            }
        } else {
            return new LoginResponse("Admin não encontrado", false);
        }
    }
}
