package com.ferreteria.service;

import com.ferreteria.dto.ArticuloDTO;

import java.util.List;

public interface ArticuloService {

    List<ArticuloDTO> listar();

    ArticuloDTO obtenerPorId(Long id);

    ArticuloDTO guardar(ArticuloDTO dto);

    ArticuloDTO actualizar(Long id, ArticuloDTO dto);

    void eliminar(Long id);
}
