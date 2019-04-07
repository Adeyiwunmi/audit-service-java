package com.jumia.jpay.audit.controllers;

import com.jumia.jpay.audit.dto.AuditActionTypeDto;
import com.jumia.jpay.audit.dto.audit_action_type.CreateAuditActionTypeDto;
import com.jumia.jpay.audit.service.contract.AuditActionTypeService;
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


@Api(value = "AuditActionTypes", description = "", tags = "AuditActionTypes")
@RestController
@RequestMapping("/api/v1/audit-action-types")
public class AuditActionTypeController {


    private AuditActionTypeService auditActionTypeService;

    @Autowired
    public void setAuditActionTypeService(AuditActionTypeService auditActionTypeService) {
        this.auditActionTypeService = auditActionTypeService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all", produces = "application/json")
    @ApiOperation(value = "Get All Audit Action Types", notes = "Used to get all audit actions types as a dropdown on the user interface", response = AuditActionTypeDto.class, responseContainer = "List", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "You are not authorized access the resource"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not Found")})
    public Mono<ResponseEntity> getAuditActionTypes() {
        return auditActionTypeService.getAllAuditActionTypes();
    }


    @RequestMapping(method = RequestMethod.POST, value = "/create", produces = "application/json")
    @ApiOperation(value = "Create Audit Action Types", response = AuditActionTypeDto.class, responseContainer = "List", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "You are not authorized access the resource"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not Found")})
    public Mono<ResponseEntity> createAuditActionTypes(@RequestBody @Valid CreateAuditActionTypeDto createAuditActionTypeDto) {
        return auditActionTypeService.createAuditActionType(createAuditActionTypeDto);
    }

}
