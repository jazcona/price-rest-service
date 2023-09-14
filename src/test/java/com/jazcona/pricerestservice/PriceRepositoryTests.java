package com.jazcona.pricerestservice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.jazcona.pricerestservice.domain.Price;
import com.jazcona.pricerestservice.infra.outputport.PriceRepository;

@DataJpaTest
public class PriceRepositoryTests {
    @Autowired
    private PriceRepository priceRepository;
    
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final Long brandId = 1L;
    private static final Long productId = 35455L;
    @Test
    public void givenBrandIdProductIdApplicationDate_whenfindFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc_thenReturnExpectedPrice(){
        
        // given - setup or precondition
        String applicationDateStr = "2020-06-14 21:00:00";
        Long expectedId = 1L;
        // when - action or the testing
        Price priceDB = priceRepository.findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(brandId, productId, LocalDateTime.parse(applicationDateStr,formatter), LocalDateTime.parse(applicationDateStr,formatter)).get();

        // then - very output
        Assertions.assertNotNull(priceDB);
        Assertions.assertNotNull(priceDB.getId());
        Assertions.assertEquals(priceDB.getId(), expectedId);
    }
}
