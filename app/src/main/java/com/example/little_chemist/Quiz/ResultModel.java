package com.example.little_chemist.Quiz;

public class ResultModel {
    private String Question;
    private String option;
    private String currect;
    private int optionimage;

    public ResultModel(String question, String option, String currect, int optionimage) {
        Question = question;
        this.option = option;
        this.currect = currect;
        this.optionimage = optionimage;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getCurrect() {
        return currect;
    }

    public void setCurrect(String currect) {
        this.currect = currect;
    }

    public int getOptionimage() {
        return optionimage;
    }

    public void setOptionimage(int optionimage) {
        this.optionimage = optionimage;
    }

    @Override
    public String toString() {
        return "ResultModel{" +
                "Question='" + Question + '\'' +
                ", option='" + option + '\'' +
                ", currect='" + currect + '\'' +
                ", optionimage=" + optionimage +
                '}';
    }
}
