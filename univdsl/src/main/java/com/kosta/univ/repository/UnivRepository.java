package com.kosta.univ.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.univ.entity.QDepartment;
import com.kosta.univ.entity.QStudent;
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
}
