package com.example.transporter.controller.back;

import com.example.transporter.model.dto.OrderDTO;
import com.example.transporter.model.dto.ReportDTO;
import com.example.transporter.model.mapper.OrderMapper;
import com.example.transporter.service.OrderService;
import com.example.transporter.utils.DateUtil;
import com.example.transporter.utils.ValidatorUtil;
import com.example.transporter.validation.ReportValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/back/report")
public class ReportController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    ReportValidator reportValidator;

    @Autowired
    DateUtil dateUtil;


    @GetMapping(value = {"/", ""})
    public String viewReport(Model model) {
        ReportDTO reportDTO = new ReportDTO();
        Date date = new Date();
        String dateStr = dateUtil.convertDateToString(date, "yyyy-MM-dd");
        reportDTO.setStartDate(dateStr);
        reportDTO.setEndDate(dateStr);

        setModel(model, reportDTO);

        return "back/report";
    }

    @GetMapping(value = {"/search"})
    public String search(Model model, ReportDTO reportDTO, BindingResult bindingResult) {
        reportValidator.validate(reportDTO, bindingResult);
        reportDTO.setSearch(true);

        if (bindingResult.hasErrors()) {
            Date date = new Date();
            String dateStr = DateUtil.convertDateToString(date, "yyyy-MM-dd");
            reportDTO.setStartDate(dateStr);
            reportDTO.setEndDate(dateStr);
            reportDTO.setError(true);
            setModel(model, reportDTO);
            model.addAttribute("errorList", ValidatorUtil.toErrors(bindingResult.getFieldErrors()));

            return "back/report";
        }

        setModel(model, reportDTO);
        return "back/report";
    }

    private void setModel(Model model, ReportDTO reportDTO) {
        List<OrderDTO> orderDTOList = orderMapper.toListDTO(orderService.findReport(reportDTO));
        int countPending =0;
        int countCompleted =0;
        int countShipping =0;
        String progressPending = "PENDING";
        String progressCompleted = "COMPLETED";
        String progressShipping = "SHIPPING";
        for(OrderDTO orderDTO : orderDTOList) {

            if (orderDTO.getProgress().equals(progressPending)){
                countPending += 1;
            }

            if (orderDTO.getProgress().equals(progressCompleted)){
                countCompleted += 1;
            }
            if (orderDTO.getProgress().equals(progressShipping)){
                countShipping += 1;
            }
        }
        reportDTO.setTotalOrder(orderDTOList.size());
        reportDTO.setTotalShipping(countShipping);
        reportDTO.setTotalPending(countPending);
        reportDTO.setTotalCompleted(countCompleted);

        model.addAttribute("orderDTOList", orderDTOList);
        model.addAttribute("reportDTO", reportDTO);
    }

}
