package com.disney.studios.common.converter;

import com.disney.studios.dto.DogBreedDto;
import com.disney.studios.entities.DogBreed;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Component
public class DogBreedEntityToDogBreedDtoConverter implements Converter<DogBreed, DogBreedDto> {

    @Override
    public Collection<DogBreedDto> convert(Collection<DogBreed> source) {
        if(CollectionUtils.isEmpty(source)) {
            return source == null ? null : Collections.emptyList();
        }
        return source.stream()
                .filter(Objects::nonNull)
                .map(this::convert)
                .toList();
    }

    @Override
    public DogBreedDto convert(DogBreed source) {
        return Objects.isNull(source) ? null : DogBreedDto.builder()
                .name(source.getName())
                .breedDetails(source.getBreedDetails())
                .build();
    }

}
