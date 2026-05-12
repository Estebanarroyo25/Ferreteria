package com.ferreteria.security.service;

import com.ferreteria.security.dto.AuthRequest;
import com.ferreteria.security.dto.AuthResponse;
import com.ferreteria.security.entity.Usuario;
import com.ferreteria.security.jwt.JwtService;
import com.ferreteria.security.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl  implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public String register(AuthRequest request) {

        Usuario usuario = Usuario.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol("USER")
                .build();

        usuarioRepository.save(usuario);
        return "Usuario registrado correctamente";
    }

    @Override
    public AuthResponse login(AuthRequest request) {

        // Autenticación con Spring Security
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // Generación del token JWT
        String token = jwtService.generarToken(request.getUsername());
        return new AuthResponse(token);
    }
}
