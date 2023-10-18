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
    public CommercialProposalDto createCommercialProposal(CommercialProposalDto commercialProposalDto) {
        var commercialProposalEntity = modelMapper.map(commercialProposalDto, CommercialProposalEntity.class);
        return modelMapper.map(commercialProposalDao.save(commercialProposalEntity),CommercialProposalDto.class);
    }

    @Override
    public CommercialProposalDto editCommercialProposal(CommercialProposalDto commercialProposalDto) {
        var commercialProposalEntity = modelMapper.map(commercialProposalDto, CommercialProposalEntity.class);
        return modelMapper.map(commercialProposalDao.save(commercialProposalEntity),CommercialProposalDto.class);
    }

    @Override
    public void deleteCommercialProposal(long id) {
        commercialProposalDao.deleteById(id);
    }

    @Override
    public CommercialProposalDto getCommercialProposal(long id) {
        CommercialProposalEntity commercialProposalEntity = commercialProposalDao.findById(id).get();
        return modelMapper.map(commercialProposalEntity, CommercialProposalDto.class);
    }
}
