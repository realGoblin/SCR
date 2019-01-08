/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scr;
//import  javax.swing.*;
import  java.awt.event.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Goblin
 */
public class SCR {
    
    ArrayList<logIn> manLog;
    NewJFrame   logInFrame  =   new NewJFrame();
    public static void main(String[] args) {
        SCR gui =   new SCR();   
        gui.go();  
    }
    public void go(){                  
            manLog  =   downloadAndSave.downloadA();
            logInFrame.setVisible(true);
            NewJFrame.jButtonLogIn.addActionListener(new  LabelListener());    
        }
    public  void firstStart(){
                    ArrayList<ratings> rat    =   new ArrayList<ratings>();
                    ArrayList<lecturer> lec    =   new ArrayList<lecturer>();
                    ArrayList<student> stud    =   new ArrayList<student>();
                    manLog =   new ArrayList<logIn>();    
                    //admin   ad   =   new admin();
                    logIn   adLog    =   new logIn("ADMIN","ADMIN","ADMIN");
                    manLog.add(adLog);
                    //man.add(ad);
                    downloadAndSave.saveLogs(manLog);
                    downloadAndSave.saveL(lec);
                    downloadAndSave.saveR(rat);
                    downloadAndSave.saveS(stud);
    }
    class   LabelListener   implements  ActionListener{
            @Override
            public  void actionPerformed(ActionEvent    event){
                //авторизация
                String  login   =  NewJFrame.jTextFieldLogin.getText();                
                String  pass   =  NewJFrame.jTextFieldPass.getText();                
                //поиск по ИД
                List result = manLog.stream()
                .filter(a -> Objects.equals(a.ID, login))
                .collect(Collectors.toList());
                if(result.isEmpty()){
                    System.out.println("ты кто такой");
                }else{
                logIn    res =   new logIn();
                res = (logIn)  result.get(0);                    
                if(Objects.equals(pass,res.password)){
                    //залогинились
                    System.out.println("приветстую тебя");
                    if(Objects.equals(res.position, "ADMIN")){
                        //админов туда
                        logInFrame.setVisible(false);
                        mainMenu.go();
                        }else if(Objects.equals(res.position, "Student")){
                        // студентов сюда
                        System.out.println("student"); 
                        logInFrame.setVisible(false);
                        menuStudent.go(res.ID);
                        }else{
                        // а суда остальных lecturer
                        System.out.println("lecturer"); 
                        logInFrame.setVisible(false);
                        menuLecturer.go(res.ID);
                        }
                    }else{
                    System.out.println("пшел вон");
                    }
                }                
        }
    }  
}        
    

