package net.javaguides.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.sms.entity.Student;


public interface StudentRepository extends JpaRepository<Student, Long> {
//jpa repository have two vlaues , type of jpa entity, type of primary key
	
	
}
