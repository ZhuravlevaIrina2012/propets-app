package propets.messaging.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import propets.messaging.dto.NewPostDto;
import propets.messaging.dto.PostDto;
import propets.messaging.dto.ViewPostsDto;
import propets.messaging.service.MessagingService;

@RestController
@RequestMapping("/message/en/v1")
public class MessagingController {

	@Autowired
	MessagingService messagingService;
	
	@PostMapping("/{login}")
	public PostDto createPost(@PathVariable String login, @RequestBody NewPostDto newPostDto) {
		return messagingService.createPost(login, newPostDto);
	}
	
	@PutMapping("/{id}")
	public PostDto updatePost(@PathVariable String id, @RequestBody NewPostDto newPostDto) {
		return messagingService.updatePost(id, newPostDto);
	}
	
	@DeleteMapping("/{id}")
	public PostDto deletePost(@PathVariable String id) {
		return messagingService.deletePost(id);
	}
	
	@GetMapping("/{id}")
	public PostDto getPost(@PathVariable String id) {
		return messagingService.getPost(id);
	}
	
	@GetMapping("/view")
	public ViewPostsDto viewPosts(@RequestParam int itemsOnPage, @RequestParam int currentPage) {
		return messagingService.viewPosts(itemsOnPage, currentPage);
	}
	
	@PostMapping("/userdata")
	public List<PostDto> getPosts(@RequestBody Set<String> postsId){
		return messagingService.getPosts(postsId);
	}
}
