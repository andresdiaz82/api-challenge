package com.disney.studios.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DogDetailsDto {

    private String uuid;
    private String pictureUrl;
    private Integer age;
    private Integer upVotes;

}
