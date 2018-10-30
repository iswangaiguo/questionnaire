package com.example.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.example.pojo.Score;

public interface ScoreMapper {
	
	List<Score> selectScore(int categoryId);
	
	int updateScore(Score score);

	int deleteByCategoryId(int categoryid);

	int insertByCategoryId(Score score);

	Map<String, BigDecimal> selectTotalScore(int categoryId);
}
