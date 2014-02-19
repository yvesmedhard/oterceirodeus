package com.oterceirodeus.codigo_fonte.engine;


import java.awt.Image;
import java.util.ArrayList;


public class Sprite {
private ArrayList<Image> Images = new ArrayList();
	
	public Sprite() {
		// TODO Auto-generated constructor stub
	}
	public Sprite(ArrayList<Image> i) {
		this.Images = i;
	}
	public ArrayList<Image> getImages() {
		return this.Images;
		
	}public void setImages(ArrayList<Image> images) {
		this.Images = images;
	}
}
