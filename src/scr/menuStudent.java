package scr;

import  java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import  javax.swing.*;
    
public  class   menuStudent{
    JFrameStudent  menuStud   =   new    JFrameStudent();
    ArrayList<ratings> rat;
    List<ratings> result;    
    public static   void    go(String   i){
        menuStudent ff  =   new menuStudent();
        ff.start(i);
    }
    public  void    start(String   idStudent){
        //JFrameStudent.JCombo   listDiscp    =   new JFrameStudent.JCombo();
        downloadRat();
        
        result = rat.stream()
        .filter(a -> Objects.equals(a.stud, idStudent))
        .collect(Collectors.toList());
        
        for(int i   =0;i<result.size();i++){
            ratings ratObject  =    (ratings)    result.get(i);
            JFrameStudent.JCombo.addItem(ratObject.discp);            
        }        
        //JFrameStudent.jComboBox1    =   listDis;        
        JFrameStudent.jButtonVisiblRat.addActionListener(new  visiblRat());
        menuStud.setVisible(true);
    }
    class   visiblRat   implements  ActionListener{
        @Override
        public  void actionPerformed(ActionEvent    event){
            visRat();
        }  
    }
    public  void    visRat(){
        String  vibDisc =   (String)   JFrameStudent.JCombo.getSelectedItem();
        List res = result.stream()
        .filter(a -> Objects.equals(a.discp, vibDisc))
        .collect(Collectors.toList());
        ratings rObject  =    (ratings)    res.get(0);        
        menuStud.jLabel2.setText(String.valueOf(rObject.lectures));
        menuStud.jLabel3.setText(String.valueOf(rObject.practic));
    }
    public  void    downloadRat(){
        try{
        ObjectInputStream   iR  =   new ObjectInputStream(new   FileInputStream("E:\\scrRat.ser")); 
        rat    =   (ArrayList<ratings>)   iR.readObject();  
        }catch(Exception ex){
        System.out.println(ex.getMessage());
        }
    }
}