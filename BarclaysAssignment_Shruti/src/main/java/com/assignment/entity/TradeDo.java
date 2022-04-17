package com.assignment.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Shruti.Bodhe
 *
 */
@Entity
@Table(name = "TRADE")
public class TradeDo {

	@EmbeddedId
	private TradeDoPk id;

	@Column(name = "COUNTR_PARTY_ID")
	private String countryPartyId;

	@Column(name = "BOOK_ID")
	private String bookId;

	@Temporal(TemporalType.DATE)
	@Column(name = "MATURITY_DATE")
	private Date maturityDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "IS_EXPIRED")
	private boolean isExpired;

	public TradeDoPk getId() {
		return id;
	}

	public void setId(TradeDoPk id) {
		this.id = id;
	}

	public String getCountryPartyId() {
		return countryPartyId;
	}

	public void setCountryPartyId(String countryPartyId) {
		this.countryPartyId = countryPartyId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isExpired() {
		return isExpired;
	}

	public void setExpired(boolean isExpired) {
		this.isExpired = isExpired;
	}

	@Override
	public String toString() {
		return "TradeDo [id=" + id + ", countryPartyId=" + countryPartyId + ", bookId=" + bookId + ", maturityDate="
				+ maturityDate + ", createdDate=" + createdDate + ", isExpired=" + isExpired + "]";
	}

}
