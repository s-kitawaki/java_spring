package com.example.demo.Service;

import java.util.Optional;

import com.example.demo.Entity.StudentMember;

public interface StudentService {

	Iterable<StudentMember> selectStudent();
	
	Optional<StudentMember> selectOneById(int MemberId);

	public void updateStudent(StudentMember student);

	public int insertStudent(StudentMember student);

	public void deleteStudent(Integer id);
}
