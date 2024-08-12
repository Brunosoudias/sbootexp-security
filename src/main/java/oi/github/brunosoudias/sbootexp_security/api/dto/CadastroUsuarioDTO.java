package oi.github.brunosoudias.sbootexp_security.api.dto;

import lombok.Data;
import oi.github.brunosoudias.sbootexp_security.domain.entity.Usuario;

import java.util.List;

@Data
public class CadastroUsuarioDTO {
    private Usuario usuario;
    private List<String> permissoes;

}
