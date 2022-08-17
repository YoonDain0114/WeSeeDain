package com.ezen.WeSee.dto;

public class MovieDTO {
	private int movienum;		//등록번호
	private String opendate;	//개봉일
	private String title;		//영화 제목
	private String image;		//포스터 이미지
	private String director;	//감독
	private String actor;		//출연진
	private int times;			//상영시간
	private int age;			//관람제한가
	private String moviecontents;	//시놉시스
	private String moviefile;	//예고편 영상명
	private String genre;		//장르
	private int viewcount;		//조회수
	private String videofile;
	private String videofile2;
	private String videofile3;
	
	
	public int getMovienum() {
		return movienum;
	}
	public void setMovienum(int movienum) {
		this.movienum = movienum;
	}
	public String getOpendate() {
		return opendate;
	}
	public void setOpendate(String opendate) {
		this.opendate = opendate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMoviecontents() {
		return moviecontents;
	}
	public void setMoviecontents(String moviecontents) {
		this.moviecontents = moviecontents;
	}
	public String getMoviefile() {
		return moviefile;
	}
	public void setMoviefile(String moviefile) {
		this.moviefile = moviefile;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getViewcount() {
		return viewcount;
	}
	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}
	public String getVideofile() {
		return videofile;
	}
	public void setVideofile(String videofile) {
		this.videofile = videofile;
	}
	public String getVideofile2() {
		return videofile2;
	}
	public void setVideofile2(String videofile2) {
		this.videofile2 = videofile2;
	}
	public String getVideofile3() {
		return videofile3;
	}
	public void setVideofile3(String videofile3) {
		this.videofile3 = videofile3;
	}
}
