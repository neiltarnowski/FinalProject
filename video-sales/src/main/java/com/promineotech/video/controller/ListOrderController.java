package com.promineotech.video.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.video.entity.Orders;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@RequestMapping("/listorder")
@OpenAPIDefinition(info = @Info(title = "Video Sales Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})

public interface ListOrderController {

  @Operation(
      summary = "Returns order",
      description = "Returns all orders in the database",
      responses = {
          @ApiResponse(responseCode = "200",
              description = "A list of all orders is returned",
              content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = Orders.class))),
          @ApiResponse(responseCode = "500",
            description = "An unplanned error occurred",
            content = @Content(mediaType = "application/json"))
      }
      
  )
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  List<Orders> fetchOrders();
}