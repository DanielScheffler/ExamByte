package com.example.exambyte.unitTests;

import com.example.exambyte.data.WochenTest;
import com.example.exambyte.repositories.WochenTestRepository;
import com.example.exambyte.service.WochenTestService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import static org.mockito.Mockito.*;

public class WochenTestServiceTest {


    @Test
    @DisplayName("Über WochenTestService kann WochenTest hinzugefügt werden")
    void test_1(){
        //Arrange
        WochenTestRepository wochenTestRepository = mock(WochenTestRepository.class);
        WochenTestService wochenTestService = new WochenTestService(wochenTestRepository);
        WochenTest wochenTest = new WochenTestTestBuilder()
                .addName("Wochentest1")
                .addStartTime(LocalDateTime.MIN)
                .addEndTime(LocalDateTime.MAX)
                .build();
        //Act
        wochenTestService.addWochenTest(wochenTest);
        //Assert
        verify(wochenTestRepository).save(wochenTest);
    }

}
