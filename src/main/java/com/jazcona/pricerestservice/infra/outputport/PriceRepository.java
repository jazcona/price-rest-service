package com.jazcona.pricerestservice.infra.outputport;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jazcona.pricerestservice.domain.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price, Long> {
    Optional<Price> findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
            Long brandId, Long productId, LocalDateTime date, LocalDateTime date2);
}
