package com.disney.studios;

import ch.qos.logback.core.net.server.Client;
import com.disney.studios.entities.ClientEntity;
import com.disney.studios.entities.DogBreed;
import com.disney.studios.entities.DogDetails;
import com.disney.studios.repository.ClientEntityRepository;
import com.disney.studios.repository.DogBreedRepository;
import com.disney.studios.repository.DogDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

/**
 * Loads stored objects from the file system and builds up
 * the appropriate objects to add to the data source.
 *
 * Created by fredjean on 9/21/15.
 */
@Component
@Slf4j
public class PetLoader implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(PetLoader.class);

    // Resources to the different files we need to load.
    @Value("classpath:data/labrador.txt")
    private Resource labradors;

    @Value("classpath:data/pug.txt")
    private Resource pugs;

    @Value("classpath:data/retriever.txt")
    private Resource retrievers;

    @Value("classpath:data/yorkie.txt")
    private Resource yorkies;

    /*@Autowired
    DataSource dataSource;*/

    @Autowired
    DogBreedRepository dogBreedRepository;

    @Autowired
    DogDetailsRepository dogDetailsRepository;

    @Autowired
    ClientEntityRepository clientEntityRepository;

    /**
     * Load the different breeds into the data source after
     * the application is ready.
     *
     * @throws Exception In case something goes wrong while we load the breeds.
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        loadBreed("Labrador", labradors);
        loadBreed("Pug", pugs);
        loadBreed("Retriever", retrievers);
        loadBreed("Yorkie", yorkies);
        loadClients();
    }

    private void loadClients() {
        ClientEntity clientEntity = null;
        for(int i=1; i<=4; i++){
            String uuid = UUID.randomUUID().toString();
            logger.info("Client"+i+" uuid: "+uuid);
            clientEntity = new ClientEntity();
            clientEntity.setName("Client"+i);
            clientEntity.setUuid(uuid);
            clientEntityRepository.save(clientEntity);
        }

    }

    /**
     * Reads the list of dogs in a category and (eventually) add
     * them to the data source.
     * @param breed The breed that we are loading.
     * @param source The file holding the breeds.
     * @throws IOException In case things go horribly, horribly wrong.
     */
    private void loadBreed(String breed, Resource source) throws IOException {
        DogBreed dbreed = new DogBreed();
        dbreed.setName(breed);
        dbreed.setBreedDetails(breed+" dog breed details");
        dogBreedRepository.save(dbreed);
        DogDetails dogDetails = null;
        try ( BufferedReader br = new BufferedReader(new InputStreamReader(source.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                dogDetails = new DogDetails();
                dogDetails.setBreed(breed);
                dogDetails.setPictureUrl(line);
                dogDetails.setAge((int) Math.round(Math.random()*10));
                dogDetails.setUpVotes((int) Math.round(Math.random()*100));
                dogDetailsRepository.save(dogDetails);
            }
        }
    }
}
