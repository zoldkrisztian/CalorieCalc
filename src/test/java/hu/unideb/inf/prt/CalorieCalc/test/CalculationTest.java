package hu.unideb.inf.prt.CalorieCalc.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hu.unideb.inf.prt.CalorieCalc.calculation.Calculation;
import hu.unideb.inf.prt.CalorieCalc.model.Gender;
import hu.unideb.inf.prt.CalorieCalc.model.Goal;
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
	public void BMRMaleTest() {
		assertEquals(1730, Calculation.getBMR(userHusband, 80, 180, 40), 0.01);
	}

	@Test
	public void BMRFemaleTest() {
		assertEquals(1341.5, Calculation.getBMR(userWife, 60, 170, 32), 0.01);
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
	public void fatPercentageFemaleTest() {
		assertEquals(20.76, Calculation.getBMI(userWife), 0.01);
	}

}
