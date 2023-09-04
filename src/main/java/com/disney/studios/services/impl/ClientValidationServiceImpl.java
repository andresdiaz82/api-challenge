package com.disney.studios.services.impl;

import com.disney.studios.entities.ClientEntity;
import com.disney.studios.repository.ClientEntityRepository;
import com.disney.studios.services.ClientValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientValidationServiceImpl implements ClientValidationService {

    private Logger logger = LoggerFactory.getLogger(ClientValidationServiceImpl.class);

    private final ClientEntityRepository clientEntityRepository;

    @Override
    public boolean validateClientId(String uuid) throws Exception {
        try{
            ClientEntity clientEntity = clientEntityRepository.findByUuid(uuid);
            return clientEntity != null ? true : false;
        }catch(Exception e) {
            logger.info("Failed to retrieve client");
            logger.debug(e.getMessage());
            logger.debug(e.getStackTrace().toString());
            throw e;
        }
    }

}
