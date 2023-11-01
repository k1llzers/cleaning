package com.naukma.cleaning.services.commentService;

import com.naukma.cleaning.models.dtos.CommentDto;
import com.naukma.cleaning.models.order.Comment;


public interface CommentService {
    void createComment(Long orderId, CommentDto comment);
    void editComment(CommentDto comment);
    void deleteComment(long id);
    CommentDto getComment(long id);
}
