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
    public  static  void    downloadL(){
        downloadAndSave faf =   new downloadAndSave();
        faf.downloadLect();
    }
    public  static  void    downloadS(){
        downloadAndSave faf =   new downloadAndSave();
        faf.downloadStud();
    }
    public  static  void    downloadA(){
        downloadAndSave faf =   new downloadAndSave();
        faf.downloadAuten();
    }
    public  static  void    downloadR(){
        downloadAndSave faf =   new downloadAndSave();
        faf.downloadRat();
    }
    public  static  void    saveL(){
        downloadAndSave faf =   new downloadAndSave();
        faf.saveLect();
    }
    public  static  void    saveS(){
        downloadAndSave faf =   new downloadAndSave();
        faf.saveStud();
    }
    public  static  void    saveR(){
        downloadAndSave faf =   new downloadAndSave();
        faf.saveRate();
    }
    public  static  void    saveLogs(){
        downloadAndSave faf =   new downloadAndSave();
        faf.saveLog();
    }
    public  void    downloadLect(){
        try{
        ObjectInputStream   iM  =   new ObjectInputStream(new   FileInputStream(addresDataBaseLecturer)); 
        lect    =   (ArrayList<lecturer>)   iM.readObject();  
        }catch(Exception ex){
        System.out.println(ex.getMessage());
        }
    }
    public  void    downloadStud(){
        try{ 
        ObjectInputStream   iM  =   new ObjectInputStream(new   FileInputStream(addresDataBaseStudent)); 
        stud    =   (ArrayList<student>)   iM.readObject();  
        }catch(Exception ex){
        System.out.println(ex.getMessage());
        }
    }
    public  void    downloadAuten(){
        try{
        ObjectInputStream   is  =   new ObjectInputStream(new   FileInputStream(addresLogIn)); 
        manLog    =   (ArrayList<logIn>)   is.readObject();           
        }catch(Exception ex){
        System.out.println(ex.getMessage());
        }
    }
    public  void    downloadRat(){
        try{
        ObjectInputStream   iR  =   new ObjectInputStream(new   FileInputStream(addresDataBaseRat)); 
        rat    =   (ArrayList<ratings>)   iR.readObject();  
        }catch(Exception ex){
        System.out.println(ex.getMessage());
        }
    }
    public  void    saveLect(){
        try{
            ObjectOutputStream  o = new ObjectOutputStream(new  FileOutputStream(addresDataBaseLecturer));
            o.writeObject(lect);
            o.close();
        }catch(Exception ex1){
            System.out.println(ex1.getMessage());
        }
    }
    public  void    saveStud(){
        try{
            ObjectOutputStream  o = new ObjectOutputStream(new  FileOutputStream(addresDataBaseStudent));
            o.writeObject(stud);
            o.close();
        }catch(Exception ex1){
            System.out.println(ex1.getMessage());
        }
    }
    public  void    saveRate(){
        try{
            ObjectOutputStream  o = new ObjectOutputStream(new  FileOutputStream(addresDataBaseRat));
            o.writeObject(rat);
            o.close();
        }catch(Exception ex1){
            System.out.println(ex1.getMessage());
        }
    }    
    public  void    saveLog(){
        try{
            ObjectOutputStream  oL = new ObjectOutputStream(new  FileOutputStream(addresLogIn));
            oL.writeObject(manLog);
            oL.close();
            }catch(Exception ex1){
                System.out.println(ex1.getMessage());
            }
    }
    
}
