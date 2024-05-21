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
public class LojaDto {
    private String nomeLoja;
    private LocalDate dataAtualizacaoDados;
    private String cnpj;
    private String senha;
}
