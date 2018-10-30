package com.example.serviceImpl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.ScoreMapper;
import com.example.pojo.Msg;
import com.example.pojo.Score;
import com.example.service.ScoreService;

@Service
public class ScoreServiceImpl implements ScoreService {

	@Autowired
	ScoreMapper scoreMapper;

	@Override
	public List<Score> getScore(int categoryId) {
		return scoreMapper.selectScore(categoryId);

	}

	@Override
	public int addScore(Score score) {
		return scoreMapper.insertByCategoryId(score);
	}

	@Override
	public int removeScoresByCategoryId(int categoryId) {
		scoreMapper.deleteByCategoryId(categoryId);
		return 0;
	}


	@Override
	public List<String> getWeightScore(int index) {
		Map map = scoreMapper.selectTotalScore(index+1);
		if (map == null) {
			return null;
		}
		DecimalFormat df = new DecimalFormat("#.00");
		int sumScore = Integer.parseInt(map.get("scores").toString());
		List list = new ArrayList<>();
			if (index == 0) {
				list.add(df.format(sumScore*0.26));
				list.add(df.format(sumScore*0.18));
				list.add(df.format(sumScore*0.24));
				list.add(df.format(sumScore*0.14));
				list.add(df.format(sumScore*0.18));
			} else if (index == 1) {
				list.add(df.format(sumScore*0.30));
				list.add(df.format(sumScore*0.12));
				list.add(df.format(sumScore*0.14));
				list.add(df.format(sumScore*0.31));
				list.add(df.format(sumScore*0.13));
			} else if (index == 2) {
				list.add(df.format(sumScore*0.20));
				list.add(df.format(sumScore*0.18));
				list.add(df.format(sumScore*0.13));
				list.add(df.format(sumScore*0.34));
				list.add(df.format(sumScore*0.15));
			} else if (index == 3) {
				list.add(df.format(sumScore*0.14));
				list.add(df.format(sumScore*0.21));
				list.add(df.format(sumScore*0.18));
				list.add(df.format(sumScore*0.29));
				list.add(df.format(sumScore*0.18));
			} else {
				list.add(df.format(sumScore*0.15));
				list.add(df.format(sumScore*0.31));
				list.add(df.format(sumScore*0.23));
				list.add(df.format(sumScore*0.16));
				list.add(df.format(sumScore*0.15));
			}
			
			return list;
	}

	public int addScop(List<Integer> list, int start, int end) {
		int sum = 0;
		for (int i = start; i <= end; i++) {
			sum += list.get(i);
		}
		System.out.println("开始" + (start + 1) + "结束" + (end + 1) + "sum=" + sum);
		return sum;
	}

	@Override
	public Map getScoreAdvise(int index) {
		Map map =  scoreMapper.selectTotalScore(index+1);
		if (map == null) {
			return null;
		}
		String[] strings = {"项目在产出、成本和时间进度上实现了项目原定的大部分目标，按投入成本计算，项目获得了重大的经济效益，对社会发展有良好的影响。",
							"项目在产出、成本和时间进度上实现了项目原定的一部分目标，项目或投资超支过多或时间进度延误过长，按成本计算，项目获得部分经济效益；项目对社会发展的作用和影响是积极的。",
							"项目在产出、成本和时间进度上只能实现原定的少部分目标；按成本计算，项目效益很小或很难以确定；项目对社会发展没有或只有极小的积极作用和影响。",
							"项目原定的各项目标基本上都没实现，项目效益为零或负值，对社会发展的作用和影响是消极的或有害的。或项目被撤销，终止等。"};
		int sumScore = Integer.parseInt(map.get("scores").toString());
		if (sumScore >= 80) {
			map.put("advise", strings[0]);
		} else if (sumScore >= 60) {
			map.put("advise", strings[1]);
		} else if (sumScore >= 40) {
			map.put("advise", strings[2]);
		} else {
			map.put("advise", strings[3]);
		} 
		return map;
	}

}
