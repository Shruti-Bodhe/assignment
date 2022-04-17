package com.assignment.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Shruti.Bodhe
 *
 */
@Embeddable
public class TradeDoPk implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "TRADE_ID")
	private String tradeId;

	@Column(name = "VERSION")
	private Integer version;

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

	@Override
	public String toString() {
		return "TradeDoPk [tradeId=" + tradeId + ", version=" + version + "]";
	}

}
