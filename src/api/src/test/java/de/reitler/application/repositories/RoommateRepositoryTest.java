package de.reitler.application.repositories;


import de.reitler.domain.entities.Roommate;
import de.reitler.domain.repositories.RoommateRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(SpringRunner.class)
public class RoommateRepositoryTest {

    @Mock
    RoommateRepository repository;

    Roommate roommate= new Roommate("abcde", "roommate 1", "roommate@roommate.com", "roommate.png" );

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void loadRoommateTest(){
        Mockito.when(repository.findAll()).thenReturn(
                Collections.emptyList()
        );

        Assertions.assertArrayEquals(new ArrayList<Roommate>().toArray(), repository.findAll().toArray());
    }

    @Test
    public void saveRoommateTest() {
        Mockito.when(repository.save(roommate)).thenReturn(roommate);

        Assertions.assertEquals(roommate, repository.save(roommate));
    }
}
