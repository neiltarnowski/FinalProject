package com.promineotech.video.controller;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.video.entity.Orders;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@RequestMapping("/addtoorder")
@OpenAPIDefinition(info = @Info(title = "Video Sales Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})
public interface AddToOrderController {


  @Operation(
      summary = "Add price to order",
      description = "Returns the order with added price details",
      responses = {
          @ApiResponse(responseCode = "201",
              description = "The updated order is returned",
              content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = Orders.class))),
          @ApiResponse(responseCode = "400",
            description = "The request parameters are invalid",
            content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "404",
            description = "no order was found with the added details",
            content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500",
            description = "An unplanned error occurred.",
            content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(name = "order_id",
          required = true,
          description = "ID of order (i.e., '9')"),
          @Parameter(name = "price",
          required = true,
          description = "added price to order (i.e., '50')"),

      }
  )
  @PutMapping
  @ResponseStatus(code = HttpStatus.OK)
  Orders addToOrder(
      @RequestParam int order_id,
      @RequestParam BigDecimal price);
}