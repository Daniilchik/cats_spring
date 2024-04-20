package org.example.Controllers;


import lombok.RequiredArgsConstructor;
import org.example.DTOs.OwnerDTO;
import org.example.Entities.Owner;
import org.example.Services.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping(value = "/owners/{ownerId}", produces = APPLICATION_JSON_VALUE)
    public Owner findByOwnerId(@PathVariable("ownerId") Integer ownerId) {
        return ownerService.findByOwnerId(ownerId);
    }
    @GetMapping(value = "/owners", produces = APPLICATION_JSON_VALUE)
    public List<Owner> findAllOwners() {
        return ownerService.findAllOwners();
    }

    @GetMapping(value = "/owners/count")
    public Long countOwners() {
        return ownerService.countAllOwners();
    }

    @PostMapping(value = "/add/owner", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Owner addOwner(@RequestBody OwnerDTO ownerDTO) {
        return ownerService.addOwner(ownerDTO);
    }

    @PatchMapping(value = "/update/owner/{ownerId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Owner updateOwner(@PathVariable("ownerId") Integer ownerId, @RequestBody OwnerDTO ownerDTO) {
        return ownerService.updateOwnerById(ownerId, ownerDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/delete/owner/{ownerId}", produces = APPLICATION_JSON_VALUE)
    public void deleteOwner(@PathVariable("ownerId") Integer ownerId) {
        ownerService.deleteOwnerById(ownerId);
    }
}
