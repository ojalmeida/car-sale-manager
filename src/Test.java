import entities.User;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import services.DataStorageService;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {

    public static void main(String[] args) throws IOException {

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        System.out.println(simpleDateFormat.format(date));
    }

    static String PATH = "C:\\Users\\Otavi\\Desktop\\CSM";


    //methods being tested:

    public static String encryptData(String data) {

        char[] decrypted_chars = data.toCharArray();
        StringBuilder encrypted_data = new StringBuilder();

        for (int i = 0; i < decrypted_chars.length; i++){
            char[] encrypted_chars = new char[decrypted_chars.length];
            encrypted_chars[i] = (char) (decrypted_chars[i] + 1);
            encrypted_data.append((encrypted_chars[i]));
        }

        return encrypted_data.toString();
    }
    public static String decryptData(String encryptedData) {
        char[] encrypted_chars = encryptedData.toCharArray();
        StringBuilder decrypted_data = new StringBuilder();

        for (int i = 0; i < encrypted_chars.length; i++){
            char[] decrypted_chars = new char[encrypted_chars.length];
            decrypted_chars[i] = (char) (encrypted_chars[i] - 1);
            decrypted_data.append((decrypted_chars[i]));
        }

        return decrypted_data.toString();
    }
    public static List<User> loadUsers() throws IOException {

        List<User> users = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(PATH));
        String username;
        String password;
        String phone;

        String line = reader.readLine();
        while (line != null) {

            String[] dividedLine = line.split("; ");

            username = decryptData(dividedLine[0]);
            password = decryptData(dividedLine[1]);
            phone = decryptData(dividedLine[2]);

            users.add(new User(username, password, phone));

            line = reader.readLine();
        }

        reader.close();
        return users;
    }
    public static void addUser(User user) throws IOException {


        File file = new File((PATH.substring(0, 18)));
        if(!file.exists()){
            file.mkdirs();
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(PATH));

        String username = encryptData(user.getUsername());
        String passsword = encryptData(user.getPassword());
        String phone = encryptData(user.getPhone());

        writer.write(username);
        writer.write("; " + passsword);
        writer.write("; " + phone);
        writer.newLine();
        writer.close();

    }
    public static void playSong(String path){

        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
    private static void removeLine(String line, String path, String filename) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(path + "\\" + filename));
        BufferedWriter writer = new BufferedWriter(new FileWriter(path + "\\temp_" + filename ));

        File originalFile = new File(PATH + "\\" + filename);
        File tempFile = new File(PATH + "\\temp_" + filename);

        String currentLine = reader.readLine();
        while(currentLine!= null) {

            if (!currentLine.equals(line)) {
                writer.write(currentLine);
                writer.newLine();
            }

            currentLine = reader.readLine();

        }

        writer.close();
        reader.close();
        originalFile.delete();
        tempFile.renameTo(originalFile);



    }
}
