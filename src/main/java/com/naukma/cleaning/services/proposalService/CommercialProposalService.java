package com.naukma.cleaning.services.proposalService;

import com.naukma.cleaning.models.order.CommercialProposalDto;

public interface CommercialProposalService {
    void createCommercialProposal(CommercialProposalDto commercialProposalDto);
    void editCommercialProposal(CommercialProposalDto commercialProposalDto);
    void deleteCommercialProposal(long id);
    CommercialProposalDto getCommercialProposal(long id);
}
