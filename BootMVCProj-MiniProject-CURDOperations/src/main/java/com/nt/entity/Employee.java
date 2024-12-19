package com.nt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="emp")
@Data
public class Employee {
	@Id
	@SequenceGenerator(name="gen1",sequenceName = "empno_seq1",initialValue = 1000, allocationSize = 1)
	@GeneratedValue(generator = "gen1",strategy = GenerationType.SEQUENCE)
	private Integer empno;
	private  String  ename;
	private  String  job="CLERK";
	private   Double sal;
	private  Integer  deptno;

}
