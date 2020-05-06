package storage;

import model.Category;
import model.Item;
import model.User;
import util.FileUtil;

import java.io.EOFException;
import java.io.IOException;
import java.util.*;

import static java.util.Comparator.comparing;

public class DataStorage {


    private static long itemId = 0;
    private Map<String, User> userMap = new HashMap<>();
    private List<Item> items = new ArrayList<>();

    public void add(User user) throws IOException {
        userMap.put(user.getPhoneNumber(), user);
        FileUtil.serializeUserMap(userMap);


    }

    public void add(Item item) throws IOException {
        item.setId(itemId++);
        items.add(item);
        FileUtil.serializeItemList(items);

    }

    public User getUser(String phoneNumber) {
        return userMap.get(phoneNumber);
    }

    public Item getItemById(long id) {
        for (Item item : items) {
            if (item.getId() == id) {
                return item;
            }

        }
        return null;
    }

    public void printItems() {
        for (Item item : items) {
            System.out.println(item);
        }
    }

    public void printItemsOrderByTitle() {
        List<Item> orderedList = new ArrayList<>(items);
        Collections.sort(orderedList);
        for (Item item : orderedList) {
            System.out.println(item);
        }
    }

    public void printItemsOrderByDate() {
        List<Item> orderedList = new ArrayList<>(items);
        orderedList.sort(comparing(Item::getCreatedDate));
        for (Item item : orderedList) {
            System.out.println(item);
        }
    }

    public void printItemsByUser(User user) {
        for (Item item : items) {
            if (item.getUser().equals(user)) {
                System.out.println(item);
            }

        }
    }

    public void printItemsByCategory(Category category) {
        for (Item item : items) {
            if (item.getCategory() == category) {
                System.out.println(item);
            }

        }
    }

    public void deleteItemsByUser(User user) throws IOException, ClassNotFoundException {

        items.removeIf(next -> next.getUser().equals(user));
        FileUtil.deserializeUserMap();

    }

    public void deleteItemsById(long id) throws IOException, ClassNotFoundException {
        try {
            items.remove(getItemById(id));
            FileUtil.deserializeItemList();
        } catch (EOFException e) {
            e.printStackTrace();

        }

    }

    public void initData() throws IOException, ClassNotFoundException {
        try {
            userMap = FileUtil.deserializeUserMap();
            items = FileUtil.deserializeItemList();
            itemId++;
        } catch (EOFException e) {
            System.out.println("The file is empty");
        }


    }
}
