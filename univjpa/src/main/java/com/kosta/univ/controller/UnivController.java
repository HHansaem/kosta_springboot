package com.kosta.univ.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.univ.dto.DepartmentDto;
import com.kosta.univ.dto.ProfessorDto;
import com.kosta.univ.dto.StudentDto;
import com.kosta.univ.service.UnivService;

@RestController  //이 안에 있는 모든 메서드가 비동기 즉, ResponseBody(뷰를 주고받는 게 아니라 데이터만 요청하고 받음 like ajax)
public class UnivController {
	
	@Autowired
	private UnivService univService;
	
	@PostMapping("/regDept")  //파라미터가 RequestBody : JSON으로 보내야 함
	public ResponseEntity<String> regDepartment(@RequestBody DepartmentDto departmentDto) {
		try {
			univService.saveDepartment(departmentDto);
			return new ResponseEntity<String>("학과 정상 등록", HttpStatus.OK);  //(데이터, 전송여부)
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/regStud")  //파라미터가 객체 : Form Data로 보내야 함
	public ResponseEntity<String> regStud(StudentDto studDto) {
		try {
			univService.saveStudent(studDto);
			return new ResponseEntity<String>("학생 정상 등록", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/regProf")
	public ResponseEntity<String> regProf(@RequestBody ProfessorDto profDto) {
		try {
			univService.saveProfessor(profDto);
			return new ResponseEntity<String>("교수 정상 등록", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/studInfo")
	public ResponseEntity<List<StudentDto>> studentInfo(@RequestParam("name") String name) {
		try {
			List<StudentDto> studDtoList = univService.studentListByName(name);
			return new ResponseEntity<List<StudentDto>>(studDtoList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<StudentDto>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/studInfoByNo")
	public ResponseEntity<StudentDto> studentInfoByNo(@RequestParam("studno") Integer studno) {
		try {
			StudentDto studDto = univService.studentByStudentno(studno);
			return new ResponseEntity<StudentDto>(studDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<StudentDto>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/deptInfoByName")  //학과명으로 학과조회
	public ResponseEntity<DepartmentDto> deptInfoByName(@RequestParam("dname") String dname) {
		try {
			DepartmentDto deptDto = univService.departmentByDeptName(dname);
			return new ResponseEntity<DepartmentDto>(deptDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<DepartmentDto>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/deptInfoByNo")  //학과번호로 학과조회
	public ResponseEntity<DepartmentDto> deptInfoByNo(@RequestParam("deptno") Integer deptno) {
      try {
         DepartmentDto deptDto = univService.departmentByDeptNo(deptno);
         return new ResponseEntity<DepartmentDto>(deptDto, HttpStatus.OK);
      } catch(Exception e) {
         e.printStackTrace();
         return new ResponseEntity<DepartmentDto> (HttpStatus.BAD_REQUEST);
      }
   }
	
	@GetMapping("/deptInfoByPart")  //학부로 학과조회
	public ResponseEntity<List<DepartmentDto>> deptInfoByPart(@RequestParam("part") String part) {
      try {
         List<DepartmentDto> deptDtoList = univService.departmentListByPart(part);
         return new ResponseEntity<List<DepartmentDto>>(deptDtoList,HttpStatus.OK);
      } catch(Exception e) {
         e.printStackTrace();
         return new ResponseEntity<List<DepartmentDto>> (HttpStatus.BAD_REQUEST);
      }
   }

	
	@GetMapping("/deptInfoByBuild")  //건물로 학과조회
	public ResponseEntity<List<DepartmentDto>> deptInfoByBuild(@RequestParam("build") String build){
	      try {
	         List<DepartmentDto> deptDtoList = univService.departmentListByBuild(build);
	         return new ResponseEntity<List<DepartmentDto>>(deptDtoList,HttpStatus.OK);
	      } catch(Exception e) {
	         e.printStackTrace();
	         return new ResponseEntity<List<DepartmentDto>> (HttpStatus.BAD_REQUEST);
	      }
	   }

	@GetMapping("/studIndeptNo")  //학과번호에 소속된 학생 조회 (주전공)
	public ResponseEntity<List<StudentDto>> studInDeptNo(@RequestParam("deptno1") Integer deptno1){
	      try {
	         List<StudentDto> stdDtoList = univService.studentListInDept1ByDeptNo(deptno1);
	         return new ResponseEntity<List<StudentDto>>(stdDtoList,HttpStatus.OK);
	      } catch(Exception e) {
	         e.printStackTrace();
	         return new ResponseEntity<List<StudentDto>> (HttpStatus.BAD_REQUEST);
	      }
	   }

	@GetMapping("/studIndeptName")  //학과명에 소속된 학생 조회 (주전공)
	public ResponseEntity<List<StudentDto>> studInDeptName(@RequestParam("dname") String dname){
	      try {
	         List<StudentDto> stdDtoList = univService.studentListInDept1ByDeptName(dname);
	         return new ResponseEntity<List<StudentDto>>(stdDtoList,HttpStatus.OK);
	      } catch(Exception e) {
	         e.printStackTrace();
	         return new ResponseEntity<List<StudentDto>> (HttpStatus.BAD_REQUEST);
	      }
	   }

	@GetMapping("/studIndeptNo2")  //학과번호에 소속된 학생 조회 (부전공)
	public ResponseEntity<List<StudentDto>> studInDeptNo2(@RequestParam("deptno2") Integer deptno2){
	      try {
	         List<StudentDto> stdDtoList = univService.studentListInDept2ByDeptNo(deptno2);
	         return new ResponseEntity<List<StudentDto>>(stdDtoList,HttpStatus.OK);
	      } catch(Exception e) {
	         e.printStackTrace();
	         return new ResponseEntity<List<StudentDto>> (HttpStatus.BAD_REQUEST);
	      }
	   }

	@GetMapping("/studIndeptName2")  //학과명에 소속된 학생 조회 (부전공)
	public ResponseEntity<List<StudentDto>> studInDeptName2(@RequestParam("dname") String dname){
	      try {
	         List<StudentDto> stdDtoList = univService.studentListInDept2ByDeptName(dname);
	         return new ResponseEntity<List<StudentDto>>(stdDtoList,HttpStatus.OK);
	      } catch(Exception e) {
	         e.printStackTrace();
	         return new ResponseEntity<List<StudentDto>> (HttpStatus.BAD_REQUEST);
	      }
	   }

	
	@GetMapping("/studInProfNo")  //교수번호에 소속된 학생 조회
	public ResponseEntity<List<StudentDto>> studInProfNo(@RequestParam("profno") Integer profno){
	      try {
	         List<StudentDto> stdDtoList = univService.studentListByProfNo(profno);
	         System.out.println(stdDtoList);
	         return new ResponseEntity<List<StudentDto>> (stdDtoList,HttpStatus.OK);
	      } catch(Exception e) {
	         e.printStackTrace();
	         return new ResponseEntity<List<StudentDto>> (HttpStatus.BAD_REQUEST);
	      }
	   }
	
	@GetMapping("/profInfoByNo")  //교수번호로 교수 조회
	public ResponseEntity<ProfessorDto> profInfoByNo(@RequestParam("profno") Integer profno){
	      try {
	         ProfessorDto profDto = univService.professorByProfNo(profno);
	         return new ResponseEntity<ProfessorDto>(profDto, HttpStatus.OK);
	      } catch(Exception e) {
	         e.printStackTrace();
	         return new ResponseEntity<ProfessorDto> (HttpStatus.BAD_REQUEST);
	      }
	   }

	@GetMapping("/profInfoByName")  //교수명으로 교수 조회
	public ResponseEntity<List<ProfessorDto>> profInfoByName(@RequestParam("name") String name){
	      try {
	         List<ProfessorDto> profDto = univService.professorListByProfName(name);
	         return new ResponseEntity<List<ProfessorDto>>(profDto, HttpStatus.OK);
	      } catch(Exception e) {
	         e.printStackTrace();
	         return new ResponseEntity<List<ProfessorDto>> (HttpStatus.BAD_REQUEST);
	      }
	   }

	@GetMapping("/profInDeptNo")  //학과번호에 소속된 교수 조회
	public ResponseEntity<List<ProfessorDto>> profInDeptno(@RequestParam("deptno") Integer deptno){
	      try {
	         List<ProfessorDto> profDto = univService.professorListByDeptNo(deptno);
	         return new ResponseEntity<List<ProfessorDto>>(profDto, HttpStatus.OK);
	      } catch(Exception e) {
	         e.printStackTrace();
	         return new ResponseEntity<List<ProfessorDto>> (HttpStatus.BAD_REQUEST);
	      }
	   }

	@GetMapping("/profInDeptName")  //학과명에 소속된 교수 조회
	public ResponseEntity<List<ProfessorDto>> profInDeptName(@RequestParam("dname") String dname){
	      try {
	         List<ProfessorDto> profDto = univService.professorListByDeptName(dname);
	         return new ResponseEntity<List<ProfessorDto>>(profDto, HttpStatus.OK);
	      } catch(Exception e) {
	         e.printStackTrace();
	         return new ResponseEntity<List<ProfessorDto>> (HttpStatus.BAD_REQUEST);
	      }
	   }
	
}
