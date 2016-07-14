/**
 * 
 */
package com.sivalabs.blog.web.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sivalabs.blog.entities.Comment;
import com.sivalabs.blog.entities.Post;
import com.sivalabs.blog.model.CommentDTO;
import com.sivalabs.blog.model.PostDTO;
import com.sivalabs.blog.model.ServiceResponse;
import com.sivalabs.blog.resources.PostsResource;
import com.sivalabs.blog.services.BlogService;
import com.sivalabs.blog.services.EmailService;
import com.sivalabs.blog.utils.BeanCopyUtils;

/**
 * @author Siva
 *
 */
@RestController
@RequestMapping(value="/api/posts")
public class PostController
{
	private final static Logger LOGGER = LoggerFactory.getLogger(PostController.class);
	
	@Autowired private BlogService blogService;
	@Autowired EmailService emailService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ServiceResponse<PostsResource> findPosts(@RequestParam(name="page", defaultValue="0") int page, 
			@RequestParam(name="size", defaultValue="5") int size) {
		PageRequest request = new PageRequest(page, size);
		Page<Post> pageData = blogService.findPosts(request);
		PostsResource postsResponse = new PostsResource(pageData);
		
		ServiceResponse<PostsResource> resp = new ServiceResponse<>(postsResponse);
		return resp;
	}
	
	@RequestMapping(value="/{postId}", method=RequestMethod.GET)
	public ServiceResponse<PostDTO> findPostById(@PathVariable(value="postId") Integer postId) {
		LOGGER.debug("View Post id: "+postId);
		Post post = blogService.findPostById(postId);
		return new ServiceResponse<PostDTO>(BeanCopyUtils.toPostDTO(post));
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ServiceResponse<PostDTO> createPost(@RequestBody PostDTO postDTO, 
								HttpServletRequest request) 
	{
		Post post = BeanCopyUtils.toPostEntity(postDTO);
		Post createdPost = this.blogService.createPost(post);
		String subject = "New Post ["+post.getTitle()+"] published";
		String content = "A new post with title \""+post.getTitle()+"\" is published.\nThanks,\nSiva";
		emailService.sendEmail(subject, content);
		return new ServiceResponse<PostDTO>(BeanCopyUtils.toPostDTO(createdPost));
	}
	
	@RequestMapping(value="/{postId}", method=RequestMethod.DELETE)
	public ServiceResponse<Void> deletePostById(@PathVariable(value="postId") Integer postId) {
		LOGGER.debug("Delete Post id: "+postId);
		blogService.deletePost(postId);
		return new ServiceResponse<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/{postId}/comments", method=RequestMethod.POST)
	public ServiceResponse<CommentDTO> addComment(@PathVariable(value="postId") Integer postId, @RequestBody CommentDTO commentDTO) {
		Comment comment = BeanCopyUtils.toCommentEntity(commentDTO);
		comment.setPost(new Post(postId));
		Comment createdComment = blogService.createComment(comment );
		String subject = "New Comment on ["+comment.getPost().getTitle()+"] published";
		String content = "A new post with title \""+comment.getPost().getTitle()+"\" is published.\nThanks,\nSiva";
		emailService.sendEmail(subject, content);
		return new ServiceResponse<>(BeanCopyUtils.toCommentDTO(createdComment));
	}
	
	
}
