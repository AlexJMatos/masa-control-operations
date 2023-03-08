package com.masa.operations.masalaboratory.controller.view;

import com.masa.operations.masalaboratory.dto.ConcreteSampleDTO;
import com.masa.operations.masalaboratory.dto.ConcreteSpecimenDTO;
import com.masa.operations.masalaboratory.model.ConcreteSample;
import com.masa.operations.masalaboratory.model.ConcreteWorkOrder;
import com.masa.operations.masalaboratory.service.ConcreteWorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class ConcreteSampleController {
    @Autowired
    private ConcreteWorkOrderService concreteWorkOrderService;

    @GetMapping(value = "orden/{orderId}/agregar-muestra")
    public ModelAndView addSample(@PathVariable("orderId") Long orderId, ModelMap modelMap) {
        Optional<ConcreteWorkOrder> optionalConcreteWorkOrder = concreteWorkOrderService.getById(orderId);

        // Add 4 specimens for 3, 7, 28 and 28
        List<ConcreteSpecimenDTO> specimens = List.of(
                new ConcreteSpecimenDTO("0001", 3, optionalConcreteWorkOrder.get().getSampleDate().plusDays(3)),
                new ConcreteSpecimenDTO("0002", 7,optionalConcreteWorkOrder.get().getSampleDate().plusDays(7)),
                new ConcreteSpecimenDTO("0003", 28,optionalConcreteWorkOrder.get().getSampleDate().plusDays(28)),
                new ConcreteSpecimenDTO("0004", 28,optionalConcreteWorkOrder.get().getSampleDate().plusDays(28))
        );
        optionalConcreteWorkOrder.ifPresent(concreteWorkOrder -> modelMap.addAttribute("order", concreteWorkOrder));
        modelMap.addAttribute("concreteSample", new ConcreteSampleDTO());
        modelMap.addAttribute("concreteSpecimens", specimens);
        return new ModelAndView("sample-form", modelMap);
    }
}
