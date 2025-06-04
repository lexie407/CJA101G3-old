package com.toiukha.order.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderVO implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ODRDID")
	private Integer ordId;

	@Column(name = "MEMID")
	private Integer memId;

	@Column(name = "ORDSTA")
	private Integer ordSta;

	@Column(name = "CREDATE")
	private Timestamp creDate;

	public OrderVO() {
		super();
	}

	public OrderVO(Integer ordId, Integer memId, Integer ordSta, Timestamp creDate) {
		super();
		this.ordId = ordId;
		this.memId = memId;
		this.ordSta = ordSta;
		this.creDate = creDate;
	}

	public Integer getOrdId() {
		return ordId;
	}

	public void setOrdId(Integer ordId) {
		this.ordId = ordId;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public Integer getOrdSta() {
		return ordSta;
	}

	public void setOrdSta(Integer ordSta) {
		this.ordSta = ordSta;
	}

	public Timestamp getCreDate() {
		return creDate;
	}

	public void setCreDate(Timestamp creDate) {
		this.creDate = creDate;
	}

}
