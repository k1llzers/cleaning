package com.naukma.cleaning.controllers;

import com.naukma.cleaning.models.dtos.CommentDto;
import com.naukma.cleaning.models.order.Comment;
import com.naukma.cleaning.services.commentService.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@Tag(name = "Comment API", description = "Endpoint for operations with comments of orders")
public class CommentController {

    public final CommentService commentService;

    @Operation(summary = "Get comment by id", description = "Get comment by id")
    @GetMapping("/{id}")
    public CommentDto getComment(@PathVariable Long id) {
        return commentService.getComment(id);
    }

    @Operation(summary = "Change comment", description = "Change comment")
    @PutMapping()
    public void editComment(@RequestBody CommentDto comment) {
        commentService.editComment(comment);
    }

    @Operation(summary = "Create new comment for order", description = "Create new comment for order")
    @PostMapping("/{orderId}")
    public void createComment(@PathVariable Long orderId, @RequestBody CommentDto comment) {
        commentService.createComment(orderId, comment);
    }

    @Operation(summary = "Delete comment", description = "Delete comment")
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}
