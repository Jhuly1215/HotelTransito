package com.example.hoteltransito.service;

import com.example.hoteltransito.model.RoomType;
import com.example.hoteltransito.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para operaciones CRUD de RoomType.
 */
@Service
public class RoomTypeService {

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    /**
     * Recupera todos los tipos de habitación.
     */
    public List<RoomType> findAll() {
        return roomTypeRepository.findAll();
    }

    /**
     * Recupera un tipo de habitación por su ID
     */
    public Optional<RoomType> findById(Long id) {
        return roomTypeRepository.findById(id);
    }

    /**
     * Crea un nuevo tipo de habitación
     */
    @Transactional
    public RoomType create(RoomType roomType) {
        return roomTypeRepository.save(roomType);
    }

    /**
     * Actualiza un tipo de habitación existente.
     */
    @Transactional
    public Optional<RoomType> update(Long id, RoomType roomType) {
        return roomTypeRepository.findById(id)
                .map(existing -> {
                    existing.setName(roomType.getName());
                    existing.setDescription(roomType.getDescription());
                    existing.setCapacity(roomType.getCapacity());
                    existing.setPricePerNight(roomType.getPricePerNight());
                    return roomTypeRepository.save(existing);
                });
    }

    /**
     * Elimina un tipo de habitación por su ID.
     */
    @Transactional
    public boolean delete(Long id) {
        return roomTypeRepository.findById(id)
                .map(existing -> {
                    roomTypeRepository.delete(existing);
                    return true;
                }).orElse(false);
    }
}

