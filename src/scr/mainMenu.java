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
        downloadLect();
        downloadStud();
                downloadAuten();
                        downloadRat();
        menuFrame.setVisible(true); 
        System.out.println("приветстую тебя избранный");
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
        ArrayList<String>    list   =   new ArrayList<String>();
        ArrayList<String>    list1   =   new ArrayList<String>();
        lecturer    licturObject    =   new lecturer(NewLecturer.jTextFieldName.getText(),NewLecturer.jTextFieldID.getText(),NewLecturer.jTextFieldPass.getText(),list,list1);        
        lect.add(licturObject);
        saveLect();
        logIn   licturObjectLog =   new logIn(NewLecturer.jTextFieldID.getText(),NewLecturer.jTextFieldPass.getText(),"lecturer");
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
        String  id  =   NewStudent.jTextStudentID.getText();
        student studentObject   =   new student(NewStudent.jTextStudentName.getText(),id,NewStudent.jTextStudentPassword.getText(),NewStudent.jTextStudentGroup.getText());
        stud.add(studentObject);        
        logIn   studentObjectLog =   new logIn(id,NewStudent.jTextStudentPassword.getText(),"Student");
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
            System.out.println("ne ugadal");
            ratings raOb    =   (ratings)   res.get(i);
            System.out.println("ne ugadal");
            ratings ra  =   new ratings(id,raOb.lect,raOb.discp,raOb.group);
            System.out.println("ne ugadal");
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
        List re = lect.stream()
        .filter(a -> Objects.equals(a.ID, AddGroup.jTextFieldLecturer.getText()))
        .collect(Collectors.toList());
        System.out.println("ne ugadal");
        lecturerObject  =   (lecturer)   re.get(0);
        System.out.println("ne ugadal");
       // lecturerObject.listGroups.add(grup);
       lecturerObject.listGroups.add(grup);
       lecturerObject.listDiscp.add(dis);
        System.out.println("ne ugadal");
        int z   =   lect.indexOf(lecturerObject);
        System.out.println("ne ugadal");
        lect.set(z, lecturerObject);
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
