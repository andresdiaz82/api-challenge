package com.disney.studios.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
public class DogGroupDto {

    private DogBreedDto breed;
    private List<DogDetailsDto> dogs;

}
