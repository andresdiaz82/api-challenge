package com.disney.studios.repository;

import com.disney.studios.entities.ClientLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientLikesRepository extends JpaRepository<ClientLikes, Integer> {

    public ClientLikes findByClientUuidAndDogUuid(String clientUuid, String dogUuid);

}
