package com.study.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

// Entity는 DB 테이블을 의미, JPA가 알아서 읽어서 해석해
@Data
@NoArgsConstructor
@Entity
@Table(name = "board")
public class Board {
	@Id // primary key 의미
	@GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY 는 mariaDB
	private Integer id;

	private String title;
	private String content;
	private String filename;
	private String filepath;
}
