package com.example.hoteltransito.controller;
import com.example.hoteltransito.model.RoomType;
import com.example.hoteltransito.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room-types")
public class RoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;

    /** List all room types, listar tipos de cuarto*/
    @GetMapping
    public List<RoomType> getAll() {
        return roomTypeService.findAll();
    }

    /** Get a single room type by ID, get id de un cuarto*/
    @GetMapping("/{id}")
    public ResponseEntity<RoomType> getById(@PathVariable Long id) {
        return roomTypeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** Create a new room type, crear nuevo cuarto */
    @PostMapping
    public RoomType create(@RequestBody RoomType roomType) {
        return roomTypeService.create(roomType);
    }

    /** Update an existing room type, actualizar un cuarto existente*/
    @PutMapping("/{id}")
    public ResponseEntity<RoomType> update(
            @PathVariable Long id,
            @RequestBody RoomType roomType
    ) {
        return roomTypeService.update(id, roomType)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** Delete a room type, borrar tipo de cuarto*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (roomTypeService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
