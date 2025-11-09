package com.utn.productos_api.service;

import com.utn.productos_api.dto.ActualizarStockDTO;
import com.utn.productos_api.dto.ProductoDTO;
import com.utn.productos_api.dto.ProductoResponseDTO;
import com.utn.productos_api.model.Categoria;
import com.utn.productos_api.model.Producto;
import com.utn.productos_api.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoResponseDTO crearProducto(ProductoDTO producto){
        if (producto.nombre() == null || producto.nombre().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        Producto productoCreado = Producto.builder().nombre(producto.nombre()).descripcion(producto.descripcion())
                .precio(producto.precio()).categoria(producto.categoria()).build();
        return ProductoResponseDTO.fromEntity(productoRepository.save(productoCreado));
    }

    public List<ProductoResponseDTO> obtenerTodos() {
        return productoRepository.findAll()
                .stream()
                .map(ProductoResponseDTO::fromEntity)
                .toList();
    }

    public ProductoResponseDTO obtenerPorId(Long id) {
        Producto productoPorId = productoRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("No existe un producto con el ID " + id));
        return ProductoResponseDTO.fromEntity(productoPorId);
    }

    public List<ProductoResponseDTO> obtenerPorCategoria(Categoria categoria){
        return productoRepository.findByCategoria(categoria)
                .stream()
                .map(ProductoResponseDTO::fromEntity)
                .toList();
    }

    public ProductoResponseDTO actualizarProducto(Long id, ProductoDTO productoActualizado){
        Producto productoParaActualizar = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe un producto con el ID " + id));
        productoParaActualizar.setNombre(productoActualizado.nombre());
        productoParaActualizar.setDescripcion(productoActualizado.descripcion());
        productoParaActualizar.setPrecio(productoActualizado.precio());
        productoParaActualizar.setStock(productoActualizado.stock());
        productoParaActualizar.setCategoria(productoActualizado.categoria());
        return ProductoResponseDTO.fromEntity(productoRepository.save(productoParaActualizar));
    }

    public ProductoResponseDTO actualizarStock(Long id, ActualizarStockDTO nuevoStock){
        Producto productoParaActualizar = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe un producto con el ID " + id));
        productoParaActualizar.setStock(nuevoStock.stock());
        return ProductoResponseDTO.fromEntity(productoRepository.save(productoParaActualizar));
    }

    public ProductoResponseDTO eliminarProducto(Long id) {
        Producto productoParaEliminar = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe un producto con el ID " + id));
        productoRepository.delete(productoParaEliminar);
        return ProductoResponseDTO.fromEntity(productoParaEliminar);
    }

}
