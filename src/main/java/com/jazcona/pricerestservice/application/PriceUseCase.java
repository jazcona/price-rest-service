package com.jazcona.pricerestservice.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jazcona.pricerestservice.domain.Price;
import com.jazcona.pricerestservice.infra.inputport.PriceInputPort;
import com.jazcona.pricerestservice.infra.outputport.PriceRepository;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@Slf4j
public class PriceUseCase implements PriceInputPort {
    
    
    PriceRepository priceRepository;
    
    @Autowired
    public void setPriceRepository(PriceRepository priceRepository){
        this.priceRepository = priceRepository;
    }

    @Override
    public Optional<Price> findPriceByParams(Long brandId, Long productId, LocalDateTime applicationDate) {
        log.info("findPriceByParams: "+brandId+"|"+productId+"|"+applicationDate);
        return priceRepository.findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                brandId, productId, applicationDate, applicationDate);
    }
}
