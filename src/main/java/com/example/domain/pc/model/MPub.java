package com.example.domain.pc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="publishing_company")
public class MPub {
	@Id
	private Integer pcID;
	private String pcName;
}
