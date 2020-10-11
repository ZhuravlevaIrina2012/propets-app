package propets.messaging.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import propets.messaging.dao.MessagingRepository;
import propets.messaging.dto.NewPostDto;
import propets.messaging.dto.PostDto;
import propets.messaging.dto.ViewPostsDto;
import propets.messaging.exceptions.WrongParameters;
import propets.messaging.model.Post;

@Service
public class MessagingServiceImpl implements MessagingService {

	@Autowired
	MessagingRepository messagingRepository;
	
	@Autowired
	ModelMapper modelMapper;
	 
	@Override
	public PostDto createPost(String login, NewPostDto newPostDto) {
		Post post = new Post(login, "Anna Smith", "https://www.gravatar.com/avatar/0?d=mp", newPostDto.getText(), newPostDto.getImages());
		messagingRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto updatePost(String id, NewPostDto newPostDto) {
		Post post = messagingRepository.findById(id).orElseThrow(() -> new WrongParameters());
		String avatar = "https://www.gravatar.com/avatar/0?d=mp";
		if (avatar != null) {
			post.setAvatar(avatar);
		}
		String name = "Anna Smith";
		if (name != null) {
			post.setUserName(name);
		}
		String text = newPostDto.getText();
		if (text != null) {
			post.setText(text);
		}
		Set<String> images = newPostDto.getImages();
		if (!images.isEmpty()) {
			images.forEach(post::addImage);
		}
		messagingRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto deletePost(String id) {
		Post post = messagingRepository.findById(id).orElseThrow(() -> new WrongParameters());
		messagingRepository.delete(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto getPost(String id) {
		Post post = messagingRepository.findById(id).orElseThrow(() -> new WrongParameters());
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public ViewPostsDto viewPosts(int itemsOnPage, int currentPage) {
		List<PostDto> posts = messagingRepository.findAll(PageRequest.of(currentPage-1, itemsOnPage))
							.map(p -> modelMapper.map(p, PostDto.class))
							.getContent();
		return new ViewPostsDto(itemsOnPage, currentPage, messagingRepository.findAll().size(), posts);
	}

	@Override
	public List<PostDto> getPosts(Set<String> postsId) {
		return postsId.stream()
				.map(id -> messagingRepository.findById(id).orElseThrow(() -> new WrongParameters()))
				.map(p -> modelMapper.map(p, PostDto.class))
				.collect(Collectors.toList());
	}

}
