package com.jumia.jpay.audit.controllers;

import com.jumia.jpay.audit.dto.enumerated_entity.EnumeratedEntityCreateDto;
import com.jumia.jpay.audit.dto.enumerated_entity.EnumeratedEntityDto;
import com.jumia.jpay.audit.service.contract.AuditActionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @author adeyi.adebolu
 * created on 07/04/2019
 */

@Api(value = "AuditActions", description = "", tags = "AuditActions")
@RestController
@RequestMapping("/api/v1/audit-actions")
public class AuditActionsController {

    private AuditActionService auditActionService;

    @Autowired
    public void setAuditActionService(AuditActionService auditActionService) {
        this.auditActionService = auditActionService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all", produces = "application/json")
    @ApiOperation(value = "Get All Audit Actions", notes = "Used to get all audit actions as a dropdown on the user interface", response = EnumeratedEntityDto.class, responseContainer = "List", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "You are not authorized access the resource"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not Found")})
    public Mono<ResponseEntity> getAuditActions() {
        return auditActionService.getAllAuditActions();
    }


    @RequestMapping(method = RequestMethod.POST, value = "/create", produces = "application/json")
    @ApiOperation(value = "Create Audit Action", response = EnumeratedEntityDto.class, responseContainer = "List", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "You are not authorized access the resource"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not Found")})
    public Mono<ResponseEntity> createAuditActions(@RequestBody @Valid EnumeratedEntityCreateDto enumeratedEntityCreateDto) {
        return auditActionService.createAuditAction(enumeratedEntityCreateDto);
    }

}
