package com.utn.productos_api.service;

import com.utn.productos_api.model.Categoria;
import com.utn.productos_api.model.Producto;
import com.utn.productos_api.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    public Producto crearProducto(Producto producto){
        if (producto.getNombre() == null || producto.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        Producto productoCreado = Producto.builder().nombre(producto.getNombre()).descripcion(producto.getDescripcion())
                .precio(producto.getPrecio()).categoria(producto.getCategoria()).build();
        return productoRepository.save(productoCreado);
    }

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerPorId(Long id) {
        Producto productoPorId = productoRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("No existe un producto con el ID " + id));
        return Optional.ofNullable(productoPorId);
    }

    public List<Producto> obtenerPorCategoria(Categoria categoria){
        return productoRepository.findByCategoria(categoria);
    }

    public Producto actualizarProducto(Long id, Producto productoActualizado){
        Producto productoParaActualizar = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe un producto con el ID " + id));
        productoParaActualizar.setNombre(productoActualizado.getNombre());
        productoParaActualizar.setDescripcion(productoActualizado.getDescripcion());
        productoParaActualizar.setPrecio(productoActualizado.getPrecio());
        productoParaActualizar.setStock(productoActualizado.getStock());
        productoParaActualizar.setCategoria(productoActualizado.getCategoria());
        return productoRepository.save(productoParaActualizar);
    }

    public Producto actualizarStock(Long id, Integer nuevoStock){
        Producto productoParaActualizar = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe un producto con el ID " + id));
        productoParaActualizar.setStock(nuevoStock);
        return productoRepository.save(productoParaActualizar);
    }

    public Producto eliminarProducto(Long id) {
        Producto productoParaEliminar = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe un producto con el ID " + id));
        productoRepository.delete(productoParaEliminar);
        return productoParaEliminar;
    }

}
