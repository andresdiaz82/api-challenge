package com.disney.studios.services.impl;

import com.disney.studios.common.converter.DogBreedEntityToDogBreedDtoConverter;
import com.disney.studios.common.converter.DogDetailsEntityToDogDetailsDtoConverter;
import com.disney.studios.dto.DogBreedResponse;
import com.disney.studios.dto.DogGroupDto;
import com.disney.studios.entities.ClientLikes;
import com.disney.studios.entities.DogDetails;
import com.disney.studios.repository.ClientLikesRepository;
import com.disney.studios.repository.DogBreedRepository;
import com.disney.studios.repository.DogDetailsRepository;
import com.disney.studios.services.DogBreedService;
import com.disney.studios.utils.comparators.DogDetailsComparer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DogBreedServiceImpl implements DogBreedService {


    private final DogBreedRepository dogBreedRepository;

    private final DogDetailsRepository dogDetailsRepository;

    private final ClientLikesRepository clientLikesRepository;

    private final DogBreedEntityToDogBreedDtoConverter dogBreedEntityToDogBreedDtoConverter;

    private final DogDetailsEntityToDogDetailsDtoConverter dogDetailsEntityToDogDetailsDtoConverter;

    private final DogDetailsComparer dogDetailsComparer;

    private Logger logger = LoggerFactory.getLogger(DogBreedServiceImpl.class);

    @Override
    public DogBreedResponse findAll() {
        try {
            List<DogGroupDto> dogGroups = new ArrayList<>();
            dogBreedRepository.findAll().parallelStream()
                    .forEach(b -> {
                        dogGroups.add(
                        DogGroupDto.builder()
                                .breed(dogBreedEntityToDogBreedDtoConverter.convert(b))
                                .dogs(
                                        dogDetailsEntityToDogDetailsDtoConverter.convert(
                                                dogDetailsRepository.findByBreed(b.getName()))
                                                .parallelStream().toList()
                                                .stream().sorted(dogDetailsComparer).toList()
                                )
                                .build()
                        );
                    });

            return DogBreedResponse.builder()
                    .dogs(dogGroups)
                    .build();
        }catch (Exception e) {
            logger.debug(e.getMessage());
            logger.debug(e.getStackTrace().toString());
            return DogBreedResponse.builder().dogs(null).build();
        }
    }

    @Override
    public DogBreedResponse findAllByBreed(String breed) {
        try {
            return DogBreedResponse.builder()
                    .dogs(Collections.singletonList(DogGroupDto.builder()
                    .breed(dogBreedEntityToDogBreedDtoConverter
                            .convert(dogBreedRepository
                                    .findByName(breed)))
                    .dogs(dogDetailsEntityToDogDetailsDtoConverter.convert(
                                    dogDetailsRepository.findByBreed(breed))
                            .parallelStream().toList()
                            .stream().sorted(dogDetailsComparer).toList())
                    .build()))
                    .build();
        }catch (Exception e) {
            logger.debug(e.getMessage());
            logger.debug(e.getStackTrace().toString());
            return DogBreedResponse.builder().dogs(null).build();
        }
    }

    @Override
    public boolean upvotePicture(String picUuid, String clientUuid) throws Exception {
        try {

            ClientLikes clientLikes = clientLikesRepository.findByClientUuidAndDogUuid(clientUuid, picUuid);
            if(clientLikes != null) {
                throw new Exception("Client already voted for this picture!");
            }

            DogDetails dogDetails = dogDetailsRepository.findByUuid(picUuid);
            dogDetails.setUpVotes(dogDetails.getUpVotes()+1);
            dogDetailsRepository.save(dogDetails);

            clientLikes = new ClientLikes();
            clientLikes.setClientUuid(clientUuid);
            clientLikes.setDogUuid(picUuid);

            clientLikesRepository.save(clientLikes);

            return true;
        } catch(Exception e) {
            logger.debug(e.getMessage());
            logger.debug(e.getStackTrace().toString());
            throw e;
        }
    }
}
