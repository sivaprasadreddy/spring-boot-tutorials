/**
 * 
 */
package com.sivalabs.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sivalabs.blog.entities.Comment;
import com.sivalabs.blog.entities.Post;
import com.sivalabs.blog.repositories.CommentRepository;
import com.sivalabs.blog.repositories.PostRepository;

/**
 * @author Siva
 *
 */
@Service
@Transactional
public class BlogService 
{
	@Autowired PostRepository postRepository;
	@Autowired CommentRepository commentRepository;
	
	public Page<Post> findPosts(PageRequest postsRequest) 
	{
		Sort sort = new Sort(Direction.DESC, "createdOn");
		int pageNo = postsRequest.getPageNumber();
		int pageSize = postsRequest.getPageSize();
		if(pageNo < 0){
			pageNo = 0;
		}
		if(pageSize < 1) {
			pageSize = 5;
		}
		Pageable pageable = new PageRequest(pageNo, pageSize, sort);
		Page<Post> pageData = postRepository.findAll(pageable);
		
		return pageData;
	}
	
	public Post findPostById(int postId) {
		return postRepository.findOne(postId);
	}
	
	public Post createPost(Post post) {
		return postRepository.save(post);
	}
		
	public Comment createComment(Comment comment) {
		return commentRepository.save(comment);
	}

	public void deletePost(Integer postId)
	{
		postRepository.delete(postId);
	}

	public Page<Comment> findComments(PageRequest request)
	{
		Sort sort = new Sort(Direction.DESC, "createdOn");
		int pageNo = request.getPageNumber();
		int pageSize = request.getPageSize();
		if(pageNo < 0){
			pageNo = 0;
		}
		if(pageSize < 1) {
			pageSize = 5;
		}
		Pageable pageable = new PageRequest(pageNo, pageSize, sort);
		Page<Comment> pageData = commentRepository.findAll(pageable);
		
		return pageData;
	}

	public void deleteComment(Integer commentId)
	{
		commentRepository.delete(commentId);
	}

}
