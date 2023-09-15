package com.jazcona.pricerestservice;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.jazcona.pricerestservice.domain.Price;
import com.jazcona.pricerestservice.infra.inputadapter.http.PriceAPI;
import com.jazcona.pricerestservice.infra.inputport.PriceInputPort;

@WebMvcTest(PriceAPI.class)
@AutoConfigureMockMvc
public class PriceAPITests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceInputPort priceInputPort;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final Long brandId = 1L;
    private static final Long productId = 35455L;

    @Test
    public void givenBrandIdProductIdApplicationDate_whenHTTPGETPrice_thenStatusOKAndExpectedPrice() throws Exception {
        String applicationDateStr = "2020-06-16 21:00:00";

        Optional<Price> expectedPrice = Optional.of(Price.builder()
                .brandId(brandId)
                .startDate(LocalDateTime.parse("2020-06-15 16:00:00",formatter))
                .endDate(LocalDateTime.parse("2020-12-31 23:59:59",formatter))
                .priceList(4L)
                .productId(productId)
                .priority(1L)
                .price(BigDecimal.valueOf(38.95))
                .curr("EUR")
                .build());

            when(priceInputPort.findPriceByParams(brandId, productId, LocalDateTime.parse(applicationDateStr,formatter)))
                .thenReturn(expectedPrice);

            mockMvc.perform(MockMvcRequestBuilders.get("/price")
                .param("brandId", brandId.toString())
                .param("productId", productId.toString())
                .param("applicationDate", applicationDateStr)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(38.95));
    }

    @Test
    public void givenBrandIdProductIdApplicationDate_whenNoResults_thenError4xx() throws Exception {
        String applicationDateStr = "2020-06-16 21:00:00";

        Optional<Price> expectedPrice = Optional.empty();
        when(priceInputPort.findPriceByParams(2L, productId, LocalDateTime.parse(applicationDateStr,formatter)))
            .thenReturn(expectedPrice);

        mockMvc.perform(MockMvcRequestBuilders.get("/price")
            .param("brandId", brandId.toString())
            .param("productId", productId.toString())
            .param("applicationDate", applicationDateStr)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void givenBrandIdProductIdApplicationDate_whenBadParam_thenError4xx() throws Exception {
        String applicationDateStr = "9999-99-99 99:99:99";

        mockMvc.perform(MockMvcRequestBuilders.get("/price")
            .param("brandId", brandId.toString())
            .param("productId", productId.toString())
            .param("applicationDate", applicationDateStr)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}
