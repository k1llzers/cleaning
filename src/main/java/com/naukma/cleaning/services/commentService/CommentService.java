package com.naukma.cleaning.services.commentService;

import com.naukma.cleaning.models.order.CommentDto;


public interface CommentService {
    void createComment(CommentDto commentDto);
    void editProposal(CommentDto commentDto);
    void deleteComment(long id);
    CommentDto getComment(long id);
}
