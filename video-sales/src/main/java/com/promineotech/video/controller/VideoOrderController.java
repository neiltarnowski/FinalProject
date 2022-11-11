package com.promineotech.video.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.video.entity.Orders;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RequestMapping("/orders")
public interface VideoOrderController {


  @Operation(
      summary = "Create an order for a video",
      description = "Returns the order",
      responses = {
          @ApiResponse(responseCode = "201",
              description = "The created video order is returned",
              content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = Orders.class))),
          @ApiResponse(responseCode = "400",
            description = "The request parameters are invalid",
            content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "404",
            description = "no order was found with those details or the video may not exist",
            content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500",
            description = "An unplanned error occurred.",
            content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(name = "video_id",
            required = true,
            description = "ID of video (i.e., '1')"),
          @Parameter(name = "customer_id",
          required = true,
          description = "ID of customer (i.e., '2')"),

      }
  )
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  Orders createOrder(
      @RequestParam int video_id,
      @RequestParam int customer_id);
}
