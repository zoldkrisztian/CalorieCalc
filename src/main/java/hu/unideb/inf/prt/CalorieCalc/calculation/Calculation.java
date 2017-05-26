package hu.unideb.inf.prt.CalorieCalc.calculation;

import java.time.LocalDate;
import java.util.List;

import hu.unideb.inf.prt.CalorieCalc.model.DailyIntakeOfNutrients;
import hu.unideb.inf.prt.CalorieCalc.model.Gender;
import hu.unideb.inf.prt.CalorieCalc.model.Goal;
import hu.unideb.inf.prt.CalorieCalc.model.Intake;
import hu.unideb.inf.prt.CalorieCalc.model.Nutrients;
import hu.unideb.inf.prt.CalorieCalc.model.User;

public class Calculation {
	
	private static final Double KcalPerOneGrammCh = 4.1;
	
	private static final Double KcalPerOneGrammP = 5.4;
	
	private static final Double KcalPerOneGrammF = 9.3;
	
	private static final Double CarbohydratePercentage = 0.48;
	
	private static final Double ProteinPercentage = 0.32;
	
	private static final Double FatPercentage = 0.2;
	
	public static double getBMR(User user, double bodyweight){
		
		if(user.getAge() >= 10 && user.getAge() <= 18)
			return (user.getGender() == Gender.FEMALE) ? (12.2 * bodyweight + 746) : (17.5 * bodyweight + 651);
			
			else if(user.getAge() > 18 && user.getAge() <= 30)
				return (user.getGender() == Gender.FEMALE) ? (14.7 * bodyweight + 446) : (15.3 * bodyweight + 679);
		
				else if(user.getAge() > 30 && user.getAge() <= 60)
					return (user.getGender() == Gender.FEMALE) ? (8.7 * bodyweight + 829) : (11.6 * bodyweight + 879);
					
					else if(user.getAge() > 60)
						return (user.getGender() == Gender.FEMALE) ? (10.5 * bodyweight + 596) : (13.5 * bodyweight + 487);
	
		
		return 0;
	}
	
	public static double getSuitableWeight(User user){
		
		return (user.getBodyHeight() - 100) * 0.9;
	}
	
	public static double getKcalPerDayToKeepBodyWeight(User user, double bodyweight){
		return getBMR(user, bodyweight) * 1.6;
	}
	
	public static double getKcalPerDayToLoseBodyWeight(User user, double bodyweight){
		return getBMR(user, bodyweight) * 1.1;
	}
	
	public static double getFatPercentage(User user){
		int gender = (user.getGender() == Gender.FEMALE) ? 0 : 1;
		return (1.2 * user.getBodyWeight() / Math.pow(user.getBodyHeight() / 100.0, 2)) + (0.23 * user.getAge()) - 5.4 - (10.8 * gender);
	}
	
	public static void setDailyNutrients( User u){
		double localBMR;
		if(u.getGoal() == Goal.LOSE_BODYWEIGHT)
			localBMR = (getFatPercentage(u) > 30) ? getKcalPerDayToLoseBodyWeight(u, getSuitableWeight(u))
					: getKcalPerDayToLoseBodyWeight(u, u.getBodyWeight());
			else
				localBMR = (getFatPercentage(u) > 30) ? getKcalPerDayToKeepBodyWeight(u, getSuitableWeight(u)) :
					getKcalPerDayToKeepBodyWeight(u, u.getBodyWeight());
				
		u.setCarbohydrate(localBMR * CarbohydratePercentage / KcalPerOneGrammCh);
		u.setProtein(localBMR * ProteinPercentage / KcalPerOneGrammP);
		u.setFat(localBMR * FatPercentage / KcalPerOneGrammF);
		u.setBMR(u.getCarbohydrate() + u.getProtein() + u.getFat());
	}
	
	public static void updateGotBMR(User user){
		user.setIntakeBMR(user.getIntakeCarbohydrate() + user.getIntakeProtein() + user.getIntakeFat());
	}
	
	public static void increaseIntakeCarbohydrate(Double intakeCarbohydrate, User user){
		
		user.setIntakeCarbohydrate(user.getIntakeCarbohydrate() + intakeCarbohydrate);
		Intake intake = new Intake(Nutrients.CARBOHYDRATE, intakeCarbohydrate);
		user.getIntakes().add(intake);
		updateGotBMR(user);
		
	}
	
	public static void increaseIntakeProtein(Double intakeProtein, User user){
		user.setIntakeProtein(user.getIntakeProtein() + intakeProtein);
		Intake intake = new Intake(Nutrients.PROTEIN, intakeProtein);
		user.getIntakes().add(intake);
		updateGotBMR(user);
		
	}
	
	public static void increaseIntakeFat(Double intakeFat, User user){
		user.setIntakeFat(user.getIntakeFat() + intakeFat);
		Intake intake = new Intake(Nutrients.FAT, intakeFat);
		user.getIntakes().add(intake);
		updateGotBMR(user);
		
	}
	
	public static void resetGotNutrients(List<User> users){
		for( User user : users){
			if(LocalDate.now().minusDays(1).isAfter(user.getDay()) || LocalDate.now().minusDays(1).equals(user.getDay())){
				if (user.getWeek().size() >= 6) 
					user.getWeek().remove(0);
				user.getWeek().add(new DailyIntakeOfNutrients(user.getIntakeCarbohydrate(), user.getIntakeProtein(), user.getIntakeFat(), user.getDay()));
				user.setDay(LocalDate.now());
				user.setIntakeCarbohydrate(0.0);
				user.setIntakeProtein(0.0);
				user.setIntakeFat(0.0);
				user.setIntakeBMR(0.0);
			}else if(LocalDate.now().isBefore(user.getDay())){
				user.setDay(LocalDate.now());
				user.setIntakeCarbohydrate(0.0);
				user.setIntakeProtein(0.0);
				user.setIntakeFat(0.0);
				user.setIntakeBMR(0.0);
				
			}
		}
	}
	
	public static void resetUndos(List<User> users){
		for(User user : users){
			user.getIntakes().clear();
		}
	}
	
	public static void handleSkippedDays(List<DailyIntakeOfNutrients> week) {
		int gap;

		if (week.size() >= 1) {
			if ((gap = week.get(week.size() - 1).getDate().until(LocalDate.now()).getDays()) > 0) {

				for (int i = 0; i < gap - 1; i++) {
					DailyIntakeOfNutrients filler = new DailyIntakeOfNutrients(0.0, 0.0, 0.0, week.get(week.size() - 1).getDate().plusDays(1));
					week.add(filler);
				}
				int plusSize;
				if ((plusSize = week.size()) > 7) {
					plusSize -= 7;
					for (int i = 0; i < plusSize; i++) {
						week.remove(0);
					}
				}
			} else if (gap < 0) {
				gap = Math.abs(gap);
				if (gap < week.size())
					for (int i = 0; i <= gap; i++) {
						week.remove(week.size() - 1);
					}
				else
					week.clear();
			}
		}

	}

}
