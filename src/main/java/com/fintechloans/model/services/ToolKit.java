package com.fintechloans.model.services;

import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;

import com.fintechloans.exceptions.*;
import com.fintechloans.model.product.Product;
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
    private String merchantsPath;
    private Gson jsonMapper;
    private FileReader regularUsersReader;
    private FileReader casinoUsersReader;
    private FileReader merchantsReader;
    private FileWriter writer;

    public ToolKit() {
        //Tener en cuenta que según el dispositivo puede variar que para importar las rutas correctamente
        //se deba usar com. o com/, en caso de que no le funcione cambie la ruta a la que le funcione
        regularUsersPath = "src/main/java/com/fintechloans/data/regularUsers.json";
        casinoUsersPath = "src/main/java/com/fintechloans/data/casinoUsers.json";
        merchantsPath = "src/main/java/com/fintechloans/data/merchants.json";
        jsonMapper = new GsonBuilder().registerTypeAdapter(LocalDate.class, new GsonLocalDateAdapter())
                .serializeSpecialFloatingPointValues()
                .create();
    }

    public void merchantManagment(String merchantName, User user) throws Exception, MerchantNotAllied {
        merchantsReader = new FileReader(merchantsPath);
        ArrayList<Merchant> merchantJsonArray = jsonMapper.fromJson(merchantsReader,
                new TypeToken<ArrayList<Merchant>>() {
                }.getType());
        for (Merchant merchant : merchantJsonArray) {
            if (!merchant.getMerchantName().equals(merchantName)) {
                throw new MerchantNotAllied();
            } else {
                // Implementar metodo de compra
            }
        }
    }

    /**
     * Metodo que permite crear un usuario regular y guardarlo en el archivo JSON,
     * esto se realiza obteniendo la lista guardad dentro del json, agregand el
     * nuevo usuario y sobreescribiendo el archivo.
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
            int contractType,
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
     * Metodo que permite crear un usuario de casino y guardarlo en el archivo
     * JSON, esto se realiza obteniendo la lista guardad dentro del json,
     * agregando el nuevo usuario y sobreescribiendo el archivo.
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
            int contractType,
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
     * Metodo que permite loguear un usuario, esto se logra obteniendo la lista de
     * usuarios correspondiente al tipo de usuario que se desea loguear, luego se
     * filtra la lista para obtener el usuario que coincida con el email y la
     * contraseña ingresada, finalmente se retorna dicho usuario para que se pueda
     * realizar las tareas en el presentador.
     * 
     * @param email
     * @param password
     * @param userType
     * @return User
     * @throws UserNotFoundException
     * @trhows Exception
     */
    public User logUser(String email, String password, int userType) throws UserNotFoundException, Exception {
        ArrayList<User> jsonAccounts;
        User loggedUser;
        switch (userType) {
            case 1:
                regularUsersReader = new FileReader(regularUsersPath);
                jsonAccounts = jsonMapper.fromJson(regularUsersReader, new TypeToken<ArrayList<RegularCustomer>>() {
                }.getType());
                break;
            case 2:
                casinoUsersReader = new FileReader(casinoUsersPath);
                jsonAccounts = jsonMapper.fromJson(casinoUsersReader, new TypeToken<ArrayList<CasinoCustomer>>() {
                }.getType());
                break;
            default:
                throw new UserNotFoundException();
        }
        loggedUser = jsonAccounts.stream().filter((account) -> {
            return account.getEmail().equals(email) && account.getPassword().equals(password);
        }).findFirst().get();
        return loggedUser;
    }

    /**
     * Metodo para actualizar la información del usuario en el Json, este metodo
     * obtiene la lista de usuarios correspondiente al tipo de usuario especificado,
     * luego filtra el usuario en cuestión, lo elimina y agrega la versión
     * actualizada de ese mismo usuario.
     * 
     * @param userToUpdate
     * @return void
     * @throws UserNotFoundException
     * @throws Exception
     */
    public void updateJsonInfo(User userToUpdate) {
        ArrayList<User> jsonAccounts;
        try {
            switch (userToUpdate.getUserType()) {
                case 1:
                    System.out.println("Regular");
                    regularUsersReader = new FileReader(regularUsersPath);
                    jsonAccounts = jsonMapper.fromJson(regularUsersReader, new TypeToken<ArrayList<RegularCustomer>>() {
                    }.getType());
                    break;
                case 2:
                    System.out.println("Casino");
                    casinoUsersReader = new FileReader(casinoUsersPath);
                    jsonAccounts = jsonMapper.fromJson(casinoUsersReader, new TypeToken<ArrayList<CasinoCustomer>>() {
                    }.getType());
                    break;
                default:
                    throw new UserNotFoundException();
            }
            jsonAccounts.removeIf((account) -> {
                return account.getEmail().equals(userToUpdate.getEmail());
            });
            jsonAccounts.add(userToUpdate);
            if (userToUpdate.getUserType() == 1) {
                writer = new FileWriter(regularUsersPath, false);
            } else {
                writer = new FileWriter(casinoUsersPath, false);
            }
            writer.write(jsonMapper.toJson(jsonAccounts));
            writer.flush();
            writer.close();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Product getProductById(int id, User user) {
        Product product = null;
        try {
            product = user.getProducts().stream().filter((productToFind) -> {
                return productToFind.getId() == id;
            }).findFirst().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }
}
