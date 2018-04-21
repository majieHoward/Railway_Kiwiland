package com.howard.www.railway.question.entity;

/**
 * 问题的实体对象保存问题的所有属性
 * 
 * @author mayijie
 *
 */
public class ToDoQuestionInfo {
	/**
	 * 问题的序号
	 */
	private int sort;
	/**
	 * 问题的描述
	 */
	private String questionDescribe;
	
	/**
	 * 问题的入参
	 */
	private String entryParameter;
	
	/**
	 * 问题的返回结果集
	 */
	private String resultSet;
	
	/**
	 * 问题的处理方法
	 */
	private String processingMethod;
	
	private char startStation;
	
	private char endStation;
	
	public char getStartStation() {
		return startStation;
	}

	public void setStartStation(char startStation) {
		this.startStation = startStation;
	}

	public char getEndStation() {
		return endStation;
	}

	public void setEndStation(char endStation) {
		this.endStation = endStation;
	}

	private StringBuffer sample=new StringBuffer("In the complete data below.");

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getQuestionDescribe() {
		return questionDescribe;
	}

	public void setQuestionDescribe(String questionDescribe) {
		this.questionDescribe = questionDescribe;
	}

	public String getEntryParameter() {
		return entryParameter;
	}

	public void setEntryParameter(String entryParameter) {
		this.entryParameter = entryParameter;
	}

	public String getResultSet() {
		return resultSet;
	}

	public void setResultSet(String resultSet) {
		this.resultSet = resultSet;
	}

	public String getProcessingMethod() {
		return processingMethod;
	}

	public void setProcessingMethod(String processingMethod) {
		this.processingMethod = processingMethod;
	}

	public ToDoQuestionInfo(String[] pendingQuestionItem,int sort) {
		if(pendingQuestionItem!=null&&pendingQuestionItem.length>0){
			this.setQuestionDescribe(pendingQuestionItem[0]);
			this.setSort(sort);
			this.setProcessingMethod(pendingQuestionItem[2]);
			this.setEntryParameter(pendingQuestionItem[1]);
		}
	}
	
	public StringBuffer getSample() {
		return sample;
	}

	public void setSample(StringBuffer sample) {
		this.sample = sample;
	}

	public String obtainDetailsOfTheProblem(){
		StringBuffer param=new StringBuffer();
		param.append(this.getSort());
		param.append(".");
		param.append(this.getQuestionDescribe());
		param.append("\n");
		param.append("Output #");
		param.append(this.getSort());
		param.append(":");
		param.append(this.getResultSet());
		return param.toString();
	}
	
	
}
