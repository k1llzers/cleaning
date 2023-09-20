package com.naukma.cleaning.services;

import com.naukma.cleaning.models.order.Comment;

public interface CommentService {
    void createComment(Comment comment);
    void editProposal(Comment comment);
    void deleteComment(long id);
    Comment getComment(long id);
}
