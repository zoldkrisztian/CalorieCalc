package hu.unideb.inf.prt.CalorieCalc.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import hu.unideb.inf.prt.CalorieCalc.DAO.UserDAOImpl;
import hu.unideb.inf.prt.CalorieCalc.calculation.Calculation;
import hu.unideb.inf.prt.CalorieCalc.model.DailyIntakeOfNutrients;
import hu.unideb.inf.prt.CalorieCalc.model.Gender;
import hu.unideb.inf.prt.CalorieCalc.model.Goal;
import hu.unideb.inf.prt.CalorieCalc.model.Intake;
import hu.unideb.inf.prt.CalorieCalc.model.Nutrients;
import hu.unideb.inf.prt.CalorieCalc.model.User;

/**
 * @author Zöld Krisztián
 *
 */
public class CalculationTest {

	private User userHusband;
	private User userWife;
	private User user;
	private List<User> users;

	@Before
	public void setCouple() throws Exception {
		user = new User();
		userHusband = new User("DeadPool", "Ryan", "Reynolds", Double.valueOf(180), Double.valueOf(80), 40, Gender.MALE,
				Goal.KEEP_BODYWEIGHT);
		userWife = new User("BlackWidow", "Scarlett", "Johansson", Double.valueOf(170), Double.valueOf(60), 32,
				Gender.FEMALE, Goal.LOSE_BODYWEIGHT);
		users = new ArrayList<User>();
	}

	@Test
	public void idealWeightMaleTest() {
		assertEquals(72, Calculation.getIdealWeight(userHusband), 0.01);
	}

	@Test
	public void idealWeightFemaleTest() {
		assertEquals(63, Calculation.getIdealWeight(userWife), 0.01);
	}

	@Test
	public void idealWeightStudentTest() {
		user.setGender(Gender.MALE);
		user.setAge(21);
		user.setBodyHeight(Double.valueOf(165));
		assertEquals(58.5, Calculation.getIdealWeight(user), 0.01);
	}

	@Test
	public void BMIMaleTest() {
		assertEquals(24.7, Calculation.getBMI(userHusband), 0.01);
	}

	@Test
	public void BMIFemaleTest() {
		assertEquals(20.76, Calculation.getBMI(userWife), 0.01);
	}

	@Test
	public void BMRMaleTest() {
		assertEquals(1730, Calculation.getBMR(userHusband, userHusband.getBodyWeight(), userHusband.getBodyHeight(),
				userHusband.getAge()), 0.01);
	}

	@Test
	public void BMRFemaleTest() {
		assertEquals(1341.5, Calculation.getBMR(userWife, 60, 170, 32), 0.01);
	}

	@Test
	public void fatPercentageMaleTest() {
		assertEquals(22.629, Calculation.getFatPercentage(userHusband), 0.01);
	}

	@Test
	public void fatPercentageFemaleTest() {
		assertEquals(26.872, Calculation.getFatPercentage(userWife), 0.01);
	}

	@Test
	public void keepWeightMaleTest() {
		assertEquals(2768, Calculation.getKcalPerDayToKeepBodyWeight(userHusband, userHusband.getBodyWeight(),
				userHusband.getBodyHeight(), userHusband.getAge()), 0.01);
	}

	@Test
	public void loseWeightMaleTest() {
		assertEquals(1903, Calculation.getKcalPerDayToLoseBodyWeight(userHusband, userHusband.getBodyWeight(),
				userHusband.getBodyHeight(), userHusband.getAge()), 0.01);
	}

	@Test
	public void loseWeightFemaleTest() {
		assertEquals(1475.65, Calculation.getKcalPerDayToLoseBodyWeight(userWife, userWife.getBodyWeight(),
				userWife.getBodyHeight(), userWife.getAge()), 0.01);
	}

	@Test
	public void keepWeightFemaleTest() {
		assertEquals(2146.4, Calculation.getKcalPerDayToKeepBodyWeight(userWife, userWife.getBodyWeight(),
				userWife.getBodyHeight(), userWife.getAge()), 0.01);
	}

	@Test
	public void DailyNutrientsFemaleLoseWeightLessThan30PercentFatTest() {
		Calculation.setDailyNutrients(userWife);
		assertEquals(205.15, userWife.getCarbohydrate(), 0.01);
		assertEquals(100.78, userWife.getProtein(), 0.01);
		assertEquals(23.80, userWife.getFat(), 0.01);
	}

	@Test
	public void DailyNutrientsFemaleKeepWeightLessThan30PercentFatTest() {
		user = userWife;
		user.setGoal(Goal.KEEP_BODYWEIGHT);
		Calculation.setDailyNutrients(userWife);
		assertEquals(298.40, userWife.getCarbohydrate(), 0.01);
		assertEquals(146.58, userWife.getProtein(), 0.01);
		assertEquals(34.62, userWife.getFat(), 0.01);
	}

	@Test
	public void DailyNutrientsFemaleLoseWeightMoreThan30PercentFatTest() {
		user = userWife;
		user.setBodyWeight(Double.valueOf(90));
		Calculation.setDailyNutrients(userWife);
		assertEquals(209.74, userWife.getCarbohydrate(), 0.01);
		assertEquals(103.03, userWife.getProtein(), 0.01);
		assertEquals(24.33, userWife.getFat(), 0.01);
	}

	@Test
	public void DailyNutrientsFemaleKeepWeightMoreThan30PercentFatTest() {
		user = userWife;
		user.setGoal(Goal.KEEP_BODYWEIGHT);
		user.setBodyWeight(Double.valueOf(90));
		Calculation.setDailyNutrients(userWife);
		assertEquals(305.08, userWife.getCarbohydrate(), 0.01);
		assertEquals(149.86, userWife.getProtein(), 0.01);
		assertEquals(35.39, userWife.getFat(), 0.01);
	}

	@Test
	public void refreshIntakeBMRTest() {
		user = userHusband;
		Calculation.refreshIntakeBMR(user);
		assertEquals(0, user.getIntakeBMR(), 0.01);
	}

	@Test
	public void setIntakeCarbohydrateTest() {
		user = userHusband;
		Calculation.increaseIntakeCarbohydrate(11.0, user);
		assertEquals(11.0, user.getIntakeCarbohydrate(), 0.01);
	}

	@Test
	public void setIntakeProteinTest() {
		user = userHusband;
		Calculation.increaseIntakeProtein(7.0, user);
		assertEquals(7.0, user.getIntakeProtein(), 0.01);
	}

	@Test
	public void setIntakeFatTest() {
		user = userHusband;
		Calculation.increaseIntakeFat(20.0, user);
		assertEquals(20.0, user.getIntakeFat(), 0.01);
	}

	@Test
	public void setIntakeCarbohydrateNotNullTest() {
		user = userHusband;
		user.setIntakeCarbohydrate(20.0);
		Calculation.increaseIntakeCarbohydrate(12.0, user);
		assertEquals(32.0, user.getIntakeCarbohydrate(), 0.01);
	}

	@Test
	public void setIntakeProteinNotNullTest() {
		user = userHusband;
		user.setIntakeProtein(9.0);
		Calculation.increaseIntakeProtein(12.0, user);
		assertEquals(21.0, user.getIntakeProtein(), 0.01);
	}

	@Test
	public void setIntakeFatNotNullTest() {
		user = userHusband;
		user.setIntakeFat(11.0);
		Calculation.increaseIntakeFat(12.0, user);
		assertEquals(23.0, user.getIntakeFat(), 0.01);
	}

	@Test
	public void withdrawCarbohydrateTest() {
		user = userHusband;
		user.getIntakes().add(new Intake(Nutrients.CARBOHYDRATE, 34.0));
		user.setIntakeCarbohydrate(34.0);
		Calculation.withdraw(user);
		assertEquals(0.0, user.getIntakeCarbohydrate(), 0.01);
	}

	@Test
	public void withdrawProteinTest() {
		user = userHusband;
		user.getIntakes().add(new Intake(Nutrients.PROTEIN, 20.0));
		user.setIntakeProtein(20.0);
		Calculation.withdraw(user);
		assertEquals(0.0, user.getIntakeProtein(), 0.01);
	}

	@Test
	public void withdrawFatTest() {
		user = userHusband;
		user.getIntakes().add(new Intake(Nutrients.FAT, 8.0));
		user.setIntakeFat(8.0);
		Calculation.withdraw(user);
		assertEquals(0.0, user.getIntakeFat(), 0.01);
	}

	@Test
	public void withdrawNoIntakeTest() {
		user = userHusband;
		user.setIntakeCarbohydrate(30.0);
		Calculation.withdraw(user);
	}

	@Test
	public void fillSkippedDaysTest() {
		user = userHusband;
		user.getWeek().clear();
		Calculation.fillSkippedDays(user.getWeek());
		assertEquals(0, user.getWeek().size());
	}

	@Test
	public void fillSkippedDaysWeekSizeTest() {
		user = userWife;
		user.getWeek().clear();
		user.getWeek().add(new DailyIntakeOfNutrients(1.0, 1.0, 1.0, LocalDate.now().minusDays(9)));
		user.getWeek().add(new DailyIntakeOfNutrients(1.0, 1.0, 1.0, LocalDate.now().minusDays(8)));
		Calculation.fillSkippedDays(user.getWeek());
		assertEquals(7, user.getWeek().size());
	}

	@Test
	public void fillSkippedDaysDifferenceGreaterThanWeekSizeTest() {
		user = userWife;
		user.getWeek().clear();
		user.getWeek().add(new DailyIntakeOfNutrients(1.0, 1.0, 1.0, LocalDate.now().plusDays(1)));
		user.getWeek().add(new DailyIntakeOfNutrients(1.0, 1.0, 1.0, LocalDate.now().plusDays(2)));
		Calculation.fillSkippedDays(user.getWeek());
		assertEquals(0, user.getWeek().size());
	}

	@Test
	public void fillSkippedDaysDifferenceEqualsZeroTest() {
		user = userWife;
		user.getWeek().clear();
		user.getWeek().add(new DailyIntakeOfNutrients(1.0, 1.0, 1.0, LocalDate.now()));
		Calculation.fillSkippedDays(user.getWeek());
		assertEquals(1, user.getWeek().size());
	}

	@Test
	public void fillSkippedDaysSizeTest() {
		user = userWife;
		user.getWeek().clear();
		user.getWeek().add(new DailyIntakeOfNutrients(1.0, 1.0, 1.0, LocalDate.now().minusDays(6)));
		user.getWeek().add(new DailyIntakeOfNutrients(1.0, 1.0, 1.0, LocalDate.now().minusDays(5)));
		Calculation.fillSkippedDays(user.getWeek());
		assertEquals(6, user.getWeek().size());
	}

	@Test
	public void clearWithdrawsTest() {
		users.clear();
		userWife.getIntakes().add(new Intake(Nutrients.CARBOHYDRATE, 11.0));
		users.add(userWife);
		Calculation.clearWithdraws(users);
		for (User user : users) {
			assertEquals(0, user.getIntakes().size());
		}
	}

	@Test
	public void resetIntakeNutrientsDayTest() {
		users.clear();
		user.setDay(LocalDate.of(2017, 04, 26));
		users.add(user);
		Calculation.resetIntakeNutrients(users);
		assertEquals(LocalDate.of(2017, 04, 26), user.getWeek().get(0).getDate());
	}

	@Test
	public void resetIntakeNutrientsWeekTest() {
		users.clear();
		for (int i = 0; i < 7; i++) {
			user.getWeek().add(new DailyIntakeOfNutrients());
		}
		user.setDay(LocalDate.now().minusDays(2));
		users.add(user);
		Calculation.resetIntakeNutrients(users);
		assertEquals(7, user.getWeek().size());
	}

	@Test
	public void resetIntakeNutrientsTodayTest() {
		users.clear();
		userHusband.setDay(LocalDate.now().minusDays(1));
		users.add(userHusband);
		Calculation.resetIntakeNutrients(users);
		assertEquals(LocalDate.now(), userHusband.getDay());
		assertEquals(0.0, userHusband.getIntakeCarbohydrate(), 0.01);
		assertEquals(0.0, userHusband.getIntakeProtein(), 0.01);
		assertEquals(0.0, userHusband.getIntakeFat(), 0.01);
		assertEquals(0.0, userHusband.getIntakeBMR(), 0.01);
	}

	@Test
	public void resetIntakeNutrientsBeforeTest() {
		users.clear();
		userHusband.setDay(LocalDate.now().plusDays(9));
		users.add(userHusband);
		Calculation.resetIntakeNutrients(users);
		assertEquals(LocalDate.now(), userHusband.getDay());
		assertEquals(0.0, userHusband.getIntakeCarbohydrate(), 0.01);
		assertEquals(0.0, userHusband.getIntakeProtein(), 0.01);
		assertEquals(0.0, userHusband.getIntakeFat(), 0.01);
		assertEquals(0.0, userHusband.getIntakeBMR(), 0.01);
	}

	@Test
	public void resetIntakeNutrientsNowTest() {
		users.clear();
		userHusband.setDay(LocalDate.now());
		userHusband.setIntakeCarbohydrate(1.0);
		userHusband.setIntakeFat(1.0);
		userHusband.setIntakeProtein(1.0);
		Calculation.refreshIntakeBMR(userHusband);
		users.add(userHusband);
		Calculation.resetIntakeNutrients(users);
		assertEquals(LocalDate.now(), userHusband.getDay());
		assertEquals(1.0, userHusband.getIntakeCarbohydrate(), 0.01);
		assertEquals(1.0, userHusband.getIntakeProtein(), 0.01);
		assertEquals(1.0, userHusband.getIntakeFat(), 0.01);
		assertEquals(3.0, userHusband.getIntakeBMR(), 0.01);
	}

	@Test
	public void resetIntakeNutrientsAfterTest() {
		users.clear();
		userHusband.setDay(LocalDate.now().minusDays(9));
		users.add(userHusband);
		Calculation.resetIntakeNutrients(users);
		assertEquals(LocalDate.now(), userHusband.getDay());
		assertEquals(0.0, userHusband.getIntakeCarbohydrate(), 0.01);
		assertEquals(0.0, userHusband.getIntakeProtein(), 0.01);
		assertEquals(0.0, userHusband.getIntakeFat(), 0.01);
		assertEquals(0.0, userHusband.getIntakeBMR(), 0.01);
	}

	@Rule
	public TemporaryFolder tmp = new TemporaryFolder();

	public UserDAOImpl dao = new UserDAOImpl();

}
