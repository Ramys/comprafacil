package br.com.leaf.usuarios.mappers;

import br.com.leaf.usuarios.models.Usuarios;
import br.com.leaf.usuarios.vos.RegistroVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuariosMapper {

    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "senha", source = "senha")
    Usuarios registroTOUsuario(RegistroVO vo);

}
