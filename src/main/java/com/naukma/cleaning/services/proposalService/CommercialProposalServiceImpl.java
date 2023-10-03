package com.naukma.cleaning.services.proposalService;

import com.naukma.cleaning.dao.CommercialProposalDao;
import com.naukma.cleaning.dao.entities.CommercialProposalEntity;
import com.naukma.cleaning.models.order.CommercialProposalDto;

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
    public void createCommercialProposal(CommercialProposalDto commercialProposalDto) {
        var commercialProposalEntity = modelMapper.map(commercialProposalDto, CommercialProposalEntity.class);
        commercialProposalDao.save(commercialProposalEntity);
    }

    @Override
    public void editCommercialProposal(CommercialProposalDto commercialProposalDto) {
        var commercialProposalEntity = modelMapper.map(commercialProposalDto, CommercialProposalEntity.class);
        commercialProposalDao.save(commercialProposalEntity);
    }

    @Override
    public void deleteCommercialProposal(long id) {
        commercialProposalDao.deleteById(id);
    }

    @Override
    public CommercialProposalDto getCommercialProposal(long id) {
        var commercialProposalEntity = commercialProposalDao.getReferenceById(id);
        return modelMapper.map(commercialProposalEntity, CommercialProposalDto.class);
    }
}
