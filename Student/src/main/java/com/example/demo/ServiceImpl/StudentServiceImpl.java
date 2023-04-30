package com.example.demo.ServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Entity.StudentMember;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.Service.StudentService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	StudentRepository repository;

	@Override
	public Iterable<StudentMember> selectStudent() {
		// TODO 自動生成されたメソッド・スタブ
		return repository.selectAll();
	}

	@Override
	public void updateStudent(StudentMember student) {
		// TODO 自動生成されたメソッド・スタブ
		repository.save(student);
	}

	@Override
	public int insertStudent(StudentMember student) {
		// TODO 自動生成されたメソッド・スタブ
		// DBに指定したIDが存在しているかチェック
		int cheackMember = repository.checkMemberId(student.getMemberId());
		
		int memberFlg = 0;
		
		if (cheackMember != 1) {
			repository.insetMember(student.getMemberId(), student.getName(), student.getBirthday(), student.getGender());
			memberFlg = 1;
		}
		return memberFlg;
	}

	@Override
	public void deleteStudent(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		repository.deleteById(id);
	}

	@Override
	public Optional<StudentMember> selectOneById(int MemberId) {
		// TODO 自動生成されたメソッド・スタブ
		return repository.findById(MemberId);
	}

}
