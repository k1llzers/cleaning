package com.naukma.cleaning.services.proposalService;

import com.naukma.cleaning.models.order.Order;
import com.naukma.cleaning.models.order.Proposal;

public interface ProposalService {
    void createProposal(Proposal proposal);
    void editProposal(Proposal proposal);
    void deleteProposal(long id);
    Proposal getProposal(long id);
}
