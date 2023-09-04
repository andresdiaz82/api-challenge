package com.disney.studios.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class DogBreedResponse {

    private List<DogGroupDto> dogs;

}
