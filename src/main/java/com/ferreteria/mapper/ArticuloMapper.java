package com.ferreteria.mapper;

import com.ferreteria.dto.ArticuloDTO;
import com.ferreteria.entity.Articulo;

public class ArticuloMapper {
    private ArticuloMapper() {
        // Evita la instanciación
    }

    public static ArticuloDTO toDTO(Articulo articulo) {
        return ArticuloDTO.builder()
                .id(articulo.getId_articulo())
                .nombre(articulo.getNombre())
                .descripcion(articulo.getDescripcion())
                .precio(articulo.getPrecio())
                .stock(articulo.getStock())
                .build();
    }

    public static Articulo toEntity(ArticuloDTO dto) {
        return Articulo.builder()
                .id_articulo(dto.getId())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .precio(dto.getPrecio())
                .stock(dto.getStock())
                .build();
    }
}
