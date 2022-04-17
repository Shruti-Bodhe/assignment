package com.assignment.dto;

import java.util.Date;

/**
 * @author Shruti.Bodhe
 *
 */
public class TradeDto {

	private String tradeId;

	private Integer version;

	private String countryPartyId;

	private String bookId;

	private Date maturityDate;

	private Date createdDate;

	private boolean isExpired;

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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
		return "TradeDto [tradeId=" + tradeId + ", version=" + version + ", countryPartyId=" + countryPartyId
				+ ", bookId=" + bookId + ", maturityDate=" + maturityDate + ", createdDate=" + createdDate
				+ ", isExpired=" + isExpired + "]";
	}

}
