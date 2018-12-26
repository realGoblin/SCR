/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scr;

//import javax.swing.*;
import  java.awt.event.*;
import  java.util.ArrayList;
import  java.util.Objects;
import java.util.List;
import java.util.stream.Collectors;
import  java.io.*;

/**
 *
 * @author Goblin
 */
public  class mainMenu {
    classMan adminObject;
    ArrayList<logIn> manLog;
    ArrayList<lecturer> lect;
    ArrayList<ratings> rat;
    ArrayList<student> stud;
    JFrameMenu  menuFrame   =   new    JFrameMenu();
    JFrameNewLecturer   NewLecturer =   new JFrameNewLecturer();
    JFrameNewStudent  NewStudent   =   new    JFrameNewStudent();
    JFrameAddGroup  AddGroup   =   new    JFrameAddGroup();
    newPassword newpas  =   new newPassword();
    String  addresLogIn  =   "E:\\scrLogIn.ser";
    String  addresDataBaseLecturer  =   "E:\\scrLec.ser";
    String  addresDataBaseStudent  =   "E:\\scrStud.ser";
    String  addresDataBaseRat  =   "E:\\scrRat.ser";
    public  static void go  ()
    {
        mainMenu   f = new mainMenu();
        f.ccc();
    }
    public  void    ccc(){       
        System.out.println("приветстую тебя избранный");
        JFrameMenu.jButtonNewPass.addActionListener(new  LabelListener());
        JFrameMenu.jButtonNewLecturer.addActionListener(new  LabelListenerLecturer());
        JFrameMenu.jButtonNewStudent.addActionListener(new  LabelListenerStudent());
        JFrameMenu.jButtonAddGroup.addActionListener(new  LabelListenerGroup());
        
        menuFrame.setVisible(true); 
    }
    class   LabelListener   implements  ActionListener{
        @Override
        public  void actionPerformed(ActionEvent    event){
            newpas.setVisible(true);
            newPassword.jButtonNewPassSave.addActionListener(new  LalListener());            
        }  
    }
    class   LalListener   implements  ActionListener{
            @Override
            public  void actionPerformed(ActionEvent    event){
                newPass();
                System.out.println("приветстую тебя избранный");
            }            
    }
    class   LabelListenerLecturer   implements  ActionListener{
        @Override
        public  void    actionPerformed(ActionEvent    event){            
            NewLecturer.setVisible(true);
            NewLecturer.jButtonAddNewLecturer.addActionListener(new  LabelListenerAddLecturer());            
        }    
    }
    class   LabelListenerAddLecturer   implements  ActionListener{
        @Override
        public  void    actionPerformed(ActionEvent    event){
            newLecturer();
        }    
    }
    class   LabelListenerStudent   implements  ActionListener{
        @Override
        public  void    actionPerformed(ActionEvent    event){            
            NewStudent.setVisible(true);
            NewStudent.jButtonAddNewStudent.addActionListener(new  LabelListenerAddStudent());            
        }    
    }
    class   LabelListenerAddStudent   implements  ActionListener{
        @Override
        public  void    actionPerformed(ActionEvent    event){
            newStudent();
        }    
    }
    class   LabelListenerGroup   implements  ActionListener{
        @Override
        public  void actionPerformed(ActionEvent    event){
            AddGroup.setVisible(true);
            AddGroup.jButtonAddGrou.addActionListener(new  LalListenerAddGro());            
        }  
    }
    class   LalListenerAddGro   implements  ActionListener{
            @Override
            public  void actionPerformed(ActionEvent    event){
                addGroup();
                
            }            
    }
    public  void    newLecturer(){
        downloadLect();   
        downloadAuten();
        lecturer    licturObject    =   new lecturer(NewLecturer.jTextFieldName.getText(),NewLecturer.jTextFieldSurname.getText(),NewLecturer.jTextFieldPatronymic.getText(),
        String.valueOf(lect.size()),NewLecturer.jTextFieldPass.getText());        
        lect.add(licturObject);
        saveLect();
        logIn   licturObjectLog =   new logIn(String.valueOf(lect.size()),NewLecturer.jTextFieldPass.getText(),"lecturer");
        manLog.add(licturObjectLog);
        saveLog();
        NewLecturer.setVisible(false);
    }
    public  void    newPass(){
                downloadAuten();
                logIn    adminOb = (logIn) manLog.get(0);
                if(Objects.equals(newPassword.oldPass.getText(),adminObject.password)){
                    if(Objects.equals(newPassword.newPass.getText(),newPassword.newPass2.getText())){
                        adminObject.password    =   newPassword.newPass.getText();
                        adminOb.password    =   newPassword.newPass.getText();                        
                        manLog.set(0,adminOb);
                        saveLog();                        
                        newpas.setVisible(false);
                    }else{
                        //raznii paroli
                        System.out.println("raznii paroli");
                    }
                }else{
                    //ne ugadal
                    System.out.println("ne ugadal");
                }
                  } 
    public  void    newStudent(){
        downloadStud(); 
        downloadAuten();
        downloadRat();
        ArrayList<String>   gro =   new ArrayList<String>();
        student studentObject   =   new student(NewStudent.jTextStudentName.getText(),NewStudent.jTextStudentSubname.getText(),NewStudent.jTextStudentPatronymic.getText(),
        String.valueOf(stud.size()),NewStudent.jTextStudentPassword.getText(),NewStudent.jTextStudentGroup.getText());
        stud.add(studentObject);        
        logIn   studentObjectLog =   new logIn(String.valueOf(stud.size()),NewStudent.jTextStudentPassword.getText(),"Student");
        manLog.add(studentObjectLog);        
        //добавление рейтинга новому студенту
        List result = stud.stream()
        .filter(a -> Objects.equals(a.myGroup, NewStudent.jTextStudentGroup.getText()))
        .collect(Collectors.toList());
        student studObject   =   (student)   result.get(0);
        List res = rat.stream()
        .filter(a -> Objects.equals(a.stud, studObject.ID))
        .collect(Collectors.toList());
        for(int i=0;i<res.size();i++){
            //перебор рейтингов и создание аналогов для нового студента
            ratings raOb    =   (ratings)   result.get(i);
            ratings ra  =   new ratings(String.valueOf(stud.size()),raOb.lect,raOb.discp,raOb.group);
            rat.add(ra);
        }
        saveLog();
        saveStud();
        saveRate();
        //
        NewStudent.setVisible(false);
    }
    public  void    addGroup(){
        //
        ArrayList<String>   gro =   new ArrayList<String>(); 
        downloadLect();
        downloadRat();
        downloadStud();
        lecturer    lecturerObject  =   new lecturer();
        String  grup   =   AddGroup.jTextFieldGroup.getText();
        String  dis   =   AddGroup.jTextDis.getText();
        lecturerObject  =   (lecturer)   lect.get(Integer.parseInt(AddGroup.jTextFieldLecturer.getText()));
        lecturerObject.listGroups.add(grup);
        lect.set(Integer.parseInt(AddGroup.jTextFieldLecturer.getText()), lecturerObject);
        saveLect();
        //
        //добавление рейтинга
        // 
        //
        
        List result = stud.stream()
        .filter(a -> Objects.equals(a.myGroup, grup))
        .collect(Collectors.toList());
        for(int i=0;i<result.size();i++){
            //перебор студентов и создание рэйтинга
            student studentObject   =   (student)   result.get(i);
            ratings ra  =   new ratings(studentObject.ID,lecturerObject.ID,dis,studentObject.myGroup);
            rat.add(ra);
        }
        saveRate();        
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
