package com.naukma.cleaning.viewControllers;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.naukma.cleaning.models.dtos.AddressDto;
import com.naukma.cleaning.services.addressService.AddressService;
import com.naukma.cleaning.services.userService.UserService;
import com.naukma.cleaning.viewControllers.vcDtos.AddressDtoVC;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class UserVC {
	
	private final UserService userService;
	private final AddressService addressService;

	@GetMapping("/viewAddresses")
	public String viewAdresses(Model model, Principal principal) {
		var userID = userService.getUserByEmail(principal.getName()).getId();	
		var addresses = addressService.getAddressesByUserId(userID);
		List<AddressDtoVC> addressesVC = new ArrayList<>();
		for (var address : addresses){
			var add = new AddressDtoVC(address);
			add.setHasAttachedOrders(addressService.hasAttachedOrders(address.getId()));
			addressesVC.add(add);
		}
		model.addAttribute("addressesList", addressesVC);
		return "user-addresses";
	}

	@PostMapping("/addAddress")
	public String addAddress(@ModelAttribute @Valid AddressDto addressDto, Model model, Principal principal) {
		var userID = userService.getUserByEmail(principal.getName()).getId();
		addressService.createAddress(userID, addressDto);
		return "redirect:/viewAddresses";
	}

	@PostMapping("/removeAddress")
	public String removeAddress(@RequestParam("id") long id) {
		addressService.deleteAddress(id); 
		return "redirect:/viewAddresses";
	}

	@PostMapping("/editAddress")
	public String editAddress(@ModelAttribute @Valid AddressDto addressDto) {
		addressService.editAddress(addressDto);
		return "redirect:/viewAddresses";
	}
}

