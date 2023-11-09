package com.naukma.cleaning.viewControllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.naukma.cleaning.models.dtos.AddressDto;
import com.naukma.cleaning.services.addressService.AddressService;
import com.naukma.cleaning.services.userService.UserService;
import com.naukma.cleaning.viewControllers.TLUtils.Tables.InputsContainer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.util.Arrays;


@Controller
@RequiredArgsConstructor
public class UserVC {
	
	private final UserService userService;
	private final AddressService addressService;

	@GetMapping("/viewAddresses")
	public String viewAdresses(Model model, Principal principal) {
		var userID = userService.getUserByEmail(principal.getName()).getId();	
		model.addAttribute("addressesList", addressService.getAddressesByUserId(userID));
		var addressOverlayData = new AddressOverlayData();
		addressOverlayData.buttonText = "Add Address";
		addressOverlayData.headerText = "Add Address";
		addressOverlayData.formAction = "addAddress";
		addressOverlayData.inputsData = new InputsContainer(
			Arrays.asList(
				new InputsContainer.InputData("text", "City", "city", true),
				new InputsContainer.InputData("text", "Street", "street", true),
				new InputsContainer.InputData("text", "House Number", "houseNumber", true),
				new InputsContainer.InputData("text", "Flat Number", "flatNumber", false)
			)
		);
		model.addAttribute("addressOverlayData", addressOverlayData);
		return "user-addresses";
	}

	public static class AddressOverlayData{
		public String buttonText;
		public String headerText;
		public String formAction;
		public InputsContainer inputsData;
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

