package hu.unideb.inf.prt.CalorieCalc.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {

	private String userName;
	//password here.
	private String firstName;
	private String lastName;
	private Double bodyHeight;
	private Double bodyWeight;
	private Integer age;
	private Gender gender;
	private Goal goal;
	private Double carbohydrate;
	private Double protein;
	private Double fat;
	private Double BMR;
	
	private Double intakeCarbohydrate;
	private Double intakeProtein;
	private Double intakeFat;
	private Double intakeBMR;
	
	private List<Intake> intakes = new ArrayList<Intake>();
	private LocalDate date;
	private List<DailyIntakeOfNutrients> week = new ArrayList<DailyIntakeOfNutrients>();
	private boolean deletedUser;
	
	public User(){
		
	}

	public User(String userName, String firstName, String lastName, Double bodyHeight, Double bodyWeight, Integer age,
			Gender gender, Goal goal) {
		super();
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.bodyHeight = bodyHeight;
		this.bodyWeight = bodyWeight;
		this.age = age;
		this.gender = gender;
		this.goal = goal;
		intakeCarbohydrate = 0.0;
		intakeProtein = 0.0;
		intakeFat = 0.0;
		intakeBMR = 0.0;
		date = LocalDate.now();
		deletedUser = false;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Double getBodyHeight() {
		return bodyHeight;
	}

	public void setBodyHeight(Double bodyHeight) {
		this.bodyHeight = bodyHeight;
	}

	public Double getBodyWeight() {
		return bodyWeight;
	}

	public void setBodyWeight(Double bodyWeight) {
		this.bodyWeight = bodyWeight;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Goal getGoal() {
		return goal;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}

	public Double getCarbohydrate() {
		return carbohydrate;
	}

	public void setCarbohydrate(Double carbohydrate) {
		this.carbohydrate = carbohydrate;
	}

	public Double getProtein() {
		return protein;
	}

	public void setProtein(Double protein) {
		this.protein = protein;
	}

	public Double getFat() {
		return fat;
	}

	public void setFat(Double fat) {
		this.fat = fat;
	}

	public Double getBMR() {
		return BMR;
	}

	public void setBMR(Double bMR) {
		BMR = bMR;
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

	public Double getIntakeBMR() {
		return intakeBMR;
	}

	public void setIntakeBMR(Double intakeBMR) {
		this.intakeBMR = intakeBMR;
	}

	public List<Intake> getIntakes() {
		return intakes;
	}

	public void setIntakes(List<Intake> intakes) {
		this.intakes = intakes;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<DailyIntakeOfNutrients> getWeek() {
		return week;
	}

	public void setWeek(List<DailyIntakeOfNutrients> week) {
		this.week = week;
	}

	public boolean isDeletedUser() {
		return deletedUser;
	}

	public void setDeletedUser(boolean deletedUser) {
		this.deletedUser = deletedUser;
	}

	
	@Override
	public String toString() {
		return "Person [userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName + ", bodyHeight="
				+ bodyHeight + ", bodyWeight=" + bodyWeight + ", age=" + age + ", gender=" + gender + ", goal=" + goal
				+ ", carbohydrate=" + carbohydrate + ", protein=" + protein + ", fat=" + fat + ", BMR=" + BMR
				+ ", intakeCarbohydrate=" + intakeCarbohydrate + ", intakeProtein=" + intakeProtein + ", intakeFat="
				+ intakeFat + ", intakeBMR=" + intakeBMR + ", intakes=" + intakes + ", date=" + date + ", week=" + week
				+ ", deletedUser=" + deletedUser + "]";
	}
	
	
	
	
}
