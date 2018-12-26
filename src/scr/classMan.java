/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scr;
import  java.io.*;
import java.util.ArrayList;
/**
 *
 * @author Goblin
 */
class    classMan   implements  Serializable{
    String  name;
    String  surname;
    String  patronymic;
    String  ID;
    String  position;
    String  password;
    
}
class   admin  extends classMan{
    admin(){
    name    =   "";
    surname =   "";
    patronymic  =   "";
    ID  =   "ADMIN";
    position    =   "ADMIN";
    password    =   "ADMIN";
    }
}
class   lecturer    extends classMan{
    ArrayList<String>    listGroups;
    ArrayList<String>    listDiscp;
    public  lecturer(){
        
    }
    public  lecturer    (String  myName,   String  mySurname,    String  myPatronymic,    
            String  IDi,    String  pass){
        name    =   myName;
        surname =   mySurname;
        patronymic  =   myPatronymic;
        ID  =   IDi;
        position    =   "lecturer";
        password    =   pass;
    }
}
class   student extends classMan{
    String   myGroup;
    //ArrayList<ratings>  rat;
    public  student(){
    }
    public  student(String  myName,   String  mySurname,    String  myPatronymic,    
            String  IDi,    String  pass,   String   groupMy){        
        name    =   myName;
        surname =   mySurname;
        patronymic  =   myPatronymic;
        ID  =   IDi;
        position    =   "Student";
        password    =   pass;
        myGroup =   groupMy;   
    }   
}
//class   group   implements  Serializable{
//String  name;
//ArrayList<lecturer> lect;
//ArrayList<student>   stud;
//}
class   ratings implements  Serializable{
    int   practic;
    int   lectures;
    int     gradPointAverage;
    String  stud;
    String  lect;
    String  group;
    String  discp;
    int w;
    public  ratings(){
        //deafult
    }
    public  ratings(String id,String IdLect,String   dis,String grup){        
        practic =   0;
        lectures    =   0;
        gradPointAverage    =   0;
        stud    =   id;
        lect    =   IdLect;
        discp   =   dis;
        group   =   grup;
        w   =   0;
    }
}
class   logIn   implements  Serializable{
    String ID;
    String  password;
    String  position;
    public  logIn(){        
    }
    public  logIn(String   I,String   pass, String  pos){
        password    =   pass;
        ID  =   I;
        position    =   pos;
    }            
}
