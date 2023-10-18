package com.naukma.cleaning.services.proposalService;

import com.naukma.cleaning.dao.CommercialProposalDao;
import com.naukma.cleaning.dao.entities.CommercialProposalEntity;
import com.naukma.cleaning.models.order.CommercialProposal;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommercialProposalServiceImpl implements CommercialProposalService {
    private CommercialProposalDao commercialProposalDao;
    private ModelMapper modelMapper;

    public CommercialProposalServiceImpl(CommercialProposalDao commercialProposalDao, ModelMapper modelMapper) {
        this.commercialProposalDao = commercialProposalDao;
        this.modelMapper = modelMapper;
    }


    @Override
    public CommercialProposal createCommercialProposal(CommercialProposal commercialProposal) {
        var commercialProposalEntity = modelMapper.map(commercialProposal, CommercialProposalEntity.class);
        return modelMapper.map(commercialProposalDao.save(commercialProposalEntity), CommercialProposal.class);
    }

    @Override
    public CommercialProposal editCommercialProposal(CommercialProposal commercialProposal) {
        var commercialProposalEntity = modelMapper.map(commercialProposal, CommercialProposalEntity.class);
        return modelMapper.map(commercialProposalDao.save(commercialProposalEntity), CommercialProposal.class);
    }

    @Override
    public void deleteCommercialProposal(long id) {
        commercialProposalDao.deleteById(id);
    }

    @Override
    public CommercialProposal getCommercialProposal(long id) {
        CommercialProposalEntity commercialProposalEntity = commercialProposalDao.findById(id).get();
        return modelMapper.map(commercialProposalEntity, CommercialProposal.class);
    }
}
