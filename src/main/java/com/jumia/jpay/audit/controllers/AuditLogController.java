package com.jumia.jpay.audit.controllers;

import com.jumia.jpay.audit.dto.AuditLogDto;
import com.jumia.jpay.audit.service.contract.AuditLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

/**
 * @author adeyi.adebolu
 * created on 06/04/2019
 */


@Api(value = "AuditLog", description = "", tags = "AuditLog")
@RestController
@RequestMapping("/api/v1/audit-logs")
public class AuditLogController {

    private AuditLogService auditLogService;

    @Autowired
    @Qualifier("AuditLogServiceImpl")
    public void setAuditLogService(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }


    /**
     * @param auditLogId
     * @return Audit Log full information
     */
    @ApiOperation(value = "Get AuditLog By Id", response = AuditLogDto.class, consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "You are not authorized access the resource"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = "application/json")
    public Mono<ResponseEntity> getByTransactionId(@PathVariable("id") String auditLogId) {
        return auditLogService.getByTransactionId(auditLogId);
    }

    /**
     * @return All AuditTrails full information
     */
    @RequestMapping(method = RequestMethod.GET, value = "/all", produces = "application/json")
    @ApiOperation(value = "Get Audit Logs", response = AuditLogDto.class, responseContainer = "List", consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "You are not authorized access the resource"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not Found")})
    public Mono<ResponseEntity> getAuditTrails(@RequestParam("keyword") String keyword,
                                               @RequestParam("fromDate") String fromDate,
                                               @RequestParam("endDate") String endDate,
                                               @RequestParam("auditActionId") String auditActionId,
                                               @RequestParam("page") @NotNull int page,
                                               @RequestParam("size") @NotNull int size,
                                               @RequestParam("sortProperty") String sortProperty,
                                               @RequestParam("sortType") String sortType,
                                               @RequestParam("auditActionTypeId") String auditActionTypeId,
                                               @RequestParam("userName") String userName,
                                               HttpServletResponse httpServletResponse) {
        return auditLogService.getAuditTrails(keyword, fromDate, endDate, auditActionId, page, size, sortProperty, sortType, auditActionTypeId, userName, httpServletResponse);
    }
}
