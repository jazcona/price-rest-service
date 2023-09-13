package com.jazcona.pricerestservice.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jazcona.pricerestservice.domain.Price;
import com.jazcona.pricerestservice.infra.inputport.PriceInputPort;
import com.jazcona.pricerestservice.infra.outputport.PriceRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class PriceUseCase implements PriceInputPort {
    
    @Autowired
    PriceRepository priceRepository;

    @Override
    public Optional<Price> findPriceByParams(Long brandId, Long productId, LocalDateTime applicationDate) {
        return priceRepository.findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                brandId, productId, applicationDate, applicationDate);
    }
}
