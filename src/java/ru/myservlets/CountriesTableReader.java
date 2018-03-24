/*
 * Use and copying for commercial purposes
 * only with the author's permission
 */
package ru.myservlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeMap;

/**
 *
 * @author kentyku
 */
public class CountriesTableReader {
    private Statement stmt;
    private Connection connection;
    private TreeMap <Integer, String> countriesList;
    private ResultSet rs;

    /**
     * Основной алгоритм работы нити
     */

    public void readData() {
        try {
            connect();
            readCountriesTable();
            disconnect();
        } catch (Exception e){
            System.out.println("Ошибка загрузки из таблицы \"Страны\"");
        }


    }

    /**
     * Метод, отвечающий за подключение к БД
     * @throws SQLException, ClassNotFoundException
     */

//    private void connect() throws SQLException, ClassNotFoundException {
//        Class.forName("org.sqlite.JDBC");
//        this.connection = DriverManager.getConnection("jdbc:sqlite:GEEKBRAINS_INTERNSHIP_15_1_DB.db");
//        this.stmt = connection.createStatement();
//    }
    private void connect() throws ClassNotFoundException {
        try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase?useSSL=no","root","123456");
                stmt = connection.createStatement();
            }
            catch (SQLException ex) {
                System.out.println("***************");
                System.out.println(ex);
                System.out.println("***************");
            }
    }

    /**
     * Метод, отвечающий за отключение от БД
     */

    private void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
        }
    }

    /**
     * Метод, осуществляющий запрос из БД ключевых слов по которым ведется
     * парсинг html страниц
     * @throws Exception
     */

    public void readCountriesTable() throws SQLException {
        this.countriesList = new TreeMap<>();
        this.rs = this.stmt.executeQuery("SELECT idcountry, country FROM COUNTRY;");
        while(this.rs.next()){
            this.countriesList.put(this.rs.getInt(1), this.rs.getString(2));//treemap: ключ=id страны, значение= название страны
        }
    }
    /*
    *Метод возвращающий коллекцию стран
    */
    public TreeMap <Integer, String> getCountriesList(){
        return this.countriesList;
    }
}
