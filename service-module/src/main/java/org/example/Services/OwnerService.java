package org.example.Services;


import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.DAOs.OwnerDAO;
import org.example.DTOs.OwnerDTO;
import org.example.Entities.Owner;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class OwnerService {
    private final OwnerDAO ownerDAO;

    @NotNull
    @Transactional(readOnly = true)
    public Owner findByOwnerId(@NotNull Integer ownerId) {
        return ownerDAO.findById(ownerId)
                .orElseThrow(() -> new EntityNotFoundException("Owner" + ownerId + " Not Found"));
    }

    @NotNull
    @Transactional(readOnly = true)
    public List<Owner> findAllOwners() {
        return ownerDAO.findAll();
    }

    @NotNull
    @Transactional(readOnly = true)
    public Long countAllOwners() {
        return ownerDAO.count();
    }

    @Transactional
    public void deleteOwnerById(@NotNull Integer ownerId) {
        ownerDAO.deleteById(ownerId);
    }

    @NotNull
    @Transactional
    public Owner updateOwnerById(@NotNull Integer ownerId, @NotNull OwnerDTO ownerDTO) {
        Owner owner = ownerDAO.findById(ownerId)
                .orElseThrow(() -> new EntityNotFoundException("Owner" + ownerId + " Not Found"));
        if(ownerDTO.getName() != null) owner.setName(ownerDTO.getName());
        if(ownerDTO.getBirthDate() != null) owner.setBirthDate(ownerDTO.getBirthDate());

        return ownerDAO.save(owner);
    }

    @NotNull
    @Transactional()
    public Owner addOwner(@NotNull OwnerDTO dto) {
        return ownerDAO.save(
                Owner.builder()
                        .name(dto.getName())
                        .birthDate(dto.getBirthDate())
                        .build()
        );
    }
}
