package com.disney.studios.services;

import com.disney.studios.dto.DogBreedResponse;


public interface DogBreedService {

    DogBreedResponse findAll();

    DogBreedResponse findAllByBreed(String breed);

    boolean upvotePicture(String picUuid, String clientUuId) throws Exception;

}
