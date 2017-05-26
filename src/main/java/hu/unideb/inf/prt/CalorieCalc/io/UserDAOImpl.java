package hu.unideb.inf.prt.CalorieCalc.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import hu.unideb.inf.prt.CalorieCalc.Main;
import hu.unideb.inf.prt.CalorieCalc.model.User;

public class UserDAOImpl implements UserDAO {

	@Override
	public void loadUsers(Path path) {
		Gson gson = new GsonBuilder().create();
		if(path == null)
			path = Paths.get(System.getProperty("user.home"), "save");
		if(path.toFile().exists()){
			File dir = path.toFile();
			for( File f : dir.listFiles()){
				try {
					if(f.toPath().toString().endsWith(".json")){
						User user = gson.fromJson(new FileReader(f.getPath()), User.class);
						if (user != null) {
							Main.getUsers().add(user);
							
						}
					}
				} catch (JsonSyntaxException| JsonIOException | FileNotFoundException e) {
					// TODO: handle exception
				}
			}
		}else {
			path.toFile().mkdir();
		}
	}

	@Override
	public void saveUsers(List<User> users, Path path) {
		Gson gson = new GsonBuilder().create();
		if(path == null)
			path = Paths.get(System.getProperty("user.home"), "save");
		
		for(User user : users){
			Path userPath = Paths.get(path.toString(), user.getUserName() + ".json");
			try {
				if(user.isDeletedUser()){
					userPath.toFile().delete();
				}else{
					FileWriter fileWriter = new FileWriter(userPath.toFile());
					gson.toJson(user, fileWriter);
					fileWriter.close();
				}
			} catch (JsonIOException| IOException e) {
				// TODO: handle exception
			}
		}

	}

}
