package com.ferreteria.controller;

import com.ferreteria.dto.ArticuloDTO;
import com.ferreteria.service.ArticuloService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articulos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ArticuloController {
    private final ArticuloService service;

    @GetMapping
    public List<ArticuloDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ArticuloDTO obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArticuloDTO guardar(@Valid @RequestBody ArticuloDTO dto) {
        return service.guardar(dto);
    }

    @PutMapping("/{id}")
    public ArticuloDTO actualizar(@PathVariable Long id,
                                  @Valid @RequestBody ArticuloDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

}
