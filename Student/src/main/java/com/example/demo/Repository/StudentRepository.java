package com.example.demo.Repository;

import java.util.Date;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.StudentMember;


public interface StudentRepository extends CrudRepository<StudentMember, Integer> {
	
	// 情報の存在チェック
	@Query("SELECT COUNT(*) FROM student_member WHERE member_id = :memberid")
	int checkMemberId(@Param("memberid")int memberId);
	
	// 生徒情報の追加
	@Modifying
	@Query("INSERT INTO student_member VALUES (:id,:name,:date,:gender)")
	void insetMember(@Param("id")int memberId
			        ,@Param("name")String name
			        ,@Param("date")Date birthday
			        ,@Param("gender")int gender);
	
	// 生徒情報をすべて取得
	@Query("SELECT * FROM student_member ORDER BY member_id")
	Iterable<StudentMember> selectAll();
}
