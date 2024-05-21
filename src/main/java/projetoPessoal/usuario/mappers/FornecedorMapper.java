package projetoPessoal.usuario.mappers;

import projetoPessoal.usuario.dto.FornecedorDto;
import projetoPessoal.usuario.entities.Fornecedor;

public class FornecedorMapper {

    public static Fornecedor toEntity(FornecedorDto dto) {
        return Fornecedor.builder()
                .nomeFornecedor(dto.getNomeFornecedor())
                .cnpj(dto.getCnpj())
                .senha(dto.getSenha())
                .build();
    }

    public static FornecedorDto toDto(Fornecedor entity) {
        return FornecedorDto.builder()
                .nomeFornecedor(entity.getNomeFornecedor())
                .cnpj(entity.getCnpj())
                .senha(entity.getSenha())
                .build();
    }
}
