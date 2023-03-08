package com.masa.operations.masalaboratory.controller.view;

import com.masa.operations.masalaboratory.dto.ClientDTO;
import com.masa.operations.masalaboratory.dto.ProjectDTO;
import com.masa.operations.masalaboratory.model.Client;
import com.masa.operations.masalaboratory.model.Project;
import com.masa.operations.masalaboratory.service.ClientService;
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
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class ProjectController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private ProjectService projectService;
    Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @GetMapping(value = "agregar-obra")
    public ModelAndView projectForm(ModelMap model) {
        // Load all the clients for the selector
        Set<ClientDTO> clientsToSelect = StreamSupport.stream(clientService.getAllClients().spliterator(), false)
                .map(client -> new ClientDTO(client.getId(), client.getClientName(),
                        client.getRfc(), client.getFiscalAddress())).collect(Collectors.toSet());

        ProjectDTO projectDTO = new ProjectDTO();
        model.addAttribute("clients", clientsToSelect);
        model.addAttribute("project", projectDTO);
        return new ModelAndView("project-form", model);
    }

    @PostMapping(value = "agregar-obra")
    public ModelAndView addProject(@RequestParam Long clientId,
                                   @ModelAttribute("project") @Valid ProjectDTO projectDTO,
                                   BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            logger.error(result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", ")));
            return new ModelAndView("project-form");
        }
        // Convert the project dto to model
        Project project = new Project();
        project.setProjectName(projectDTO.getProjectName());
        project.setAddress(projectDTO.getAddress());
        project.setResident(projectDTO.getResident());

        // Verify client exists and save the project
        Optional<Client> clientOptional = clientService.getClientById(clientId);
        clientOptional.ifPresent(project::setClient);
        projectService.addProject(project);

        redirectAttributes.addFlashAttribute("successful", true);
        return new ModelAndView("redirect:/agregar-obra");
    }

    @GetMapping(value = "seleccionar-proyecto")
    public ModelAndView selectProject(ModelMap modelMap) {
        Set<ProjectDTO> projectsToSelect = StreamSupport.stream(projectService.getAllProjects().spliterator(), false)
                .map(project -> new ProjectDTO(project.getId(), project.getProjectName(), project.getAddress(), project.getResident()))
                .collect(Collectors.toSet());

        modelMap.addAttribute("projects", projectsToSelect);
        return new ModelAndView("project-selector");
    }
}
