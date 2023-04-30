package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.Entity.StudentMember;
import com.example.demo.Repository.StudentRepository;

@SpringBootApplication

public class StudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}
	
	@Autowired
	StudentRepository repository;
	
	private void excute() {
		showList();
	}

	private void showList() {
		System.out.println("--- 全件取得開始 ---");
		//リポジトリを使用して全件取得を実施、結果を取得
		Iterable<StudentMember> quizzes = repository.findAll();
		for (StudentMember quiz : quizzes) {
			System.out.println(quiz);
		}
		System.out.println("--- 全件取得完了 ---");
	}
}
