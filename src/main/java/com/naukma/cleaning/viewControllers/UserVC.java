package com.naukma.cleaning.viewControllers;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.naukma.cleaning.models.dtos.AddressDto;
import com.naukma.cleaning.services.addressService.AddressService;
import com.naukma.cleaning.services.userService.UserService;
import com.naukma.cleaning.viewControllers.vcDtos.AddressDtoVC;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.logging.Logger;


@Controller
@RequiredArgsConstructor
public class UserVC {
	
	private final Logger logger = Logger.getLogger(UserVC.class.getName());
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
	public ResponseEntity<?> addAddress(@RequestBody AddressDto addressDto, Model model, Principal principal) {
		logger.info(addressDto.toString());
		var userID = userService.getUserByEmail(principal.getName()).getId();
		addressService.createAddress(userID, addressDto);
		var addressID = addressService.getAddressesByUserId(userID).get(addressService.getAddressesByUserId(userID).size() - 1).getId();
		return ResponseEntity.ok(addressID);
	}

	@PostMapping("/removeAddress")
	public ResponseEntity<?> removeAddress(@RequestParam("id") long id) {
		addressService.deleteAddress(id); 
		return ResponseEntity.ok(id);
	}

	@PostMapping("/editAddress")
	public ResponseEntity<?> editAddress(@RequestBody AddressDto addressDto) {
		addressService.editAddress(addressDto);
		return ResponseEntity.ok(addressDto);
	}
}

