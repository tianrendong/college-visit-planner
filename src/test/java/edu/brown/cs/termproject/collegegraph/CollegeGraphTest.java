package edu.brown.cs.termproject.collegegraph;

import com.google.maps.errors.ApiException;
import edu.brown.cs.termproject.database.UserDataManager;
import edu.brown.cs.termproject.main.Encryption;
import org.eclipse.jetty.http.PathMap;
import org.junit.Before;
import org.junit.Test;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CollegeGraphTest {

  List<College> _colleges;

  @Before
  public void setUp() {
    College c1 = new College(1, "Massachusetts Institute of Technology", 42.360001, -71.092003);
    College c2 = new College(2, "Stanford University", 37.428230, -122.168861);
    College c3 = new College(3, "Harvard University", 42.374443, -71.116943);
    College c4 = new College(4, "California Institute of Technology", 34.138000, -118.125000);
    _colleges = new ArrayList<>(Arrays.asList(c1, c2, c3, c4));
  }

  @Test
  public void testGraph() throws InterruptedException, ApiException, IOException {
    CollegeGraph graph = new CollegeGraph(_colleges);
    System.out.println(graph.toString());
  }

}
