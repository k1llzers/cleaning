package com.naukma.cleaning.services.commentService;

import com.naukma.cleaning.dao.CommentDao;
import com.naukma.cleaning.dao.entities.CommentEntity;
import com.naukma.cleaning.models.order.CommentDto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentDao commentDao;
    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentDao commentDao, ModelMapper modelMapper) {
        this.commentDao = commentDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createComment(CommentDto commentDto) {
        CommentEntity commentEntity = modelMapper.map(commentDto, CommentEntity.class);
        commentDao.save(commentEntity);
    }

    @Override
    public void editProposal(CommentDto commentDto) {
        CommentEntity commentEntity = modelMapper.map(commentDto, CommentEntity.class);
        commentDao.save(commentEntity);
    }

    @Override
    public void deleteComment(long id) {
        commentDao.deleteById(id);
    }

    @Override
    public CommentDto getComment(long id) {
        CommentEntity commentById = commentDao.getReferenceById(id);
        return modelMapper.map(commentById,CommentDto.class);
    }
}
