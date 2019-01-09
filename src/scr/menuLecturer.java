/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scr;
import  java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import  java.awt.event.*;
/**
 *
 * @author Goblin
 */
public class menuLecturer {
    ArrayList<lecturer> lect;
    List<lecturer> result;  
    ArrayList<ratings> rat;
    JFrameLecturer  menuLect   =   new    JFrameLecturer();
     public static   void    go(String   i){
        menuLecturer ff  =   new menuLecturer();
        ff.start(i);
    }
     public  void    start(String   idlect){
        lect    =   downloadAndSave.downloadL(); 
        result = lect.stream()
        .filter(a -> Objects.equals(a.ID, idlect))
        .collect(Collectors.toList());
        lecturer lectObject  =    (lecturer)    result.get(0);
        menuLect.addGroupAndDiscpInComboBox(lectObject);
        menuLect.jButton1.addActionListener(new  visibleStudent());
        menuLect.setVisible(true);
     }
     class   visibleStudent   implements  ActionListener{
            @Override
            public  void actionPerformed(ActionEvent    event){
                rat =   downloadAndSave.downloadR();
        
            List<ratings> re = rat.stream()
            .filter(a -> Objects.equals(a.discp, menuLect.jComboBoxDisc.getSelectedItem()))
            .collect(Collectors.toList());
            List<ratings> re1 = re.stream()
            .filter(a -> Objects.equals(a.group, menuLect.jComboBoxGroup.getSelectedItem()))
            .collect(Collectors.toList());        
            for(int i   =0;i<re1.size();i++){
                ratings ratObject  =    (ratings)    re1.get(i);
                menuLect.addStudentInComboBox(ratObject.stud);
                } 
            }                
        }
    } 
     
