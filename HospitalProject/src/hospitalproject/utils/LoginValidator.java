package hospitalproject.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginValidator {

    public static boolean isAuthenticated(String username, String password) throws Exception {

        try {
            /* Username Password Check */

            boolean isValidated = false;

            BufferedReader reader = new BufferedReader(new FileReader("src/hospitalproject/data/admins.txt"));

            String line;
            while ((line = reader.readLine()) != null) {

                String[] words = line.split("\\|");

                if (words[6].equals(username)) {

                    String decodedPassword = PasswordEncryptDecrypt.decrypt(words[7]);

                    if (decodedPassword.equals(password)) {

                        isValidated = true;
                        LoggedInUser.setLoggedName(words[1]);
                    }

                }

            }

            reader.close();

            return isValidated;

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return false;
    }

}
