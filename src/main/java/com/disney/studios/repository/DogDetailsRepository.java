package com.disney.studios.repository;

import com.disney.studios.entities.DogDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogDetailsRepository extends JpaRepository<DogDetails, String> {

    DogDetails findByUuid(String uuid);

    List<DogDetails> findByBreed(String breed);

}
