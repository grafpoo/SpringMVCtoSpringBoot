package liveproject.webreport.result;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "result")
public class Result implements java.io.Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String division;

	@Column
	private Date date;

	@Column
	private String homeTeam;

	@Column
	private String awayTeam;

	@Column
	private int homeGoalsFullTime;

	@Column
	private int awayGoalsFullTime;

	@Column
	private int homeGoalsHalfTime;

	@Column
	private int awayGoalsHalfTime;

	@Column
	private int homeGoalsShots;

	@Column
	private int awayGoalsShots;

	public Long getId() {
		return id;
	}


	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

	public int getHomeGoalsFullTime() {
		return homeGoalsFullTime;
	}

	public void setHomeGoalsFullTime(int homeGoalsFullTime) {
		this.homeGoalsFullTime = homeGoalsFullTime;
	}

	public int getAwayGoalsFullTime() {
		return awayGoalsFullTime;
	}

	public void setAwayGoalsFullTime(int awayGoalsFullTime) {
		this.awayGoalsFullTime = awayGoalsFullTime;
	}

	public int getHomeGoalsHalfTime() {
		return homeGoalsHalfTime;
	}

	public void setHomeGoalsHalfTime(int homeGoalsHalfTime) {
		this.homeGoalsHalfTime = homeGoalsHalfTime;
	}

	public int getAwayGoalsHalfTime() {
		return awayGoalsHalfTime;
	}

	public void setAwayGoalsHalfTime(int awayGoalsHalfTime) {
		this.awayGoalsHalfTime = awayGoalsHalfTime;
	}

	public int getHomeGoalsShots() {
		return homeGoalsShots;
	}

	public void setHomeGoalsShots(int homeGoalsShots) {
		this.homeGoalsShots = homeGoalsShots;
	}

	public int getAwayGoalsShots() {
		return awayGoalsShots;
	}

	public void setAwayGoalsShots(int awayGoalsShots) {
		this.awayGoalsShots = awayGoalsShots;
	}

}
