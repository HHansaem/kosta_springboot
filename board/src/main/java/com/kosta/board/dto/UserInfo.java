package com.kosta.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
	private Long id;
	private String nickname;
	private String profile_image;
	private String thumbnail_image;
	
}
