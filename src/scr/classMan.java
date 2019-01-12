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
    String  FIO;
    String  ID;
    String  position;
    String  password;
    
}
class   admin  extends classMan{
    admin(){
    FIO    =   "";
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
    public  lecturer    (String  myName, String  IDi,    String  pass,ArrayList<String>    listGroup,ArrayList<String>    listDisc){
        FIO    =   myName;
        ID  =   IDi;
        position    =   "lecturer";
        password    =   pass;
        listGroups =   listGroup;        
        listDiscp =   listDisc;
    }
}
class   student extends classMan{
    String   myGroup;
    public  student(){
    }
    public  student(String  myName, String  IDi,    String  pass,   String   groupMy){        
        FIO    =   myName;
        ID  =   IDi;
        position    =   "Student";
        password    =   pass;
        myGroup =   groupMy;   
    }   
}
class   ratings implements  Serializable{
    int   R1;
    int   R2;
    int   gradPointAverage;
    String  studName;
    String  stud;
    String  lect;
    String  group;
    String  discp;
    double wR1;
    double wR2;
    public  ratings(){
        //deafult
    }
    public  ratings(String  Name,String id,String IdLect,String   dis,String grup){   
        studName    =   Name;
        stud    =   id;
        lect    =   IdLect;
        discp   =   dis;
        group   =   grup;
        R1 =   0;
        R2    =   0;
        gradPointAverage    =   0;
        wR1   =   0.5;
        wR2 =   0.5;
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
