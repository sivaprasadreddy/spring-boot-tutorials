/**
 * 
 */
package com.sivalabs.blog.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sivalabs.blog.entities.Comment;
import com.sivalabs.blog.model.ServiceResponse;
import com.sivalabs.blog.resources.CommentsResource;
import com.sivalabs.blog.services.BlogService;
import com.sivalabs.blog.services.EmailService;

import io.swagger.annotations.ApiImplicitParam;

/**
 * @author Siva
 *
 */
@RestController
@RequestMapping(value="/api/comments")
public class CommentsController
{
	private final static Logger LOGGER = LoggerFactory.getLogger(CommentsController.class);
	
	@Autowired
	private BlogService blogService;
	
	@Autowired EmailService emailService;
	/*
    @ApiImplicitParam(name = "x-auth-token", value = "x-auth-token value", 
        					required = true, dataType = "string", paramType = "header")
    */
	@RequestMapping(value="", method=RequestMethod.GET)
	public ServiceResponse<CommentsResource> findComments(@RequestParam(name="page", defaultValue="0") int page, 
			@RequestParam(name="size", defaultValue="5") int size) {
		PageRequest request = new PageRequest(page, size);
		Page<Comment> pageData = blogService.findComments(request);
		CommentsResource commentsResponse = new CommentsResource(pageData);
		return new ServiceResponse<>(commentsResponse);
	}
	
	@RequestMapping(value="/{commentId}", method=RequestMethod.DELETE)
	public ServiceResponse<Void> deleteCommentById(@PathVariable(value="commentId") Integer commentId) {
		LOGGER.debug("Delete comment id: "+commentId);
		blogService.deleteComment(commentId);
		return new ServiceResponse<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="", method=RequestMethod.DELETE)
	public ServiceResponse<Void> deleteComments(@RequestParam(value="commentIds") String commentIds) {
		LOGGER.debug("Delete comment ids: "+commentIds);
		String[] ids = commentIds.split(",");
		for (String strId : ids)
		{
			blogService.deleteComment(Integer.parseInt(strId));
		}
		return new ServiceResponse<>(HttpStatus.OK);
	}
	
}
