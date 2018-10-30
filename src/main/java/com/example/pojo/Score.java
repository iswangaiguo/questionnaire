package com.example.pojo;

public class Score {
	int questionid;
	int score;
	int categoryid;
	public Score() {
		super();
	}
	public Score(int questionid, int score, int categoryid) {
		super();
		this.questionid = questionid;
		this.score = score;
		this.categoryid = categoryid;
	}
	public int getQuestionid() {
		return questionid;
	}
	public void setQuestionid(int questionid) {
		this.questionid = questionid;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
}
