package com.flywise.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FeedbackDto {

	private int feedbackId;
	
	private String feedback;
	
	private AppUserDto userDto;
	
	public FeedbackDto(String feedback) {
		super();
		this.feedback = feedback;
	}
}
