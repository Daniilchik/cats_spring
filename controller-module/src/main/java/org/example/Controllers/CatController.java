package org.example.Controllers;


import lombok.RequiredArgsConstructor;
import org.example.DTOs.CatDTO;
import org.example.Entities.Cat;
import org.example.Services.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class CatController {

    private final CatService catService;

    @GetMapping(value = "/cats/{catId}", produces = APPLICATION_JSON_VALUE)
    public Cat findByCatId(@PathVariable("catId") Integer catId) {
        return catService.findByCatId(catId);
    }

    @GetMapping(value = "/cats", produces = APPLICATION_JSON_VALUE)
    public List<Cat> findAllCats() {
        return catService.findAllCats();
    }

    @GetMapping(value = "/cats/count")
    public Long countCats() {
        return catService.countAllCats();
    }

    @PostMapping(value = "/add/cat", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Cat addCat(@RequestBody CatDTO catDTO) {
        return catService.addCat(catDTO);
    }

    @PatchMapping(value = "/update/cat/{catId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Cat updateCat(@PathVariable("catId") Integer catId, @RequestBody CatDTO catDTO) {
        return catService.updateCatById(catId, catDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/delete/cat/{catId}", produces = APPLICATION_JSON_VALUE)
    public void deleteCat(@PathVariable("catId") Integer catId) {
        catService.deleteCatById(catId);
    }
}
