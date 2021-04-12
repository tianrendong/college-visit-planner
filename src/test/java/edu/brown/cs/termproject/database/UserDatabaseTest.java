package edu.brown.cs.termproject.database;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.List;

public class UserDatabaseTest {

  private UserDataManager userDatabase;

  @Before
  public void setUp() {
    userDatabase = new UserDataManager();
    userDatabase.connect("./data/sampleUsers.sqlite3");
  }
  @Test
  public void testRegister() throws InvalidKeySpecException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
    userDatabase.signup("tom", "12345", "Tom", "Wang");
    userDatabase.login("tom", "1234");
    userDatabase.login("tom", "12345");

  }

  @Test
  public void test() throws InvalidKeySpecException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
//    userDatabase.getUserInfo("a");
//    userDatabase.deleteCollege("a", 1);
    List<Integer> nearbyColleges =
        new Gson().fromJson("[1, 2]", new TypeToken<List<Integer>>() {
        }.getType());
    System.out.println(nearbyColleges);
  }



}
