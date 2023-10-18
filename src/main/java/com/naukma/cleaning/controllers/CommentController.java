package com.naukma.cleaning.controllers;

import com.naukma.cleaning.models.dtos.CommentDto;
import com.naukma.cleaning.models.order.Comment;
import com.naukma.cleaning.services.commentService.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    public final CommentService commentService;

    @PostMapping()
    public void createComment(@RequestBody CommentDto comment) {
        commentService.createComment(comment);
    }

    @PutMapping()
    public void editProposal(@RequestBody CommentDto comment) {
        commentService.editProposal(comment);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }

    @GetMapping("/{id}")
    public CommentDto getComment(@PathVariable Long id) {
        return commentService.getComment(id);
    }

}
