package com.disney.studios.repository;

import com.disney.studios.entities.DogBreed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogBreedRepository extends JpaRepository<DogBreed, Integer> {

    DogBreed findByName(String name);

}
