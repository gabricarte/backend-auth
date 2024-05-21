package projetoPessoal.usuario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projetoPessoal.usuario.entities.Role;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
