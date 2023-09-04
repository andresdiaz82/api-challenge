package com.disney.studios.utils.comparators;

import com.disney.studios.dto.DogDetailsDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class DogDetailsComparer implements Comparator<DogDetailsDto> {

    @Override
    public int compare(DogDetailsDto dog1, DogDetailsDto dog2) {
        return dog1.getUpVotes() < dog2.getUpVotes() ? 1 :
                dog1.getUpVotes() > dog2.getUpVotes() ? -1 :
                0;
    }

}
