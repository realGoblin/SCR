/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scr;
//import  javax.swing.*;
import  java.awt.event.*;
import  java.io.*;
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
    String  addresLogIn  =   "E:\\scrLogIn.ser";
    String  addresDataBaseLecturer  =   "E:\\scrLec.ser";
    String  addresDataBaseStudent  =   "E:\\scrStud.ser";
    String  addresDataBaseRat  =   "E:\\scrRat.ser";    
    NewJFrame   logInFrame  =   new NewJFrame();
    public static void main(String[] args) {
        SCR gui =   new SCR();   
        gui.go();  
    }
    public void go(){                  
                try{
                    //Загнружаем базу
                    ObjectInputStream   is  =   new ObjectInputStream(new   FileInputStream(addresLogIn)); 
                    manLog    =   (ArrayList<logIn>)   is.readObject();            
                }catch(Exception ex){
                    firstStart();
                    System.out.println(ex.getMessage());
                    //ex.printStackTrace();
                }
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
                    try{                        
                        ObjectOutputStream  os = new ObjectOutputStream(new  FileOutputStream(addresLogIn));
                        os.writeObject(manLog);
                        os.close();
                        ObjectOutputStream  oM = new ObjectOutputStream(new  FileOutputStream(addresDataBaseLecturer));
                        oM.writeObject(lec);
                        oM.close();
                        ObjectOutputStream  oR = new ObjectOutputStream(new  FileOutputStream(addresDataBaseRat));
                        oR.writeObject(rat);
                        oR.close();
                        ObjectOutputStream  oS = new ObjectOutputStream(new  FileOutputStream(addresDataBaseStudent));
                        oS.writeObject(stud);
                        oS.close();
                        } catch (Exception ex1) {
                            System.out.println(ex1.getMessage());
                            //ex1.printStackTrace();
                        }
    }
    class   LabelListener   implements  ActionListener{
            @Override
            public  void actionPerformed(ActionEvent    event){
                //авторизация
                String  login   =  NewJFrame.jTextFieldLogin.getText();
                //String  login   =   "ADMIN";
                String  pass   =  NewJFrame.jTextFieldPass.getText();
                //String  pass =   "AD";
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
                        }
                    }else{
                    System.out.println("пшел вон");
                    }
                }                
        }
    }    
}        


    
//    public void save(ArrayList<classMan> mane){
//        try{                        
//                        ObjectOutputStream  os = new ObjectOutputStream(new  FileOutputStream(addres));
//                        os.writeObject(mane);
//                        os.close();
//                        } catch (Exception ex1) {
//                            System.out.println(ex1.getMessage());
//                            ex1.printStackTrace();
//                        }
//    }
    
    

