package com.disney.studios.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DogBreedDto {

    private String name;
    private String breedDetails;

}
