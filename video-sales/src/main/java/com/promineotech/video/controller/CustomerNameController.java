package com.promineotech.video.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.promineotech.video.entity.Customer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

	@RequestMapping("/listcustomer")
	@OpenAPIDefinition(info = @Info(title = "Video Sales Service"), servers = {
	    @Server(url = "http://localhost:8080", description = "Local server.")})

	public interface CustomerNameController {


	  @Operation(
	      summary = "Lists Customer",
	      description = "Returns all customers of a supplied last name",
	      responses = {
	          @ApiResponse(responseCode = "201",
	              description = "A list of customers is returned",
	              content = @Content(mediaType = "application/json",
	              schema = @Schema(implementation = Customer.class))),
	          @ApiResponse(responseCode = "400",
	              description = "The request parameters are invalid",
	              content = @Content(mediaType = "application/json")),
	          @ApiResponse(responseCode = "404",
	              description = "The customer does not exist",
	              content = @Content(mediaType = "application/json")),
	          @ApiResponse(responseCode = "500",
	              description = "An unplanned error occurred.",
	              content = @Content(mediaType = "application/json"))
	      },
	      parameters = {
	              @Parameter(name = "last_name",
	                allowEmptyValue = false,
	                required = true,
	                description = "The customer's last name (i.e., 'Williams')"),
	          }
	      )
	      @GetMapping
	      @ResponseStatus(code = HttpStatus.OK)
	      List<Customer> fetchCustomerByLastName(@RequestParam String last_name);
	    }
