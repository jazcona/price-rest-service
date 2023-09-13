package com.jazcona.pricerestservice.infra.inputport;

import java.time.LocalDateTime;
import java.util.Optional;

import com.jazcona.pricerestservice.domain.Price;

public interface PriceInputPort {
    Optional<Price> findPriceByParams(Long brandId, Long productId, LocalDateTime applicationDate);
}
