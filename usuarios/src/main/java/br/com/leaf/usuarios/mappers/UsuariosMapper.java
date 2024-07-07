package br.com.leaf.usuarios.mappers;

import br.com.leaf.usuarios.entity.Usuarios;
import br.com.leaf.usuarios.enums.PerfisEnum;
import br.com.leaf.usuarios.vos.RegistroVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuariosMapper {

    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "senha", source = "senha")
    @Mapping(target = "perfil", expression = "java(montarPerfil(vo.getPerfil()))")
    Usuarios registroTOUsuario(RegistroVO vo);

    default String montarPerfil(PerfisEnum perfil) {
        return perfil.getDescricao();
    }

}
