package com.naukma.cleaning.controllers;

import com.naukma.cleaning.models.order.CommercialProposal;
import com.naukma.cleaning.services.proposalService.CommercialProposalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/commercial-proposals")
public class CommercialProposalController {
    private final CommercialProposalService commercialProposalService;

    @GetMapping("/{id}")
    public CommercialProposal getProposalById(@PathVariable Long id){
        return commercialProposalService.getCommercialProposal(id);
    }

    @PostMapping
    public CommercialProposal addProposal(@RequestBody @Valid CommercialProposal commercialProposal){
        return commercialProposalService.createCommercialProposal(commercialProposal);
    }

    @DeleteMapping("/{id}")
    public void deleteProposal(@PathVariable Long id){
        commercialProposalService.deleteCommercialProposal(id);
    }

    @PutMapping
    public CommercialProposal editProposal(@RequestBody @Valid CommercialProposal commercialProposal){
        return commercialProposalService.editCommercialProposal(commercialProposal);
    }
}
