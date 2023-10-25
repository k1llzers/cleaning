package com.naukma.cleaning.controllers;

import com.naukma.cleaning.models.dtos.CommercialProposalDto;
import com.naukma.cleaning.models.order.CommercialProposal;
import com.naukma.cleaning.services.proposalService.CommercialProposalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "CommercialProposal API", description = "Endpoint for operations with commercial proposals")
public class CommercialProposalController {
    private final CommercialProposalService commercialProposalService;
    @Operation(summary = "Create new commercial proposal", description = "Create new commercial proposal")
    @PostMapping
    public CommercialProposalDto addProposal(@RequestBody @Valid CommercialProposalDto commercialProposal){
        return commercialProposalService.createCommercialProposal(commercialProposal);
    }

    @Operation(summary = "Change commercial proposal", description = "Change commercial proposal")
    @PutMapping
    public CommercialProposalDto editProposal(@RequestBody @Valid CommercialProposalDto commercialProposal){
        return commercialProposalService.editCommercialProposal(commercialProposal);
    }

    @Operation(summary = "Delete commercial proposal", description = "Delete commercial proposal")
    @DeleteMapping("/{id}")
    public void deleteProposal(@PathVariable Long id){
        commercialProposalService.deleteCommercialProposal(id);
    }

    @Operation(summary = "Get commercial proposal by id", description = "Get commercial proposal by id")
    @GetMapping("/{id}")
    public CommercialProposalDto getProposalById(@PathVariable Long id){
        return commercialProposalService.getCommercialProposalDto(id);
    }
}
