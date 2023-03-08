package com.masa.operations.masalaboratory.controller.view;

import com.masa.operations.masalaboratory.dto.ConcreteWorkOrderDTO;
import com.masa.operations.masalaboratory.dto.ProjectDTO;
import com.masa.operations.masalaboratory.model.ConcreteWorkOrder;
import com.masa.operations.masalaboratory.model.Project;
import com.masa.operations.masalaboratory.service.ConcreteWorkOrderService;
import com.masa.operations.masalaboratory.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class WorkOrderController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private ConcreteWorkOrderService concreteWorkOrderService;
    Logger logger = LoggerFactory.getLogger(WorkOrderController.class);

    @GetMapping(value = "obra/{projectId}/agregar-orden")
    public ModelAndView loadWorkOrderForm(@PathVariable("projectId") Long projectId, ModelMap modelMap) {
        Optional<Project> optionalProject = projectService.getProjectById(projectId);
        ConcreteWorkOrderDTO concreteWorkOrder = new ConcreteWorkOrderDTO();
        modelMap.addAttribute("concreteWorkOrder", concreteWorkOrder);
        optionalProject.ifPresent(project -> modelMap.addAttribute("project", project));
        return new ModelAndView("work-order-form");
    }

    @PostMapping(value = "obra/{projectId}/agregar-orden")
    public ModelAndView addWorkOrder(@PathVariable("projectId") Long projectId,
                                     @ModelAttribute("concreteWorkOrder") @Valid ConcreteWorkOrderDTO concreteWorkOrderDTO,
                                     BindingResult result, RedirectAttributes redirectAttributes) {
        // Get the project and add the work order
        Optional<Project> optionalProject = projectService.getProjectById(projectId);

        if (result.hasErrors()) {
            logger.error(result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", ")));
            ModelMap modelMap = new ModelMap();
            modelMap.addAttribute("project", optionalProject.orElse(new Project()));
            return new ModelAndView("work-order-form", modelMap);
        }

        logger.info("Project ID: {}", projectId);
        optionalProject.ifPresent(project -> logger.info("Project: {}", project));
        logger.info("Concrete Work Order: {}", concreteWorkOrderDTO);

        // Add the work order
        ConcreteWorkOrder concreteWorkOrder = new ConcreteWorkOrder();
        concreteWorkOrder.setSampleDate(LocalDate.parse(concreteWorkOrderDTO.getSampleDate(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        concreteWorkOrder.setServiceHour(LocalTime.parse(concreteWorkOrderDTO.getServiceHour()));
        concreteWorkOrder.setResistanceFc(concreteWorkOrderDTO.getResistanceFc());
        concreteWorkOrder.setConcreteSlump(concreteWorkOrderDTO.getConcreteSlump());
        concreteWorkOrder.setConcreteVolume(concreteWorkOrderDTO.getConcreteVolume());
        concreteWorkOrder.setTma(concreteWorkOrderDTO.getTma());
        concreteWorkOrder.setDesignAge(concreteWorkOrderDTO.getDesignAge());
        concreteWorkOrder.setConcreteProvider(concreteWorkOrderDTO.getConcreteProvider());
        concreteWorkOrder.setProject(optionalProject.orElse(new Project()));
        logger.info("Concrete Work Order: {}", concreteWorkOrder);
        ConcreteWorkOrder created = concreteWorkOrderService.addConcreteWorkOrder(concreteWorkOrder);

        ModelAndView view = new ModelAndView("redirect:/orden/" + created.getId() + "/agregar-muestra");
        redirectAttributes.addFlashAttribute("successful", true);
        return view;
    }

    @GetMapping(value = "seleccionar-orden")
    public ModelAndView selectOrder(ModelMap modelMap) {
        Set<ConcreteWorkOrder> concreteWorkOrders = StreamSupport.stream(concreteWorkOrderService.getAllConcreteWorkOrders().spliterator(), false).collect(Collectors.toSet());
        modelMap.addAttribute("workOrders", concreteWorkOrders);
        return new ModelAndView("work-order-selector");
    }
}