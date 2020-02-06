/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conndb;

import java.util.ArrayList;

/**
 *
 * @author javcoder
 */
public class ConnDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String query = "INSERT INTO `account`(`email`, `password`, `stuId`) VALUES (?,?,?)";
        String query1 = "SELECT * FROM account ";
//        ArrayList<Object> list = new ArrayList<>() ;
//        list.add("01065418129") ;
//        list.add("level3") ;
//        list.add(1234) ;
        DatabaseConnection data = DatabaseConnection.getInstance();
//        int count = data.insertIntoDB(query , list) ;
//        if (count != 0){
//            System.out.println("sucessfully added");
//        }else {
//            System.out.println("there is error in adding process");
//        }

        ArrayList<Object> datalist = data.RetrieveDataFromDB(query1);
        for (Object datalist1 : datalist) {
            System.out.println(datalist1.toString());
        }
        String upquery = "UPDATE account SET email=?,password=? WHERE stuId =" + 123+ " " ;
        ArrayList<Object> detoListo =  new ArrayList<>() ;
        detoListo.add("Ahmedtalal@gmail.com") ;
        detoListo.add("123456") ;
        int cou = data.UpdateDataIntoDB(upquery, detoListo) ;
        if (cou != 0) {
            System.out.println("successfully updated");
        }else {
            System.out.println("there is error in update process");
        }
        
        ArrayList<Object> datalis11 = data.RetrieveDataFromDB(query1);
        for (Object datalist1 : datalis11) {
            System.out.println(datalist1.toString());
        }
        
        String delQuery = "DELETE FROM account WHERE stuId =" + 123+ "" ;
        int coun = data.deleteDataFromDB(delQuery) ;
        if (coun != 0) {
            System.out.println("successfully deleted");
        }else {
            System.out.println("there is error in delete process");
        }
        
        ArrayList<Object> datalis111 = data.RetrieveDataFromDB(query1);
        for (Object datalist1 : datalis111) {
            System.out.println(datalist1.toString());
        }
    }
}
