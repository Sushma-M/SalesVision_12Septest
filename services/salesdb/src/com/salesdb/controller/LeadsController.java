/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.salesdb.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
import com.salesdb.service.LeadsService;
import com.salesdb.service.QuotesService;
import java.io.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.TypeMismatchException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.util.WMMultipartUtils;
import com.wavemaker.runtime.util.WMRuntimeUtils;
import com.wavemaker.runtime.file.model.DownloadResponse;
import com.wordnik.swagger.annotations.*;
import com.salesdb.*;
import com.salesdb.service.*;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * Controller object for domain model class Leads.
 * @see com.salesdb.Leads
 */
@RestController(value = "Salesdb.LeadsController")
@RequestMapping("/salesdb/Leads")
@Api(value = "LeadsController", description = "Exposes APIs to work with Leads resource.")
public class LeadsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LeadsController.class);

    @Autowired
    @Qualifier("salesdb.LeadsService")
    private LeadsService leadsService;

    @Autowired
    @Qualifier("salesdb.QuotesService")
    private QuotesService quotesService;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ApiOperation(value = "Returns the list of Leads instances matching the search criteria.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Leads> findLeadss(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Leadss list");
        return leadsService.findAll(queryFilters, pageable);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Returns the list of Leads instances.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Leads> getLeadss(Pageable pageable) {
        LOGGER.debug("Rendering Leadss list");
        return leadsService.findAll(pageable);
    }

    @RequestMapping(value = "/{id}/quoteses", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the quoteses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Quotes> findAssociatedquoteses(Pageable pageable, @PathVariable("id") Integer id) {
        LOGGER.debug("Fetching all associated quoteses");
        return quotesService.findAssociatedValues(id, "leads", "id", pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 * 
	 * @param service
	 */
    protected void setLeadsService(LeadsService service) {
        this.leadsService = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Creates a new Leads instance.")
    public Leads createLeads(@RequestBody Leads instance) {
        LOGGER.debug("Create Leads with information: {}", instance);
        instance = leadsService.create(instance);
        LOGGER.debug("Created Leads with information: {}", instance);
        return instance;
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Returns the total count of Leads instances.")
    public Long countAllLeadss() {
        LOGGER.debug("counting Leadss");
        Long count = leadsService.countAll();
        return count;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Returns the Leads instance associated with the given id.")
    public Leads getLeads(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting Leads with id: {}", id);
        Leads instance = leadsService.findById(id);
        LOGGER.debug("Leads details with id: {}", instance);
        return instance;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Updates the Leads instance associated with the given id.")
    public Leads editLeads(@PathVariable("id") Integer id, @RequestBody Leads instance) throws EntityNotFoundException {
        LOGGER.debug("Editing Leads with id: {}", instance.getId());
        instance.setId(id);
        instance = leadsService.update(instance);
        LOGGER.debug("Leads details with id: {}", instance);
        return instance;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Deletes the Leads instance associated with the given id.")
    public boolean deleteLeads(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting Leads with id: {}", id);
        Leads deleted = leadsService.delete(id);
        return deleted != null;
    }
}
