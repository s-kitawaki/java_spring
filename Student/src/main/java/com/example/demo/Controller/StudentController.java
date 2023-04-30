package com.example.demo.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Entity.StudentMember;
import com.example.demo.Service.StudentService;
import com.example.demo.form.StudentForm;

@Controller
public class StudentController {

	@Autowired
	StudentService service;

	@GetMapping("student")
	public String ShowStudent() {
		return "student";
	}


	/*
	 * 照会画面に遷移
	 */
	@PostMapping(value = "selectStudent", params = "select")
	public String selectStudent(StudentMember studentDto, Model model) {
		Iterable<StudentMember> list = service.selectStudent();

		model.addAttribute("list", list);

		return "student/selectStudent";
	}


	/*
	 * 更新確認画面に遷移
	 */
	@PostMapping("/selectStudent/update/{id}")
	public String showupdate(StudentForm studentForm, StudentMember studentMember, @PathVariable Integer id, Model model) {

		Optional<StudentMember> studentOpt = service.selectOneById(id);

		StudentMember studentmember = studentOpt.get();

		model.addAttribute("studentForm", studentForm);
		model.addAttribute("memberId", studentmember.getMemberId());
		model.addAttribute("name", studentmember.getName());
		model.addAttribute("birthday", studentmember.getBirthday());
		model.addAttribute("gender", studentmember.getGender());

		return "student/updateStudent";
	}


	/*
	 * 更新情報を取得しDBを更新
	 */
	@PostMapping("/updateStudent/{id}")
	public String update(Model model, @PathVariable Integer id, @Validated @ModelAttribute StudentForm studentForm ,BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			// エラーが存在する場合には自画面へ遷移する。
			Optional<StudentMember> studentOpt = service.selectOneById(id);

			StudentMember studentmember = studentOpt.get();

			model.addAttribute("studentForm", studentForm);
			model.addAttribute("memberId", studentmember.getMemberId());
			model.addAttribute("name", studentmember.getName());
			model.addAttribute("birthday", studentmember.getBirthday());
			model.addAttribute("gender", studentmember.getGender());
			return "student/updateStudent";
		}
		
		String getBirthday = studentForm.getBirthday();
		
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date birthday = null;
		try {
			birthday = sdFormat.parse(getBirthday);
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		StudentMember studentMember = new StudentMember();
		studentMember.setMemberId(Integer.parseInt(studentForm.getId()));
		studentMember.setName(studentForm.getName());
		studentMember.setBirthday(birthday);
		studentMember.setGender(studentForm.getGender());

		service.updateStudent(studentMember);
		model.addAttribute("title", "更新完了しました");
		model.addAttribute("url", "http://localhost:8080/student");
		return "student/afterStudent";

	}



	/* 
	 * 生徒情報を削除
	 */
	@PostMapping("/deleteStudent/delete/{id}")
	public String delete(Model model, @PathVariable Integer id) {
		service.deleteStudent(id);

		model.addAttribute("title", "削除完了しました");
		model.addAttribute("url", "http://localhost:8080/student");
		return "student/afterStudent";
	}


	/*
	 * 生徒情報を追加情報入力に遷移
	 */
	@PostMapping(value = "addStudent", params = "add")
	public String add(StudentForm studentForm, Model model) {
		model.addAttribute("studentForm", studentForm);
		return "student/insertStudent";
	}


	/*
	 * 生徒情報を追加
	 */
	@PostMapping("/insertStudent")
	public String insert(@Validated @ModelAttribute StudentForm studentForm ,BindingResult bindingResult, Model model) {
		String backUrl = "student/insertStudent";
		String nextUrl = "student/afterStudent";
		if (bindingResult.hasErrors()) {
			// エラーが存在する場合には自画面へ遷移する。
			if(studentForm.getGender() == 0) {
				model.addAttribute("Exgender", "性別を選択してください");
			}
			return backUrl;
		}
		
		if(studentForm.getGender() == 0) {
			model.addAttribute("Exgender", "性別を選択してください");
			return backUrl;
		}
		
		String getBirthday = studentForm.getBirthday();

		
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date birthday = null;
		try {
			birthday = sdFormat.parse(getBirthday);
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		StudentMember studentMember = new StudentMember();
		studentMember.setMemberId(Integer.parseInt(studentForm.getId()));
		studentMember.setName(studentForm.getName());
		studentMember.setBirthday(birthday);
		studentMember.setGender(studentForm.getGender());
		
		// 生徒追加を行う
		int memberFlg = service.insertStudent(studentMember);
		
		// 返却値によって遷移する画面と返却する値を変更する
		if (memberFlg == 1) {
			model.addAttribute("title", "追加完了しました");
			model.addAttribute("url", "http://localhost:8080/student");
			return nextUrl;
		} else {
			model.addAttribute("msg", "すでに存在します");
			return backUrl;
		}
	}
	
	/*
	 * ページトップに戻る
	 */
//	@PostMapping(value = "selectStudent", params = "pageTop")
//	public String pageTop() {
//		System.out.println("pagetop");
//		return "student/selectStudent";
//	}
}
