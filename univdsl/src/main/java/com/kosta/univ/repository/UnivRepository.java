package com.kosta.univ.repository;

import java.util.List;

import org.apache.tomcat.jni.OS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	
	public List<Student> findStudentByName(String name) {
		QStudent student = QStudent.student;
		
		return jpaQueryFactory.select(student)
							.from(student)
							.where(student.name.eq(name))
							.fetch();
	}
	
	public List<Student> findStudentByDeptno(Integer deptno) {
		QStudent student = QStudent.student;
		
		return jpaQueryFactory.select(student)
							.from(student)
							.where(student.deptno1.eq(deptno))
							.fetch();
	}
	
	
}
