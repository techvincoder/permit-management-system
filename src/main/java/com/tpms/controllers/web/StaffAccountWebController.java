package com.tpms.controllers.web;

import com.tpms.entities.Council;
import com.tpms.entities.Role;
import com.tpms.entities.StaffAccounts;
import com.tpms.services.CouncilService;
import com.tpms.services.RoleService;
import com.tpms.services.StaffAccountsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/staff/staff-accounts")
public class StaffAccountWebController {

    private final StaffAccountsService staffAccountsService;
    private final CouncilService councilService;
    private final RoleService roleService;

    public StaffAccountWebController(StaffAccountsService staffAccountsService, CouncilService councilService, RoleService roleService) {
        this.staffAccountsService = staffAccountsService;
        this.councilService = councilService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String showStaffList(Model model) {
        List<?> staffList = staffAccountsService.getAllStaffAccountsDtos();
        model.addAttribute("staffAccounts", staffList);
        return "staff/staff-list";
    }

    @GetMapping("/new")
    public String showNewStaffForm(Model model) {
        List<?> allCouncils = councilService.getAllCouncilDtos();
        List<Role> allRoles = roleService.getAllRole();

        StaffAccounts staffAccount = new StaffAccounts();
        staffAccount.setCouncil(new Council()); // initialize to avoid null errors
        staffAccount.setRole(new HashSet<>()); // initialize empty roles
        staffAccount.setIsActive(true); // default active

        model.addAttribute("staffAccount", staffAccount);
        model.addAttribute("allCouncils", allCouncils);
        model.addAttribute("allRoles", allRoles);
        return "staff/staff-form";
    }

    @GetMapping("/{id}/edit")
    public String showEditStaffForm(@PathVariable Long id, Model model) {
        StaffAccounts staffAccount = staffAccountsService.findStaffAccountEntityById(id);
        List<?> allCouncils = councilService.getAllCouncilDtos();
        List<Role> allRoles = roleService.getAllRole();

        model.addAttribute("staffAccount", staffAccount);
        model.addAttribute("allCouncils", allCouncils);
        model.addAttribute("allRoles", allRoles);
        return "staff/staff-form";
    }

    @PostMapping("/save")
    public String saveStaffAccount(@ModelAttribute("staffAccount") StaffAccounts staffAccount,
                                   @RequestParam(value = "roles", required = false) List<Long> roleIds) {
        // Re-attach the council entity
        if (staffAccount.getCouncil() != null && staffAccount.getCouncil().getId() != null) {
            Council council = councilService.findCouncilEntityById(staffAccount.getCouncil().getId());
            staffAccount.setCouncil(council);
        }

        // Re-attach roles
        Set<Role> roles = new HashSet<>();
        if (roleIds != null) {
            for (Long roleId : roleIds) {
                roles.add(roleService.findRoleEntityById(roleId));
            }
        }
        staffAccount.setRole(roles);

        staffAccountsService.addStaffAccount(staffAccount);
        return "redirect:/staff/staff-accounts";
    }

    @GetMapping("/{id}/delete")
    public String deleteStaffAccount(@PathVariable Long id) {
        staffAccountsService.deleteStaffAccountById(id);
        return "redirect:/staff/staff-accounts";
    }
}
