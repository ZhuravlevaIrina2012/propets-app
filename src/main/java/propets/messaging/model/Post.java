package propets.messaging.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import propets.messaging.upload.FileUpload;

@Getter
@EqualsAndHashCode(of = { "id" })
@ToString
@Document(collection = "message")
public class Post {

	String id;
	String userLogin;
	@Setter
	String userName;
	@Setter
	String avatar;
	LocalDateTime datePost;
	@Setter
	String text;
	Set<String> images;
	
	public Post() {
		datePost = LocalDateTime.now();
		images = new HashSet<>();
	}

	public Post(String userLogin, String userName, String avatar, String text, Set<String> images) {
		this();
		this.userLogin = userLogin;
		this.userName = userName;
		this.avatar = FileUpload.loadFile(avatar);
		this.text = text;
		this.images = images.stream()
							.map(i -> FileUpload.loadFile(i))
							.collect(Collectors.toSet());
	}
	
	public boolean addImage(String image) {
		return images.add(FileUpload.loadFile(image));
	}
	
	public boolean removeImage(String image) {
		return images.remove(image);
	}
}
