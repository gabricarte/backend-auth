package projetoPessoal.usuario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projetoPessoal.usuario.entities.Admin;
import projetoPessoal.usuario.entities.Loja;

public interface IAdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByNome(String nome);
}
