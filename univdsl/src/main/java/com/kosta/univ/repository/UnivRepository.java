package com.kosta.univ.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.univ.dto.ProfessorDto;
import com.kosta.univ.entity.Department;
import com.kosta.univ.entity.Professor;
import com.kosta.univ.entity.QDepartment;
import com.kosta.univ.entity.QProfessor;
import com.kosta.univ.entity.QStudent;
import com.kosta.univ.entity.Student;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class UnivRepository {
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	
	//select에서 섞어서 가져올 때는 Tuple타입으로 리턴
	public Tuple findStudentWithDeptNameByStudno(Integer studno) {
		QStudent student = QStudent.student;
		QDepartment department = QDepartment.department;
		
		return jpaQueryFactory.select(student, department.dname)
							.from(student)
							.join(department)
							.on(student.deptno1.eq(department.deptno))
							.where(student.studno.eq(studno))
							.fetchOne();
	}
	
	public Tuple findStudentWithProfNameByStudno(Integer studno) {
		QStudent student = QStudent.student;
		QProfessor professor = QProfessor.professor;
		
		return jpaQueryFactory.select(student, professor.name)
							.from(student)
							.join(professor)
							.on(student.profno.eq(professor.profno))
							.where(student.studno.eq(studno))
							.fetchOne();
	}
	
	public Tuple findStudentWithDnameAndProfNameByStudno(Integer studno) {
		QStudent student = QStudent.student;
		QDepartment department = QDepartment.department;
		QProfessor professor = QProfessor.professor;
		
		return jpaQueryFactory.select(student, department.dname, professor.name)
							.from(student)
							.join(department)
							.on(student.deptno1.eq(department.deptno))
							.join(professor)
							.on(student.profno.eq(professor.profno))
							.where(student.studno.eq(studno))
							.fetchOne();
	}
	
	public List<Student> findStudentListByDname(String dname) {
		QStudent student = QStudent.student;
		QDepartment department = QDepartment.department;
		
		return jpaQueryFactory.select(student)
							.from(student)
							.join(department)
							.on(student.deptno1.eq(department.deptno))
							.where(department.dname.eq(dname))
							.fetch();
	}

	public List<Student> findStudentListByDnameAndGrade(String dname, Integer grade) {
		QStudent student = QStudent.student;
		QDepartment department = QDepartment.department;
		
		return jpaQueryFactory.select(student)
				.from(student)
				.join(department)
				.on(student.deptno1.eq(department.deptno))
				.where(department.dname.eq(dname).and(student.grade.eq(grade)))
				.fetch();
	}
	
	public List<Student> findStudentListByDeptNo1OrDeptNo2(Integer deptno) {
		QStudent student = QStudent.student;
		
		return jpaQueryFactory.selectFrom(student)
							.where(student.deptno1.eq(deptno).or(student.deptno2.eq(deptno)))
							.fetch();
	}
	
	public List<Student> findStudentListByDname1OrDname2(String dname1, String dname2) {
		QStudent student = QStudent.student;
		QDepartment department1 = new QDepartment("department1");
		QDepartment department2 = new QDepartment("department2");
		
		return jpaQueryFactory.selectFrom(student)
							.leftJoin(department1)
							.on(student.deptno1.eq(department1.deptno))
							.leftJoin(department2)
							.on(student.deptno2.eq(department2.deptno))
							.where(department1.dname.eq(dname1).or(department2.dname.eq(dname2)))
							.fetch();
	}

	public List<Student> findStudentListByDname1OrDname2(String dname) {
		QStudent student = QStudent.student;
		QDepartment department1 = new QDepartment("department1");
		QDepartment department2 = new QDepartment("department2");
		
		return jpaQueryFactory.selectFrom(student)
				.leftJoin(department1)
				.on(student.deptno1.eq(department1.deptno))
				.leftJoin(department2)
				.on(student.deptno2.eq(department2.deptno))
				.where(department1.dname.eq(dname).or(department2.dname.eq(dname)))
				.fetch();
	}
	
	public Tuple findProfessorByProfnoWithDname(Integer profno) {
		QProfessor professor = QProfessor.professor;
		QDepartment department = QDepartment.department;
		return jpaQueryFactory.select(professor, department.dname)
							.from(professor)
							.join(department)
							.on(professor.deptno.eq(department.deptno))
							.where(professor.profno.eq(profno))
							.fetchOne();
	}
	
	public Professor findProfessorByStudno(Integer studno) {
		QProfessor professor = QProfessor.professor;
		QStudent student = QStudent.student;
		return jpaQueryFactory.select(professor)
							.from(professor)
							.join(student)
							.on(professor.profno.eq(student.profno))
							.where(student.studno.eq(studno))
							.fetchOne();
	}

	public List<Professor> findProfessorByDeptName(String dname) {
		QProfessor professor = QProfessor.professor;
		QDepartment department = QDepartment.department;
		return jpaQueryFactory.select(professor)
				.from(professor)
				.join(department)
				.on(professor.deptno.eq(department.deptno))
				.where(department.dname.eq(dname))
				.fetch();
	}
	
	public Department findDepartmentByStudNo(Integer studno) {
		QDepartment department = QDepartment.department;
		QStudent student = QStudent.student;
		return jpaQueryFactory.select(department)
							.from(department)
							.join(student)
							.on(department.deptno.eq(student.deptno1))
							.where(student.studno.eq(studno))
							.fetchOne();
	}
	
}
