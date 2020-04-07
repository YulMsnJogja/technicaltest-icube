package com.icube.branch.controller;

import com.icube.branch.model.Branch;
import com.icube.branch.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BranchController {

    private final BranchService branchService;

    @Autowired
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String branch(Model model){
        model.addAttribute("branches", new Branch());
        return "formBranch";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView branchList(){
        ModelAndView model =new ModelAndView();
        try {
            List<Branch> branchList = branchService.getAllBranch();
            model.addObject("branchLists",branchList);
            model.setViewName("list");

        }catch (Exception e){
            e.printStackTrace();
        }
        return model;
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String addBranch(@Valid Branch branch, BindingResult result, Model model){
        try {
            if (result.hasErrors()) {
                model.addAttribute("branches", new Branch());
                return "formBranch";
            }

            branchService.saveOrUpdate(branch);
            model.addAttribute("branchLists", branchService.getAllBranch());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/list";
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String deleteBranch(@PathVariable Long id, Model model){
        try {
            branchService.delete(id);
            model.addAttribute("branches",branchService.getAllBranch());
        }catch (Exception e){

        }
        return "redirect:/list";
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public String updateBranch(@PathVariable Long id, Model model){

        model.addAttribute("branches",branchService.getBranchById(id));
        return "formBranch";
    }
}
