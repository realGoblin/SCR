/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scr;

import  java.util.ArrayList;
import  java.io.*;
/**
 *
 * @author Goblin
 */
public   class downloadAndSave {    
    ArrayList<logIn> manLog;
    ArrayList<lecturer> lect;
    ArrayList<ratings> rat;
    ArrayList<student> stud;    
    String  addresLogIn  =   "E:\\scrLogIn.ser";
    String  addresDataBaseLecturer  =   "E:\\scrLec.ser";
    String  addresDataBaseStudent  =   "E:\\scrStud.ser";
    String  addresDataBaseRat  =   "E:\\scrRat.ser";  
    public  static  ArrayList<lecturer>    downloadL(){
        downloadAndSave faf =   new downloadAndSave();
        return  faf.downloadLect();
    }
    public  static  ArrayList<student>    downloadS(){
        downloadAndSave faf =   new downloadAndSave();
        return  faf.downloadStud();
    }
    public  static  ArrayList<logIn>    downloadA(){
        downloadAndSave faf =   new downloadAndSave();
        return  faf.downloadAuten();        
    }
    public  static  ArrayList<ratings>    downloadR(){
        downloadAndSave faf =   new downloadAndSave();
        return  faf.downloadRat();
    }
    public  static  void    saveL(ArrayList<lecturer> lect){
        downloadAndSave faf =   new downloadAndSave();
        faf.saveLect(lect);
    }
    public  static  void    saveS(ArrayList<student> stud){
        downloadAndSave faf =   new downloadAndSave();
        faf.saveStud(stud);
    }
    public  static  void    saveR(ArrayList<ratings> rat){
        downloadAndSave faf =   new downloadAndSave();
        faf.saveRate(rat);
    }
    public  static  void    saveLogs(ArrayList<logIn>  manLog){
        downloadAndSave faf =   new downloadAndSave();
        faf.saveLog(manLog);
    }
    public  ArrayList<lecturer>    downloadLect(){
        try{
        ObjectInputStream   iM  =   new ObjectInputStream(new   FileInputStream(addresDataBaseLecturer)); 
        lect    =   (ArrayList<lecturer>)   iM.readObject();  
        }catch(Exception ex){
        System.out.println(ex.getMessage());
        }
        return  lect;
    }
    public  ArrayList<student>    downloadStud(){
        try{ 
        ObjectInputStream   iM  =   new ObjectInputStream(new   FileInputStream(addresDataBaseStudent)); 
        stud    =   (ArrayList<student>)   iM.readObject();  
        }catch(IOException | ClassNotFoundException ex){
        System.out.println(ex.getMessage());
        }
        return  stud;
    }
    public  ArrayList<logIn>    downloadAuten(){
        try{
        ObjectInputStream   is  =   new ObjectInputStream(new   FileInputStream(addresLogIn)); 
        manLog    =   (ArrayList<logIn>)   is.readObject();           
        }catch(Exception ex){
        SCR.first();
        System.out.println("перезапусти программу");
        System.out.println(ex.getMessage());
        }
        return manLog;
    }
    public  ArrayList<ratings>    downloadRat(){
        try{
        ObjectInputStream   iR  =   new ObjectInputStream(new   FileInputStream(addresDataBaseRat)); 
        rat    =   (ArrayList<ratings>)   iR.readObject();  
        }catch(Exception ex){
        System.out.println(ex.getMessage());
        }
        return  rat;
    }
    public  void    saveLect(ArrayList<lecturer> lect){
        try{
            ObjectOutputStream  o = new ObjectOutputStream(new  FileOutputStream(addresDataBaseLecturer));
            o.writeObject(lect);
            o.close();
        }catch(Exception ex1){
            System.out.println(ex1.getMessage());
        }
    }
    public  void    saveStud(ArrayList<student> stud){
        try{
            ObjectOutputStream  o = new ObjectOutputStream(new  FileOutputStream(addresDataBaseStudent));
            o.writeObject(stud);
            o.close();
        }catch(Exception ex1){
            System.out.println(ex1.getMessage());
        }
    }
    public  void    saveRate(ArrayList<ratings> rat){
        try{
            ObjectOutputStream  o = new ObjectOutputStream(new  FileOutputStream(addresDataBaseRat));
            o.writeObject(rat);
            o.close();
        }catch(Exception ex1){
            System.out.println(ex1.getMessage());
        }
    }    
    public  void    saveLog(ArrayList<logIn>  manLog){
        try{
            ObjectOutputStream  oL = new ObjectOutputStream(new  FileOutputStream(addresLogIn));
            oL.writeObject(manLog);
            oL.close();
            }catch(Exception ex1){
                System.out.println(ex1.getMessage());
            }
    }
    
}
