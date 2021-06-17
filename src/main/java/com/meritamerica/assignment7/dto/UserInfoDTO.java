package com.meritamerica.assignment7.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoDTO {
	private String jwt;
	private String roles;
}
