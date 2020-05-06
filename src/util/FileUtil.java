package util;


import model.Item;
import model.User;

import java.io.*;
import java.util.List;
import java.util.Map;

public class FileUtil {
    private static final String FILE_PATH_USER = "D:\\java\\io\\user.txt";
    private static final String FILE_PATH_ITEM = "D:\\java\\io\\item.txt";


    public static void serializeUserMap(Map<String, User> userMap) throws IOException {

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH_USER));
        objectOutputStream.writeObject(userMap);
        objectOutputStream.close();

    }

    public static Map<String, User> deserializeUserMap() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH_USER));
        Object deserialization = objectInputStream.readObject();
        Map<String, User> user = (Map<String, User>) deserialization;

        objectInputStream.close();
        return user;

    }

    public static void serializeItemList(List<Item> items) throws IOException {

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH_ITEM));
        objectOutputStream.writeObject(items);
        objectOutputStream.close();

    }

    public static List<Item> deserializeItemList() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH_ITEM));
        Object deserialization = objectInputStream.readObject();
        List<Item> item = (List<Item>) deserialization;

        objectInputStream.close();
        return item;

    }


}
