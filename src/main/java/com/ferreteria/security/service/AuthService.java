package com.ferreteria.security.service;

import com.ferreteria.security.dto.AuthRequest;
import com.ferreteria.security.dto.AuthResponse;

public interface AuthService {
    String register(AuthRequest request);

    AuthResponse login(AuthRequest request);
}
