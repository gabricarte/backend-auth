package projetoPessoal.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FornecedorDto {
    private String nomeFornecedor;
    private String cnpj;
    private String senha;
}
