package com.fintechloans.model.services;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import com.fintechloans.exceptions.UserNotFoundException;
import com.fintechloans.model.product.VirtualCard;
import com.fintechloans.model.user.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

/**
 * Clase que contiene los metodos necesarios para la creacion de usuarios,
 * calculo de riesgo y generacion de codigos de tarjetas virtuales.
 * 
 * @author Vicente Matallana, Gabriel Cely, Sebastian Cañon
 * @version 1.0.1
 */
public class ToolKit {

    /**
     * Atributos de la clase ToolKit
     * 
     * @param regularUsersPath   Ruta del archivo JSON que contiene los usuarios
     * @param casinoUsersPath    Ruta del archivo JSON que contiene los usuarios
     * @param jsonMapper         Objeto Gson que permite mapear los objetos a JSON
     * @param regularUsersReader Objeto FileReader que permite leer el archivo JSON
     * @param casinoUsersReader  Objeto FileReader que permite leer el archivo JSON
     * @param writer             Objeto FileWriter que permite escribir en el
     *                           archivo
     */

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
        double pesoIngreso = 0.3;
        double pesoDeudas = 0.2;
        double pesoHistorial = 0.3;
        double pesoProductosActuales = 0.2;
        int puntajeIncial = user.getScore();
        puntajeIncial += (int) (user.getIncome() * pesoIngreso);
        puntajeIncial += (int) (user.getDebts() * pesoDeudas);

        return user.getScore();
    }

    public int generateCode(VirtualCard card) {
        return 0;
    }

    /**
     * Metodo que permite crear un usuario regular y guardarlo en el archivo JSON
     * 
     * @param name
     * @param email
     * @param password
     * @param age
     * @param income
     * @param contractType
     * @param debts
     * @return void
     * @throws Exception
     * 
     */
    public void createRegularUSer(String name, String email, String password, int age, int income, int spendAmount,
            String contractType,
            int debts)
            throws Exception {
        User newUser = new RegularCustomer(name, email, password, age, income, spendAmount, contractType,
                debts);
        regularUsersReader = new FileReader(regularUsersPath);
        JsonArray array = jsonMapper.fromJson(regularUsersReader, JsonArray.class);
        array.add(jsonMapper.toJsonTree(newUser));
        writer = new FileWriter(regularUsersPath, false);
        writer.write(jsonMapper.toJson(array));
        writer.flush();
        writer.close();
    }

    /**
     * Metodo que permite crear un usuario de casino y guardarlo en el archivo JSON
     * 
     * @param name
     * @param email
     * @param password
     * @param age
     * @param income
     * @param contractType
     * @param debts
     * @param gameStast
     * @return void
     * @throws Exception
     */
    public void createCasinoUser(String name, String email, String password, int age, int income, int spendAmount,
            String contractType,
            int debts,
            ArrayList<Integer> gameStast)
            throws Exception {
        User newUser = new CasinoCustomer(name, email, password, age, income, spendAmount, contractType, debts,
                gameStast);
        casinoUsersReader = new FileReader(casinoUsersPath);
        JsonArray array = jsonMapper.fromJson(casinoUsersReader, JsonArray.class);
        array.add(jsonMapper.toJsonTree(newUser));
        writer = new FileWriter(casinoUsersPath, false);
        writer.write(jsonMapper.toJson(array));
        writer.flush();
        writer.close();
    }

    /**
     * Metodo que permite loguear un usuario
     * 
     * @param email
     * @param password
     * @param userType
     * @return User
     * @throws Exception
     */
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
}
