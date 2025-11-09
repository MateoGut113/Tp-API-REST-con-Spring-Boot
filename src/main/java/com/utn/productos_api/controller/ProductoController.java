package com.utn.productos_api.controller;

import com.utn.productos_api.dto.ActualizarStockDTO;
import com.utn.productos_api.dto.ProductoDTO;
import com.utn.productos_api.dto.ProductoResponseDTO;
import com.utn.productos_api.model.Categoria;
import com.utn.productos_api.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@RequestMapping("/api/productos")
@RestController
public class ProductoController {
    private final ProductoService productoService;

    @GetMapping
    public List<ProductoResponseDTO> listado() {
        return productoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ProductoResponseDTO buscarPorId(@PathVariable Long id) {
        return productoService.obtenerPorId(id);
    }

    @GetMapping("/categoria/{categoria}")
    public List<ProductoResponseDTO> buscarPorCategoria(@PathVariable Categoria categoria) {
        return productoService.obtenerPorCategoria(categoria);
    }

    @PostMapping
    public ProductoResponseDTO crear(@Valid @RequestBody ProductoDTO productoDTO) {
        return productoService.crearProducto(productoDTO);
    }

    @PutMapping("/{id}")
    public ProductoResponseDTO actualizarCompleto(@PathVariable Long id, @Valid @RequestBody ProductoDTO productoDTO) {
        return productoService.actualizarProducto(id, productoDTO);
    }

    @PatchMapping("/{id}/stock")
    public ProductoResponseDTO actualizarPorStock(@PathVariable Long id, @Valid @RequestBody ActualizarStockDTO productoDTO) {
        return productoService.actualizarStock(id, productoDTO);
    }

    @DeleteMapping("/{id}")
    public ProductoResponseDTO eliminar(@PathVariable Long id) {
        return productoService.eliminarProducto(id);
    }

}
