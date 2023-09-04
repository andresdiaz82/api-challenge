package com.disney.studios.repository;

import com.disney.studios.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientEntityRepository extends JpaRepository<ClientEntity, Integer> {

    public ClientEntity findByUuid(String uuid);

}
