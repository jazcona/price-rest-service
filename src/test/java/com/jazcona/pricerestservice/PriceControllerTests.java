package com.jazcona.pricerestservice;

import com.jazcona.pricerestservice.domain.Price;
import com.jazcona.pricerestservice.infra.inputadapter.http.PriceController;
import com.jazcona.pricerestservice.infra.inputport.PriceInputPort;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.mockito.Mockito.when;

@WebMvcTest(PriceController.class)
@AutoConfigureMockMvc
public class PriceControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceInputPort priceService;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final Long brandId = 1L;
    private static final Long productId = 35455L;

    /*@Test
    public void testScenario1() throws Exception {
        String applicationDateStr = "2020-06-14 10:00:00";
        
        Optional<Price> expectedPrice = Optional.of(Price.builder()
            .brandId(brandId)
            .startDate(LocalDateTime.parse("2020-06-14 00:00:00",formatter))
            .endDate(LocalDateTime.parse("2020-12-31 23:59:59",formatter))
            .priceList(1L)
            .productId(productId)
            .priority(0L)
            .price(BigDecimal.valueOf(35.50))
            .curr("EUR")
            .build());

        when(priceService.findPriceByParams(brandId, productId, LocalDateTime.parse(applicationDateStr,formatter)))
            .thenReturn(expectedPrice);

        mockMvc.perform(MockMvcRequestBuilders.get("/price")
            .param("brandId", brandId.toString())
            .param("productId", productId.toString())
            .param("applicationDate", applicationDateStr)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(35.50));
    }*/

    @Test
    public void testScenario2() throws Exception {
        String applicationDateStr = "2020-06-14 16:00:00";
        
        Optional<Price> expectedPrice = Optional.of(Price.builder()
            .brandId(brandId)
            .startDate(LocalDateTime.parse("2020-06-14 15:00:00",formatter))
            .endDate(LocalDateTime.parse("2020-06-14 18:30:00",formatter))
            .priceList(2L)
            .productId(productId)
            .priority(1L)
            .price(BigDecimal.valueOf(25.45))
            .curr("EUR")
            .build());

        when(priceService.findPriceByParams(brandId, productId, LocalDateTime.parse(applicationDateStr,formatter)))
            .thenReturn(expectedPrice);

        mockMvc.perform(MockMvcRequestBuilders.get("/price")
            .param("brandId", brandId.toString())
            .param("productId", productId.toString())
            .param("applicationDate", applicationDateStr)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(25.45));
    }    

    /*@Test
    public void testScenario3() throws Exception {
        String applicationDateStr = "2020-06-14 21:00:00";

        Optional<Price> expectedPrice = Optional.of(Price.builder()
            .brandId(brandId)
            .startDate(LocalDateTime.parse("2020-06-14 00:00:00",formatter))
            .endDate(LocalDateTime.parse("2020-12-31 23:59:59",formatter))
            .priceList(1L)
            .productId(productId)
            .priority(0L)
            .price(BigDecimal.valueOf(35.50))
            .curr("EUR")
            .build());

        when(priceService.findPriceByParams(brandId, productId, LocalDateTime.parse(applicationDateStr,formatter)))
            .thenReturn(expectedPrice);

        mockMvc.perform(MockMvcRequestBuilders.get("/price")
            .param("brandId", brandId.toString())
            .param("productId", productId.toString())
            .param("applicationDate", applicationDateStr)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(35.50));
    }

    @Test
    public void testScenario4() throws Exception {
        String applicationDateStr = "2020-06-15 10:00:00";

        Price expectedPrice = Price.builder()
            .brandId(brandId)
            .startDate(LocalDateTime.parse("2020-06-15 00:00:00",formatter))
            .endDate(LocalDateTime.parse("2020-06-15 11:00:00",formatter))
            .priceList(3L)
            .productId(productId)
            .priority(1L)
            .price(BigDecimal.valueOf(30.50))
            .curr("EUR")
            .build();

        when(priceService.findPriceByParams(brandId, productId, LocalDateTime.parse(applicationDateStr,formatter)))
            .thenReturn(expectedPrice);

        mockMvc.perform(MockMvcRequestBuilders.get("/price")
            .param("brandId", brandId.toString())
            .param("productId", productId.toString())
            .param("applicationDate", applicationDateStr)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(30.50));
    }

    @Test
    public void testScenario5() throws Exception {
        String applicationDateStr = "2020-06-16 21:00:00";

        Price expectedPrice = Price.builder()
                .brandId(brandId)
                .startDate(LocalDateTime.parse("2020-06-15 16:00:00",formatter))
                .endDate(LocalDateTime.parse("2020-12-31 23:59:59",formatter))
                .priceList(4L)
                .productId(productId)
                .priority(1L)
                .price(BigDecimal.valueOf(38.95))
                .curr("EUR")
                .build();

            when(priceService.findPriceByParams(brandId, productId, LocalDateTime.parse(applicationDateStr,formatter)))
                .thenReturn(expectedPrice);

            mockMvc.perform(MockMvcRequestBuilders.get("/price")
                .param("brandId", brandId.toString())
                .param("productId", productId.toString())
                .param("applicationDate", applicationDateStr)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(38.95));
    }*/
}
