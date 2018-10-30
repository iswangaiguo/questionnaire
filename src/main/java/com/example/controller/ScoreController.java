package com.example.controller;

import java.io.Console;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ietf.jgss.Oid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pojo.Msg;
import com.example.pojo.Score;
import com.example.service.ScoreService;

@RestController
@RequestMapping("/score")
public class ScoreController {

	@Autowired
	ScoreService scoreService;
	
	@RequestMapping("/getScore.do")
	public Msg getScore(int categoryId) {
		Msg msg = new Msg();
		List<Score> list = scoreService.getScore(categoryId);
		if (list.isEmpty()) {
			return msg.noRecord();
		} else {
			return msg.success().add("list", list);
		}
	}
	
	@RequestMapping(value="/addScore.do",method=RequestMethod.POST) 
	public Msg addScore(@RequestParam Map<String,String> map) {
		try {
			int categoryId = Integer.parseInt(map.get("categoryId"));
			map.remove("categoryId");
			Score score;
			List list = scoreService.getScore(categoryId);
			if (!list.isEmpty()) scoreService.removeScoresByCategoryId(categoryId);
			for (String key : map.keySet()) {
				int questionid = Integer.parseInt(key.substring(1));
				score = new Score(questionid, Integer.parseInt(map.get(key)), categoryId);
				scoreService.addScore(score);
			}
			System.out.println(map);
			return Msg.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Msg.fail();
		}
		
	}
	
	@RequestMapping("/getScoreAdvise.do")
	public Msg getScoreAdvise(int index) {
		Map map = scoreService.getScoreAdvise(index);
		if (map == null) {
			return Msg.noRecord();
		} else {
			return Msg.success().add("scoreAdvise", map);
		}
	}
	
	@RequestMapping("/getWeightScore.do")
	public Msg getWidhtScore(int index) {
		System.out.println(index);
		List<String> list = scoreService.getWeightScore(index);
		if (list == null) {
			return Msg.noRecord();
		} else {
			return Msg.success().add("getWeightScore", list);
		}
	}
	
	
}
