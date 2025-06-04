package com.toiukha.productfav.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.toiukha.productfav.model.ProductFavVO.CompositeDetail;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "productfav")
@IdClass(CompositeDetail.class)
public class ProductFavVO implements java.io.Serializable {

	@Id
	@ManyToOne
	@Column(name = "memid")
	private Integer memId;

	@Id
	@ManyToOne
	@Column(name = "itemid")
	private Integer itemId;

	@Column(name = "favat")
	private Timestamp favAt;

	public ProductFavVO() {
		super();
	}

	public ProductFavVO(Integer memId, Integer itemId, Timestamp favAt) {
		super();
		this.memId = memId;
		this.itemId = itemId;
		this.favAt = favAt;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Timestamp getFavAt() {
		return favAt;
	}

	public void setFavAt(Timestamp favAt) {
		this.favAt = favAt;
	}

	static class CompositeDetail implements Serializable {
		private static final long serialVersionUID = 1L;

		private Integer memId;
		private Integer itemId;

		// 一定要有無參數建構子
		public CompositeDetail() {
			super();
		}

		public CompositeDetail(Integer memId, Integer itemId) {
			super();
			this.memId = memId;
			this.itemId = itemId;
		}

		public Integer getMemId() {
			return memId;
		}

		public void setMemId(Integer memId) {
			this.memId = memId;
		}

		public Integer getItemId() {
			return itemId;
		}

		public void setItemId(Integer could) {
			this.itemId = itemId;
		}

		// 一定要 override 此類別的 hashCode() 與 equals() 方法！
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
			result = prime * result + ((memId == null) ? 0 : memId.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;

			if (obj != null && getClass() == obj.getClass()) {
				CompositeDetail compositeKey = (CompositeDetail) obj;
				if (memId.equals(compositeKey.memId) && itemId.equals(compositeKey.itemId)) {
					return true;
				}
			}

			return false;
		}
	}
}
