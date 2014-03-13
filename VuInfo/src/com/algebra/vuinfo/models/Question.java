package com.algebra.vuinfo.models;

public class Question {

	private String questionId;
	private String question;
	private String questionAnswCorr;
	private String questionAnswInc1;
	private String questionAnswInc2;

	public Question() {
	}

	public Question(String questionAnswCorr, String questionAnswInc1,
			String questionAnswInc2) {
		super();
		this.questionAnswCorr = questionAnswCorr;
		this.questionAnswInc1 = questionAnswInc1;
		this.questionAnswInc2 = questionAnswInc2;
	}


	public Question(String questionId, String question,
			String questionAnswCorr, String questionAnswInc1,
			String questionAnswInc2) {
		super();
		this.questionId = questionId;
		this.question = question;
		this.questionAnswCorr = questionAnswCorr;
		this.questionAnswInc1 = questionAnswInc1;
		this.questionAnswInc2 = questionAnswInc2;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQuestionAnswCorr() {
		return questionAnswCorr;
	}

	public void setQuestionAnswCorr(String questionAnswCorr) {
		this.questionAnswCorr = questionAnswCorr;
	}

	public String getQuestionAnswInc1() {
		return questionAnswInc1;
	}

	public void setQuestionAnswInc1(String questionAnswInc1) {
		this.questionAnswInc1 = questionAnswInc1;
	}

	public String getQuestionAnswInc2() {
		return questionAnswInc2;
	}

	public void setQuestionAnswInc2(String questionAnswInc2) {
		this.questionAnswInc2 = questionAnswInc2;
	}

	
}
