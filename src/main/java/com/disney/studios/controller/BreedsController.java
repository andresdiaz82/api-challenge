package com.disney.studios.controller;

import com.disney.studios.dto.DogBreedResponse;
import com.disney.studios.services.DogBreedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BreedsController {

    private final DogBreedService dogBreedService;

    @RequestMapping(value = "/disney/api/dogbreeds", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<DogBreedResponse> getDogBreeds(@RequestHeader("x-client-id") String clientId) {
        return ResponseEntity.ok(dogBreedService.findAll());
    }

    @RequestMapping(value = "/disney/api/dogbreeds/{name}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<DogBreedResponse> getDogBreedsByName(@RequestHeader("x-client-id") String clientId, @PathVariable String name) {
        return ResponseEntity.ok(dogBreedService.findAllByBreed(name));
    }

    @RequestMapping(value = "/disney/api/dogbreeds/like/{uuid}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<String> likeDogPicture(@RequestHeader("x-client-id") String clientId, @PathVariable String uuid) {
        try{
            return ResponseEntity.ok(String.valueOf(dogBreedService.upvotePicture(uuid, clientId)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
