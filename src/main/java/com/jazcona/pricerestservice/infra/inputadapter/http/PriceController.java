package com.jazcona.pricerestservice.infra.inputadapter.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.jazcona.pricerestservice.application.exception.PriceNotFoundException;
import com.jazcona.pricerestservice.domain.Price;
import com.jazcona.pricerestservice.infra.inputport.PriceInputPort;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "price")
public class PriceController {
    
    @Autowired
    PriceInputPort priceInputPort;

    @Operation(summary = "Obtener tarifa producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarifa encontrada",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Price.class))}),
            @ApiResponse(responseCode = "400", description = "Los parametros proporcionados no son válidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Tarifa no encontrada", content = @Content)}) 
    @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public Price getPrice(
            @Parameter(description = "id de la cadena del grupo", example="1") @RequestParam Long brandId,
            @Parameter(description = "id de producto", example="35455") @RequestParam Long productId,
            @Parameter(description = "fecha de aplicación. Formato yyyy-MM-dd HH:mm:ss", example = "2020-06-14 10:00:00") @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime applicationDate) {
        return priceInputPort.findPriceByParams(brandId, productId, applicationDate).orElseThrow(PriceNotFoundException::new);
    }
}

