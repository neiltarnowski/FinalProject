package com.promineotech.video.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.video.entity.Video;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@RequestMapping("/listvideo")
@OpenAPIDefinition(info = @Info(title = "Video Sales Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})

public interface ListVideoController {

  @Operation(
      summary = "Returns Video",
      description = "Returns a list of  videos of a supplied status",
      responses = {
          @ApiResponse(responseCode = "200",
              description = "A List of Videos are returned",
              content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = Video.class))),
          @ApiResponse(responseCode = "400",
            description = "The request parameters are invalid",
            content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "404",
            description = "The video does not exist",
            content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500",
            description = "An unplanned error occurred",
            content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(name = "status",
            allowEmptyValue = false,
            required = true,
            description = "The Video status (i.e., 'post production')"),
      }
  )
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  List<Video> fetchVideoByStatus(@RequestParam String status);
}