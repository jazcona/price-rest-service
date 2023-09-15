package com.jazcona.pricerestservice;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    public void givenBrandIdProductIdApplicationDate_whenfindFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc_thenReturnNotNull(){
        
        // given
        String applicationDateStr = "2020-06-14 21:00:00";

        // when
        Price priceDB = priceRepository.findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(brandId, productId, LocalDateTime.parse(applicationDateStr,formatter), LocalDateTime.parse(applicationDateStr,formatter)).get();

        // then
        Assertions.assertNotNull(priceDB);
    }

    @Test
    public void givenBrandId1ProductIdApplicationDate20200614100000_whenfindFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc_thenReturnExpectedPrice(){
        
        // given
        String applicationDateStr = "2020-06-14 10:00:00";
        BigDecimal expectedPrice = new BigDecimal(35.5).setScale(2, RoundingMode.HALF_UP);
        // when
        Price priceDB = priceRepository.findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(brandId, productId, LocalDateTime.parse(applicationDateStr,formatter), LocalDateTime.parse(applicationDateStr,formatter)).get();

        // then
        Assertions.assertNotNull(priceDB);
        Assertions.assertNotNull(priceDB.getId());
        Assertions.assertEquals(priceDB.getPrice(), expectedPrice);
    }

    @Test
    public void givenBrandIdProductIdApplicationDate20200614160000_whenfindFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc_thenReturnExpectedPrice(){
        
        // given
        String applicationDateStr = "2020-06-14 16:00:00";
        BigDecimal expectedPrice = new BigDecimal(25.45).setScale(2, RoundingMode.HALF_UP);
        // when
        Price priceDB = priceRepository.findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(brandId, productId, LocalDateTime.parse(applicationDateStr,formatter), LocalDateTime.parse(applicationDateStr,formatter)).get();

        // then
        Assertions.assertNotNull(priceDB);
        Assertions.assertNotNull(priceDB.getId());
        Assertions.assertEquals(priceDB.getPrice(), expectedPrice);
    }

    @Test
    public void givenBrandIdProductIdApplicationDate20200614210000_whenfindFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc_thenReturnExpectedPrice(){
        
        // given
        String applicationDateStr = "2020-06-14 21:00:00";
        BigDecimal expectedPrice = new BigDecimal(35.5).setScale(2, RoundingMode.HALF_UP);
        // when
        Price priceDB = priceRepository.findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(brandId, productId, LocalDateTime.parse(applicationDateStr,formatter), LocalDateTime.parse(applicationDateStr,formatter)).get();

        // then
        Assertions.assertNotNull(priceDB);
        Assertions.assertNotNull(priceDB.getId());
        Assertions.assertEquals(priceDB.getPrice(), expectedPrice);
    }

    @Test
    public void givenBrandIdProductIdApplicationDate20200615100000_whenfindFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc_thenReturnExpectedPrice(){
        
        // given
        String applicationDateStr = "2020-06-15 10:00:00";
        BigDecimal expectedPrice = new BigDecimal(30.50).setScale(2, RoundingMode.HALF_UP);
        // when
        Price priceDB = priceRepository.findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(brandId, productId, LocalDateTime.parse(applicationDateStr,formatter), LocalDateTime.parse(applicationDateStr,formatter)).get();

        // then
        Assertions.assertNotNull(priceDB);
        Assertions.assertNotNull(priceDB.getId());
        Assertions.assertEquals(priceDB.getPrice(), expectedPrice);
    }
}
