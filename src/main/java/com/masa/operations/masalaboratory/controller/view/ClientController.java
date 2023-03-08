package com.masa.operations.masalaboratory.controller.view;

import com.masa.operations.masalaboratory.dto.ClientDTO;
import com.masa.operations.masalaboratory.dto.ProjectDTO;
import com.masa.operations.masalaboratory.model.Client;
import com.masa.operations.masalaboratory.service.ClientService;
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
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping(value = "agregar-cliente")
public class ClientController {
    @Autowired
    private ClientService clientService;
    Logger logger = LoggerFactory.getLogger(ClientController.class);

    @GetMapping
    public ModelAndView clientForm(ModelMap model) {
        // Create new client to be added
        ClientDTO clientDTO = new ClientDTO();
        model.addAttribute("client", clientDTO);
        return new ModelAndView("client-form", model);
    }

    @PostMapping
    public ModelAndView addClient(@ModelAttribute("client") @Valid ClientDTO clientDTO,
                                  BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            logger.error(result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", ")));
            return new ModelAndView("client-form");
        }
        logger.info("Client sent {}", clientDTO);
        Client newClient = new Client();
        newClient.setClientName(clientDTO.getName().trim().toUpperCase());
        newClient.setRfc(clientDTO.getRfc().trim().toUpperCase());
        newClient.setFiscalAddress(clientDTO.getFiscalAddress().trim().toUpperCase());
        clientService.addClient(newClient);
        redirectAttributes.addFlashAttribute("successful", true);
        return new ModelAndView("redirect:/agregar-cliente");
    }
}
