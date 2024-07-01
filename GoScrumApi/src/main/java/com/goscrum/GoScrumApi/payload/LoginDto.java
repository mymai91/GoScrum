package com.GoScrum.GoScrumApi.payload;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
	private String usernameOrEmail;
	private String password;
}
