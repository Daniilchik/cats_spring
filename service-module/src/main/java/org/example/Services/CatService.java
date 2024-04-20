package org.example.Services;


import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.DAOs.CatDAO;
import org.example.DTOs.CatDTO;
import org.example.Entities.Cat;
import org.example.Entities.Owner;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CatService {

    private final CatDAO catDAO;

    @NotNull
    @Transactional(readOnly = true)
    public Cat findByCatId(@NotNull Integer catId) {
        return catDAO.findById(catId)
                .orElseThrow(() -> new EntityNotFoundException("Cat" + catId + " Not Found"));
    }

    @NotNull
    @Transactional(readOnly = true)
    public List<Cat> findAllCats() {
        return catDAO.findAll();
    }

    @NotNull
    @Transactional(readOnly = true)
    public Long countAllCats() {
        return catDAO.count();
    }

    @Transactional
    public void deleteCatById(@NotNull Integer catId) {
        catDAO.deleteById(catId);
    }

    @NotNull
    @Transactional
    public Cat updateCatById(@NotNull Integer catId, @NotNull CatDTO catDTO) {
        List<Cat> friends = new ArrayList<>();
        for(CatDTO cat : catDTO.getFriends()) {
            friends.add(Cat.builder()
                    .owner(
                            Owner.builder()
                                    .name(cat.getOwner().getName())
                                    .birthDate(cat.getOwner().getBirthDate())
                                    .build()
                    )
                    .name(cat.getName())
                    .birthDate(cat.getBirthdate())
                    .breed(cat.getBreed())
                    .color(cat.getColor())
                    .build());
        }
        Cat cat = catDAO.findById(catId)
                .orElseThrow(() -> new EntityNotFoundException("Cat" + catId + " Not Found"));
        if (catDTO.getOwner() != null) cat.setOwner(
                Owner.builder()
                .name(catDTO.getOwner().getName())
                .birthDate(catDTO.getOwner().getBirthDate())
                .build()
        );
        if (catDTO.getName() != null) cat.setName(catDTO.getName());
        if (catDTO.getBirthdate() != null) cat.setBirthDate(catDTO.getBirthdate());
        if (catDTO.getBreed() != null) cat.setBreed(catDTO.getBreed());
        if (catDTO.getColor() != null) cat.setColor(catDTO.getColor());
        if (catDTO.getFriends() != null) cat.setFriends(friends);

        return catDAO.save(cat);
    }

    //todo
    @NotNull
    @Transactional()
    public Cat addCat(@NotNull CatDTO catDTO) {
        List<Cat> friends = new ArrayList<>();
        for(CatDTO cat : catDTO.getFriends()) {
            friends.add(Cat.builder()
                            .owner(
                            Owner.builder()
                                    .name(cat.getOwner().getName())
                                    .birthDate(cat.getOwner().getBirthDate())
                                    .build()
                    )
                            .name(cat.getName())
                            .birthDate(cat.getBirthdate())
                            .breed(cat.getBreed())
                            .color(cat.getColor())
                    .build());
        }
        return catDAO.save(
                Cat.builder()
                        .owner(
                                Owner.builder()
                                        .name(catDTO.getOwner().getName())
                                        .birthDate(catDTO.getOwner().getBirthDate())
                                        .build()
                        )
                        .name(catDTO.getName())
                        .birthDate(catDTO.getBirthdate())
                        .breed(catDTO.getBreed())
                        .color(catDTO.getColor())
                        .friends(friends)
                        .build()
        );
    }
}
