package propets.messaging.dto;

import java.util.Set;

import lombok.Getter;

@Getter
public class NewPostDto {
	String userName;
	String avatar;
	String text;
	Set<String> images;
}
