package hu.unideb.inf.prt.CalorieCalc.model;

public class Intake {
	
	private Nutrients nutrient;
	private Double amount;
	
	public Intake(Nutrients nutrient, Double amount){
		this.nutrient = nutrient;
		this.amount = amount;
	}
	
	public Nutrients getNutrient(){
		return nutrient;
	}

	public Double getAmount(){
		return amount;
	}
}
