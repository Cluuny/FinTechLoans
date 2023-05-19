package com.fintechloans.model.services;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.ArrayList;

import com.fintechloans.exceptions.UserNotFoundException;
import com.fintechloans.model.product.VirtualCard;
import com.fintechloans.model.user.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class ToolKit {

    private String regularUsersPath;
    private String casinoUsersPath;
    private Gson jsonMapper;
    private FileReader regularUsersReader;
    private FileReader casinoUsersReader;
    private FileWriter writer;

    public ToolKit() throws Exception {
        regularUsersPath = "src/main/java/com/fintechloans/data/regularUsers.json";
        casinoUsersPath = "src/main/java/com/fintechloans/data/casinoUsers.json";
        jsonMapper = new Gson();
    }

    public int calculateRisk(User user) {
        return 0;
    }

    public int generateCode(VirtualCard card) {
        return 0;
    }

    public void createRegularUSer(String name, String email, String password, int age, int income, String contractType,
            int debts)
            throws Exception {
        User newUser = new RegularCustomer(name, email, password, age, income, contractType,
                debts);
        regularUsersReader = new FileReader(regularUsersPath);
        JsonArray array = jsonMapper.fromJson(regularUsersReader, JsonArray.class);
        array.add(jsonMapper.toJsonTree(newUser));
        writer = new FileWriter(regularUsersPath, false);
        writer.write(jsonMapper.toJson(array));
        writer.flush();
        writer.close();
    }

    public void createCasinoUser(String name, String email, String password, int age, int income, String contractType,
            int debts,
            ArrayList<Integer> gameStast)
            throws Exception {
        User newUser = new CasinoCustomer(name, email, password, age, income, contractType, debts, gameStast);
        casinoUsersReader = new FileReader(casinoUsersPath);
        JsonArray array = jsonMapper.fromJson(casinoUsersReader, JsonArray.class);
        array.add(jsonMapper.toJsonTree(newUser));
        writer = new FileWriter(casinoUsersPath, false);
        writer.write(jsonMapper.toJson(array));
        writer.flush();
        writer.close();
    }

    // añadir implementaciones para inicio de sesión
    public User logUser(String email, String password, int userType) throws Exception {
        JsonArray jsonAccounts;
        ArrayList<User> arrAccounts;
        User loggedUser;
        switch (userType) {
            case 1:
                regularUsersReader = new FileReader(regularUsersPath);
                jsonAccounts = jsonMapper.fromJson(regularUsersReader, JsonArray.class).getAsJsonArray();
                arrAccounts = jsonMapper.fromJson(jsonAccounts, new TypeToken<ArrayList<RegularCustomer>>() {
                }.getType());
                break;
            case 2:
                casinoUsersReader = new FileReader(casinoUsersPath);
                jsonAccounts = jsonMapper.fromJson(casinoUsersReader, JsonArray.class).getAsJsonArray();
                arrAccounts = jsonMapper.fromJson(jsonAccounts, new TypeToken<ArrayList<CasinoCustomer>>() {
                }.getType());
                break;
            default:
                throw new UserNotFoundException();
        }
        loggedUser = arrAccounts.stream().filter((account) -> {
            return account.getEmail().equals(email) && account.getPassword().equals(password);
        }).findFirst().get();
        return loggedUser;
    }

    // Añadir retorno RegularLoan cuando se implemente
    public void createLoan(User user) {
    }

    // Añadir retorno VirtualCard cuando se implemente
    public void createVirtualCard(User user) {
    }

    // Añadir implementaciones cuando se decida el funcionamiento con base en el
    // Casino
    public void casinoManagment() {
    }
}
