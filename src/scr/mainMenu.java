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
    public  static void go  ()
    {
        mainMenu   f = new mainMenu();
        f.ccc(); 
    }
    public  void    ccc(){       
        JFrameMenu.jButtonNewPass.addActionListener(new  LabelListener());
        JFrameMenu.jButtonNewLecturer.addActionListener(new  LabelListenerLecturer());
        JFrameMenu.jButtonNewStudent.addActionListener(new  LabelListenerStudent());
        JFrameMenu.jButtonAddGroup.addActionListener(new  LabelListenerGroup());
        lect    =   downloadAndSave.downloadL();
        stud    =   downloadAndSave.downloadS();
        manLog    =   downloadAndSave.downloadA();
        rat =   downloadAndSave.downloadR();
        System.out.println("ты кто такой");
        System.out.println("ты кто такой");
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
            
            NewStudent.jTextStudentName.setText("");
            NewStudent.jTextStudentID.setText("");
            NewStudent.jTextStudentPassword.setText("");
            NewStudent.jTextStudentGroup.setText("");
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
        lect    =   downloadAndSave.downloadL();   
        manLog  =   downloadAndSave.downloadA();
        ArrayList<String>    list   =   new ArrayList<String>();
        ArrayList<String>    list1   =   new ArrayList<String>();
        lecturer    licturObject    =   new lecturer(NewLecturer.jTextFieldName.getText(),NewLecturer.jTextFieldID.getText(),NewLecturer.jTextFieldPass.getText(),list,list1);        
        lect.add(licturObject);
        downloadAndSave.saveL(lect);
        logIn   licturObjectLog =   new logIn(NewLecturer.jTextFieldID.getText(),NewLecturer.jTextFieldPass.getText(),"lecturer");
        manLog.add(licturObjectLog);
        downloadAndSave.saveLogs(manLog);
        NewLecturer.setVisible(false);
    }
    public  void    newPass(){
                manLog  =   downloadAndSave.downloadA();
                logIn    adminOb = (logIn) manLog.get(0);
                if(Objects.equals(newPassword.oldPass.getText(),adminObject.password)){
                    if(Objects.equals(newPassword.newPass.getText(),newPassword.newPass2.getText())){
                        adminObject.password    =   newPassword.newPass.getText();
                        adminOb.password    =   newPassword.newPass.getText();                        
                        manLog.set(0,adminOb);
                        downloadAndSave.saveLogs(manLog);                        
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
        stud    =   downloadAndSave.downloadS(); 
        manLog  =   downloadAndSave.downloadA();
        rat =   downloadAndSave.downloadR();
        ArrayList<String>   gro =   new ArrayList<String>();
        String  id  =   NewStudent.jTextStudentID.getText();
        student studentObject   =   new student(NewStudent.jTextStudentName.getText(),id,NewStudent.jTextStudentPassword.getText(),NewStudent.jTextStudentGroup.getText());
        stud.add(studentObject);     
        downloadAndSave.saveS(stud);
        logIn   studentObjectLog =   new logIn(id,NewStudent.jTextStudentPassword.getText(),"Student");
        manLog.add(studentObjectLog);         
        downloadAndSave.saveLogs(manLog);
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
            ratings raOb    =   (ratings)   res.get(i);
            ratings ra  =   new ratings(studentObject.FIO,id,raOb.lect,raOb.discp,raOb.group);
            rat.add(ra);
        }        
        downloadAndSave.saveR(rat);
        //
        NewStudent.setVisible(false);
    }
    public  void    addGroup(){
        //
        ArrayList<String>   gro =   new ArrayList<String>(); 
        stud    =   downloadAndSave.downloadS(); 
        rat =   downloadAndSave.downloadR();
        lect    =   downloadAndSave.downloadL();
        lecturer    lecturerObject  =   new lecturer();
        String  grup   =   AddGroup.jTextFieldGroup.getText();
        String  dis   =   AddGroup.jTextDis.getText();
        List re = lect.stream()
        .filter(a -> Objects.equals(a.ID, AddGroup.jTextFieldLecturer.getText()))
        .collect(Collectors.toList());
        lecturerObject  =   (lecturer)   re.get(0);
       lecturerObject.listGroups.add(grup);
       lecturerObject.listDiscp.add(dis);
        int z   =   lect.indexOf(lecturerObject);
        lect.set(z, lecturerObject);
        downloadAndSave.saveL(lect);
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
            ratings ra  =   new ratings(studentObject.FIO,studentObject.ID,lecturerObject.ID,dis,studentObject.myGroup);
            rat.add(ra);
        }
        downloadAndSave.saveR(rat);        
    }
}