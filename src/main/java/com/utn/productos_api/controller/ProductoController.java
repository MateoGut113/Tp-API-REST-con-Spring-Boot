package com.utn.productos_api.controller;

import com.utn.productos_api.dto.*;
import com.utn.productos_api.model.Categoria;
import com.utn.productos_api.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@Tag(name = "Productos", description = "Sistema de e-commerce básico que\n" +
        "permite gestionar productos")
@RequestMapping("/api/productos")
@RestController
public class ProductoController {
    private final ProductoService productoService;

    @Operation(summary = "Obtener todos los productos", description = "Devuelve un listado de todos los productos almacenados en la base de datos")
    @ApiResponses(value =
            @ApiResponse(responseCode = "200", description = "Listado completado")
    )
    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> listado() {
        List<ProductoResponseDTO> productos = productoService.obtenerTodos();
        return ResponseEntity.ok(productos);
    }

    @Operation(summary = "Obtener un producto mediante el ID", description = "Devuelve un producto, si existe, con el ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404",
                    description = "Producto no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    ))
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }

    @Operation(summary = "Buscar los productos por categoria", description = "Devuelve un listado de todos los productos pertenecientes a una categoria en particular")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado completado"),
            @ApiResponse(responseCode = "500",
                    description = "Categoria invalida",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Error500Schema.class)
                    )
            )
    })
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ProductoResponseDTO>> buscarPorCategoria(@PathVariable Categoria categoria) {
        return ResponseEntity.ok(productoService.obtenerPorCategoria(categoria));
    }

    @Operation(summary = "Crear un producto", description = "Creacion de un producto, validando sus atributos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado"),
            @ApiResponse(responseCode = "400", description = "Validacion de atributo denegada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Error400Schema.class)
                    )),
            @ApiResponse(responseCode = "500", description = "Validacion interna denegada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Error500Schema.class)
                    ))
    })
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crear(@Valid @RequestBody ProductoDTO productoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.crearProducto(productoDTO));
    }

    @Operation(summary = "Actualizar un producto", description = "Actualiza un producto, si existe, validando sus atributos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )),
            @ApiResponse(responseCode = "400", description = "Validacion de atributo denegada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Error400Schema.class)
                    )),
            @ApiResponse(responseCode = "500", description = "Validacion interna denegada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Error500Schema.class)
                    ))
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizarCompleto(@PathVariable Long id, @Valid @RequestBody ProductoDTO productoDTO) {
        return ResponseEntity.ok(productoService.actualizarProducto(id, productoDTO));
    }

    @Operation(summary = "Actualizar el stock", description = "Actualiza el stock de un producto, si existe, validando el atributo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )),
            @ApiResponse(responseCode = "400", description = "Validacion de atributo denegada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Error400Schema.class)
                    )),
            @ApiResponse(responseCode = "500", description = "Validacion interna denegada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Error500Schema.class)
                    ))
    })
    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductoResponseDTO> actualizarPorStock(@PathVariable Long id, @Valid @RequestBody ActualizarStockDTO productoDTO) {
        return ResponseEntity.ok(productoService.actualizarStock(id, productoDTO));
    }

    @Operation(summary = "Eliminar un producto", description = "Elimina un producto, si existe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> eliminar(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

}
