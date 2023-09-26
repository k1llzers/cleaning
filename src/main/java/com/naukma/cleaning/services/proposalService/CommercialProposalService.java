package com.naukma.cleaning.services.proposalService;

import com.naukma.cleaning.models.order.CommercialProposal;

public interface CommercialProposalService {
    void createCommercialProposal(CommercialProposal commercialProposal);
    void editCommercialProposal(CommercialProposal commercialProposal);
    void deleteCommercialProposal(long id);
    CommercialProposal getCommercialProposal(long id);
}
