package projetoPessoal.usuario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projetoPessoal.usuario.entities.Loja;

public interface ILojaRepository extends JpaRepository<Loja, Integer> {
    Loja findByCnpj(String cnpj);
}
