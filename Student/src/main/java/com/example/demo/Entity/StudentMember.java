package com.example.demo.Entity;

import java.util.Date;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentMember  {

	@Id
	@NotBlank(message="IDは必須項目です")
	public int MemberId;
	//氏名
	private String name;
	//生年月日
	private Date birthday;
	//性別
	private int gender;

}
