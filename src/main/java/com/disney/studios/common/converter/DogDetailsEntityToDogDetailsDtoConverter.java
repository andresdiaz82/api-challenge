package com.disney.studios.common.converter;

import com.disney.studios.dto.DogDetailsDto;
import com.disney.studios.entities.DogDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Component
public class DogDetailsEntityToDogDetailsDtoConverter implements Converter<DogDetails, DogDetailsDto> {

    @Override
    public Collection<DogDetailsDto> convert(Collection<DogDetails> source) {
        if(CollectionUtils.isEmpty(source)) {
            return source == null ? null : Collections.emptyList();
        }
        return source.stream()
                .filter(Objects::nonNull)
                .map(this::convert)
                .toList();
    }

    @Override
    public DogDetailsDto convert(DogDetails source) {
        return Objects.isNull(source) ? null : DogDetailsDto.builder()
                .uuid(source.getUuid())
                .age(source.getAge())
                .pictureUrl(source.getPictureUrl())
                .upVotes(source.getUpVotes())
                .build();
    }

}
