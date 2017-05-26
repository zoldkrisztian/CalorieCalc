package hu.unideb.inf.prt.CalorieCalc.model;

import java.time.LocalDate;

public class DailyIntakeOfNutrients {

	private Double intakeCarbohydrate;
	private Double intakeProtein;
	private Double intakeFat;
	private LocalDate date;
	
	public DailyIntakeOfNutrients(){
		
	}
	
	public DailyIntakeOfNutrients(Double intakeCarbohydrate, Double intakeProtein, Double intakeFat, LocalDate date) {
		super();
		this.intakeCarbohydrate = intakeCarbohydrate;
		this.intakeProtein = intakeProtein;
		this.intakeFat = intakeFat;
		this.date = date;
	}

	public Double getIntakeCarbohydrate() {
		return intakeCarbohydrate;
	}

	public void setIntakeCarbohydrate(Double intakeCarbohydrate) {
		this.intakeCarbohydrate = intakeCarbohydrate;
	}

	public Double getIntakeProtein() {
		return intakeProtein;
	}

	public void setIntakeProtein(Double intakeProtein) {
		this.intakeProtein = intakeProtein;
	}

	public Double getIntakeFat() {
		return intakeFat;
	}

	public void setIntakeFat(Double intakeFat) {
		this.intakeFat = intakeFat;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
}
