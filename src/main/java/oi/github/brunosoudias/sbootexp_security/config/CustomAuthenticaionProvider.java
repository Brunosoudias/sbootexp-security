package oi.github.brunosoudias.sbootexp_security.config;

import lombok.RequiredArgsConstructor;
import oi.github.brunosoudias.sbootexp_security.domain.entity.Usuario;
import oi.github.brunosoudias.sbootexp_security.domain.secuiry.CustomAuthentication;
import oi.github.brunosoudias.sbootexp_security.domain.secuiry.IdentificacaoUsuario;
import oi.github.brunosoudias.sbootexp_security.domain.service.UsuarioService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticaionProvider implements AuthenticationProvider {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String login = authentication.getName();
        Object senha = (String) authentication.getCredentials();

        Usuario usuario = usuarioService.obeterUsuarioComPremissoes(login);
        if (usuario != null) {
            boolean senhasBatem = passwordEncoder.matches((CharSequence) senha, usuario.getSenha());
            if(senhasBatem){
                IdentificacaoUsuario identificacaoUsuario = new IdentificacaoUsuario(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getLogin(),
                        usuario.getPermissoes()
                );
                return new CustomAuthentication(identificacaoUsuario);
            }
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
