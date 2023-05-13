package com.fintechloans.model.services;

import java.io.FileReader;
import java.io.FileWriter;

import com.fintechloans.model.product.VirtualCard;
import com.fintechloans.model.user.*;
import com.google.gson.*;

public class ToolKit {

    private String path;
    private Gson jsonMapper;
    private FileReader reader;
    private FileWriter writer;

    public ToolKit() throws Exception {
        path = "src/main/java/com/fintechloans/data/users.json";
        reader = new FileReader(path);
        jsonMapper = new Gson();
    }

    public int calculateRisk(User user) {
        return 0;
    }

    public int generateCode(VirtualCard card) {
        return 0;
    }

    public void createRegularUSer(String name, String email, int age, int income, String contractType, int debts)
            throws Exception {
        User newUser = new RegularCustomer(name, email, age, income, contractType,
                debts);
        JsonArray array = jsonMapper.fromJson(reader, JsonArray.class);
        array.add(jsonMapper.toJsonTree(newUser));
        writer = new FileWriter(path, false);
        writer.write(jsonMapper.toJson(array));
        writer.flush();
        writer.close();
    }

    public boolean createCasinoUser() {
        return true;
    }

    // añadir implementaciones para inicio de sesión
    public void logUser() {

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
