package com.naukma.cleaning.services.proposalService;

import com.naukma.cleaning.models.order.CommercialProposalDto;

public interface CommercialProposalService {
    CommercialProposalDto createCommercialProposal(CommercialProposalDto commercialProposalDto);
    CommercialProposalDto editCommercialProposal(CommercialProposalDto commercialProposalDto);
    void deleteCommercialProposal(long id);
    CommercialProposalDto getCommercialProposal(long id);
}
