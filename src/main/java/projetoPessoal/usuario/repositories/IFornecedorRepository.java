package projetoPessoal.usuario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projetoPessoal.usuario.entities.Fornecedor;

public interface IFornecedorRepository extends JpaRepository<Fornecedor, Integer> {


}
