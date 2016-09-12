/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.salesdb.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
import com.salesdb.service.TasksService;
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
 * Controller object for domain model class Tasks.
 * @see com.salesdb.Tasks
 */
@RestController(value = "Salesdb.TasksController")
@RequestMapping("/salesdb/Tasks")
@Api(value = "TasksController", description = "Exposes APIs to work with Tasks resource.")
public class TasksController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TasksController.class);

    @Autowired
    @Qualifier("salesdb.TasksService")
    private TasksService tasksService;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ApiOperation(value = "Returns the list of Tasks instances matching the search criteria.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Tasks> findTaskss(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Taskss list");
        return tasksService.findAll(queryFilters, pageable);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Returns the list of Tasks instances.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Tasks> getTaskss(Pageable pageable) {
        LOGGER.debug("Rendering Taskss list");
        return tasksService.findAll(pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 * 
	 * @param service
	 */
    protected void setTasksService(TasksService service) {
        this.tasksService = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Creates a new Tasks instance.")
    public Tasks createTasks(@RequestBody Tasks instance) {
        LOGGER.debug("Create Tasks with information: {}", instance);
        instance = tasksService.create(instance);
        LOGGER.debug("Created Tasks with information: {}", instance);
        return instance;
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Returns the total count of Tasks instances.")
    public Long countAllTaskss() {
        LOGGER.debug("counting Taskss");
        Long count = tasksService.countAll();
        return count;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Returns the Tasks instance associated with the given id.")
    public Tasks getTasks(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting Tasks with id: {}", id);
        Tasks instance = tasksService.findById(id);
        LOGGER.debug("Tasks details with id: {}", instance);
        return instance;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Updates the Tasks instance associated with the given id.")
    public Tasks editTasks(@PathVariable("id") Integer id, @RequestBody Tasks instance) throws EntityNotFoundException {
        LOGGER.debug("Editing Tasks with id: {}", instance.getId());
        instance.setId(id);
        instance = tasksService.update(instance);
        LOGGER.debug("Tasks details with id: {}", instance);
        return instance;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Deletes the Tasks instance associated with the given id.")
    public boolean deleteTasks(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting Tasks with id: {}", id);
        Tasks deleted = tasksService.delete(id);
        return deleted != null;
    }
}
