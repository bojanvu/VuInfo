package com.algebra.vuinfo.models;

public class QuizResult {
	
	private int place;
	private long quizTime;
	private int answersCount;
	private String playerName;
	
	/**
	 * Class that holds row data of "result" table from "dbquiz.db" database.
	**/
	public QuizResult() {
	}
	/**
	 * Class that holds row data of "result" table from "dbquiz.db" database.
	 * 
	 * @param place
	 * 				integer value 
	 * @param quizTime
	 * 				String value
	 * @param answersCount
	 * 				String value
	 * @param playerName 
	 * 				String value          
	 **/
	public QuizResult(int place, long quizTime,
			int answersCount,String playerName) {
		super();
		this.place = place;
		this.quizTime = quizTime;
		this.answersCount = answersCount;
		this.playerName = playerName;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public long getQuizTime() {
		return quizTime;
	}

	public void setQuizTime(long quizTime) {
		this.quizTime = quizTime;
	}

	public int getAnswersCount() {
		return answersCount;
	}

	public void setAnswersCount(int answersCount) {
		this.answersCount = answersCount;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	
}
