package projetoPessoal.usuario.mappers;

import projetoPessoal.usuario.dto.LojaDto;
import projetoPessoal.usuario.entities.Loja;

public class LojaMapper {

    public static Loja toEntity(LojaDto dto) {
        return Loja.builder()
                .nomeLoja(dto.getNomeLoja())
                .dataAtualizacaoDados(dto.getDataAtualizacaoDados())
                .cnpj(dto.getCnpj())
                .senha(dto.getSenha())
                .build();
    }

    public static LojaDto toDto(Loja entity) {
        return LojaDto.builder()
                .nomeLoja(entity.getNomeLoja())
                .dataAtualizacaoDados(entity.getDataAtualizacaoDados())
                .cnpj(entity.getCnpj())
                .senha(entity.getSenha())
                .build();
    }
}
