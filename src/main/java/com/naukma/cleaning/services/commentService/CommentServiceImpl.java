package com.naukma.cleaning.services.commentService;

import com.naukma.cleaning.dao.CommentDao;
import com.naukma.cleaning.dao.OrderDao;
import com.naukma.cleaning.dao.entities.CommentEntity;
import com.naukma.cleaning.dao.entities.OrderEntity;
import com.naukma.cleaning.models.dtos.CommentDto;
import com.naukma.cleaning.models.order.Comment;

import com.naukma.cleaning.services.orderService.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;
    private final ModelMapper modelMapper;
    private final OrderDao orderDao;

    @Override
    public void createComment(Long orderId, CommentDto commentDto) {
        CommentEntity commentEntity = modelMapper.map(commentDto, CommentEntity.class);
        OrderEntity orderEntity = orderDao.findById(orderId).get();
        orderEntity.setComment(commentEntity);
        commentDao.save(commentEntity);
        orderDao.save(orderEntity);
    }

    @Override
    public void editComment(CommentDto commentDto) {
        CommentEntity commentEntity = modelMapper.map(commentDto, CommentEntity.class);
        commentDao.save(commentEntity);
    }

    @Override
    public void deleteComment(long id) {
        commentDao.deleteById(id);
    }

    @Override
    public CommentDto getComment(long id) {
        CommentEntity commentById = commentDao.findById(id).get();
        return modelMapper.map(commentById, CommentDto.class);
    }


}
