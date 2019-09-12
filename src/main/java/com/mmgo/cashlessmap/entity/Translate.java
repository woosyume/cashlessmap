package com.mmgo.cashlessmap.entity;


import javax.persistence.*;

@Entity
public class Translate {

	@Id
    private String text;
    private String targetLanguage;
    private String translatedText;

    public Translate(String text , String targetLanguage) {
    	this.text = text;
    	this.targetLanguage = targetLanguage;    	
    }

	/**
	 * @return the title
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the title to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the description
	 */
	public String getTranslatedText() {
		return translatedText;
	}

	/**
	 * @param translatedText the description to set
	 */
	public void setDescription(String translatedText) {
		this.translatedText = translatedText;
	}

	/**
	 * @return the targetLanguage
	 */
	public String getTargetLanguage() {
		return targetLanguage;
	}

	/**
	 * @param targetLanguage the targetLanguage to set
	 */
	public void setTargetLanguage(String targetLanguage) {
		this.targetLanguage = targetLanguage;
	}

	/**
	 * @param translatedText the translatedText to set
	 */
	public void setTranslatedText(String translatedText) {
		this.translatedText = translatedText;
	}
}
