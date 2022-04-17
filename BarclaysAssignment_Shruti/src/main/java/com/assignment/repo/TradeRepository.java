package com.assignment.repo;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assignment.dto.TradeDto;
import com.assignment.entity.TradeDo;
import com.assignment.entity.TradeDoPk;

/**
 * @author Shruti.Bodhe
 *
 */
public interface TradeRepository extends JpaRepository<TradeDo, String> {

	/**
	 * @param dto
	 * @return
	 */
	public default TradeDo importDto(TradeDto dto) {
		TradeDo entity = null;
		if (dto != null) {
			entity = new TradeDo();
			BeanUtils.copyProperties(dto, entity);
			TradeDoPk pk = new TradeDoPk();
			pk.setTradeId(dto.getTradeId());
			pk.setVersion(dto.getVersion());
			entity.setId(pk);
		}
		return entity;
	}

	/**
	 * @param entity
	 * @return
	 */
	public default TradeDto exportDto(TradeDo entity) {
		TradeDto dto = null;
		if (entity != null) {
			dto = new TradeDto();
			BeanUtils.copyProperties(entity, dto);
			dto.setTradeId(entity.getId().getTradeId());
			dto.setVersion(entity.getId().getVersion());

		}
		return dto;
	}

	@Query("select max(t.id.version) from TradeDo t where t.id.tradeId=:tradeId ")
	public Integer findLatestVersionByTradeId(@Param("tradeId") String tradeId);

	@Modifying
	@Query("update TradeDo t set t.isExpired=:isExpired where t.isExpired!=true and current_date > t.maturityDate ")
	public Integer updateIsExpired(@Param("isExpired") Boolean isExpired);

}
