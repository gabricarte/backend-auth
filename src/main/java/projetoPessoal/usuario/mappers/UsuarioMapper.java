package projetoPessoal.usuario.mappers;

import projetoPessoal.usuario.dto.UsuarioDto;
import projetoPessoal.usuario.entities.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioDto dto){
        return Usuario.builder()
                .username(dto.getUsername())
                .senha(dto.getSenha())
                .build();
    }

    public static UsuarioDto toDto(Usuario entity){
        return UsuarioDto.builder()
                .username(entity.getUsername())
                .senha(entity.getSenha())
                .id(entity.getId())
                .build();
    }
}
