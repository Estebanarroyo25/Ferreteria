package com.ferreteria.service;

import com.ferreteria.dto.ArticuloDTO;
import com.ferreteria.entity.Articulo;
import com.ferreteria.exception.ResourceNotFoundException;
import com.ferreteria.mapper.ArticuloMapper;
import com.ferreteria.repository.ArticuloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticulosServiceImpl implements ArticuloService {
    private final ArticuloRepository repository;

    @Override
    public List<ArticuloDTO> listar() {
        return repository.findAll()
                .stream()
                .map(ArticuloMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ArticuloDTO obtenerPorId(Long id) {
        Articulo articulo = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Artículo no encontrado con ID: " + id));
        return ArticuloMapper.toDTO(articulo);
    }

    @Override
    public ArticuloDTO guardar(ArticuloDTO dto) {
        Articulo articulo = ArticuloMapper.toEntity(dto);
        return ArticuloMapper.toDTO(repository.save(articulo));
    }

    @Override
    public ArticuloDTO actualizar(Long id, ArticuloDTO dto) {
        Articulo articulo = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Artículo no encontrado con ID: " + id));

        articulo.setNombre(dto.getNombre());
        articulo.setDescripcion(dto.getDescripcion());
        articulo.setPrecio(dto.getPrecio());
        articulo.setStock(dto.getStock());

        return ArticuloMapper.toDTO(repository.save(articulo));
    }

    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Artículo no encontrado con ID: " + id);
        }
        repository.deleteById(id);
    }

}
