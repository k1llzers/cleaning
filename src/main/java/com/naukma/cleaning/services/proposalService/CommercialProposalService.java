package com.naukma.cleaning.services.proposalService;

import com.naukma.cleaning.models.dtos.CommercialProposalDto;
import com.naukma.cleaning.models.order.CommercialProposal;

import java.util.List;

public interface CommercialProposalService {
    CommercialProposal createCommercialProposal(CommercialProposal commercialProposal);
    CommercialProposalDto createCommercialProposal(CommercialProposalDto commercialProposalDto);
    CommercialProposal editCommercialProposal(CommercialProposal commercialProposal);
    CommercialProposalDto editCommercialProposal(CommercialProposalDto commercialProposalDto);
    void deleteCommercialProposal(long id);
    CommercialProposal getCommercialProposal(long id);
    CommercialProposalDto getCommercialProposalDto(long id);
    List<CommercialProposal> getAllCommercialProposals();
}
