package com.fintechloans.model.product;

import java.time.LocalDate;
import java.time.*;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

/**
 * Clase VirtualCard, esta clase hereda de la clase Product y se encarga de la
 * creación de tarjetas virtuales (el código, el CVV y la fecha de expiración)
 * 
 * @author Vicente Matallana, Gabriel Cely, Sebastian Cañon
 * @version 1.0
 */

public class VirtualCard extends Product {

    /*
     * Atributos de la clase VirtualCard
     * 
     * @param code Código de la tarjeta virtual
     * @param cardHolder Nombre del titular de la tarjeta
     * @param cvv Código de seguridad de la tarjeta
     * @param codes ArrayList que contiene los códigos de las tarjetas virtuales
     * @param expirationDate Fecha de expiración de la tarjeta
     * @param formatter Objeto DateTimeFormatter que permite dar formato a la fecha
     * de expiración
     */
    private long code;
    private String cardHolder;
    private int cvv;
    private ArrayList<String> codes = new ArrayList<>();
    LocalDate expirationDate;
    DateTimeFormatter formatter;
    

    public VirtualCard(double loanAmount,  int termInMonths, LocalDate dueDate) {
        super(loanAmount, termInMonths, dueDate);
    }

    /** 
     * Clase que permite generar un código de 16 dígitos aleatorios
     * @return String
     */
    public String generateCode() {
        long min = 1000000000000000L;
        long max = 9999999999999999L;
        code = (long) (Math.random() * (max - min + 1) + min);
        setCode(code);
        return "Su código es: " + code;
    }

    
    /** 
     * Clase que genera múltiples códigos de tarjetas virtuales
     * @param count
     * @return ArrayList<String>
     */
    public ArrayList<String> generateCreditCardNumbers(int count) {
        for (int i = 0; i < count; i++) {
            String creditCardNumber = generateCode();
            codes.add(creditCardNumber);
        }
        return codes;
    }

    /** 
     * Clase que compara las tarjetas virtuales para verificar que no se repitan
     */
    public void checkDuplicateCreditCardNumbers() {
        for (int i = 0; i < codes.size() - 1; i++) {
            String currentCode = codes.get(i);
            for (int j = i + 1; j < codes.size(); j++) {
                String otherCode = codes.get(j);
                if (currentCode.equals(otherCode)) {
                    throw new IllegalArgumentException("Se encontró un número duplicado");
                }
            }
        }
    }

    /** 
     * Clase que genera un código de seguridad de 3 dígitos aleatorios
     */
    public void generateCvv() {
        int min = 999;
        int max = 100;
        cvv = (int) (Math.random() * (max - min + 1) + min);
        setCvv(cvv);
    }

    /** 
     * Clase que genera la fecha de expiración de la tarjeta virtual
     */
    public void generateExpirationDate() {
        expirationDate = LocalDate.now().plusYears(6);
        formatter = DateTimeFormatter.ofPattern("MM/YY");
        setExpirationDate(expirationDate);
    }

    /**
     * Getters y Setters
     */

    public String getVirtualCardInfo(String cardHolder) {
        return "!Hola " + this.cardHolder + " se ha creado su tarjeta virtual! \n Su código es: " + this.getCode() + " \n Su CVV es: " + this.getCvv() + " \n La fecha de expiración es: " + this.getExpirationDate().format(formatter);
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public long getCode() {
        return code;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

}