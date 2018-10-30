package com.example.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.example.pojo.Score;

public interface ScoreService{
	
	List<Score> getScore(int categoryId);
	
	int addScore(Score score);
	
	int removeScoresByCategoryId(int categoryId);

	
	List<String> getWeightScore(int index);
	
	Map getScoreAdvise(int index);
}