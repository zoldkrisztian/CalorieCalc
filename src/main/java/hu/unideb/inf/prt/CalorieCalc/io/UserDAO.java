package hu.unideb.inf.prt.CalorieCalc.io;

import java.nio.file.Path;
import java.util.List;

import hu.unideb.inf.prt.CalorieCalc.model.User;


public interface UserDAO {
	
	public void loadUsers(Path path);
	
	public void saveUsers(List<User> users, Path path);

}
