package com.example.demo.bean;


public class SendData {

	private String model;
	private String prompt;
	private String negative_prompt;
	private String style_preset;
	private int steps;
	private int cfg_scale;
	private int seed;
	private Boolean upscale;
	private String sampler;
	private String aspect_ratio;
	
	
	private String url;
	
	private String key;
	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public int getCfg_scale() {
		return cfg_scale;
	}

	public void setCfg_scale(int cfg_scale) {
		this.cfg_scale = cfg_scale;
	}

	public int getSeed() {
		return seed;
	}

	public void setSeed(int seed) {
		this.seed = seed;
	}

	public Boolean getUpscale() {
		return upscale;
	}

	public void setUpscale(Boolean upscale) {
		this.upscale = upscale;
	}

	public String getSampler() {
		return sampler;
	}

	public void setSampler(String sampler) {
		this.sampler = sampler;
	}

	public String getAspect_ratio() {
		return aspect_ratio;
	}

	public void setAspect_ratio(String aspect_ratio) {
		this.aspect_ratio = aspect_ratio;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getNegative_prompt() {
		return negative_prompt;
	}

	public void setNegative_prompt(String negative_prompt) {
		this.negative_prompt = negative_prompt;
	}

	public String getStyle_preset() {
		return style_preset;
	}

	public void setStyle_preset(String style_preset) {
		this.style_preset = style_preset;
	}

	
	

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

}
