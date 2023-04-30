package com.example.demo.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentForm {

	@NotBlank(message="必須項目です")
	@Pattern(regexp="^[0-9]*$", message="数字のみ有効です")
	private String id;
	//氏名
	@NotBlank(message="必須項目です")
	@Length(max=10 , message="10文字以下で入力してください")
	private String name;
	//生年月日
	@NotBlank(message="必須項目です")
	private String birthday;
	//性別
	private int gender;
}
