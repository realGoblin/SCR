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
    List<ratings> re1;//список группы 
    ratings ratObject;
    int idRate;
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
        menuLect.jButton2.addActionListener(new  visibleRateStudent());        
        menuLect.jButton4.addActionListener(new  calculateRate());
        menuLect.jButton5.addActionListener(new  safeRate());
        menuLect.jButton3.addActionListener(new  safeWrRate());
        menuLect.setVisible(true);
     }
     class   visibleStudent   implements  ActionListener{
            @Override
            public  void actionPerformed(ActionEvent    event){
                rat =   downloadAndSave.downloadR();
        
            List<ratings> re = rat.stream()
            .filter(a -> Objects.equals(a.discp, menuLect.jComboBoxDisc.getSelectedItem()))
            .collect(Collectors.toList());
            re1 = re.stream()
            .filter(a -> Objects.equals(a.group, menuLect.jComboBoxGroup.getSelectedItem()))
            .collect(Collectors.toList());        
            for(int i   =0;i<re1.size();i++){
                ratings ratObject  =    (ratings)    re1.get(i);
                menuLect.addStudentInComboBox(ratObject.studName);
                } 
            }                
        }
     class   visibleRateStudent   implements  ActionListener{
         @Override
            public  void actionPerformed(ActionEvent    event){
                List<ratings> re0 = re1.stream()
                .filter(a -> Objects.equals(a.studName, menuLect.jComboBox1.getSelectedItem()))
                .collect(Collectors.toList()); 
                ratObject  =    (ratings)    re1.get(0);
                idRate  =   rat.indexOf(ratObject);
                menuLect.jTextField1.setText(String.valueOf(ratObject.R1));
                menuLect.jTextField2.setText(String.valueOf(ratObject.R2));
                menuLect.jTextField3.setText(String.valueOf(ratObject.gradPointAverage));
                menuLect.jTextField4.setText(String.valueOf(ratObject.wR1));
                menuLect.jTextField5.setText(String.valueOf(ratObject.wR2));
            }
     }
     class   calculateRate   implements  ActionListener{
            @Override
            public  void actionPerformed(ActionEvent    event){
                menuLect.jTextField3.setText(String.valueOf(Integer.parseInt(menuLect.jTextField1.getText())+Integer.parseInt(menuLect.jTextField2.getText())));
            }                
        }
     class   safeRate   implements  ActionListener{
            @Override
            public  void actionPerformed(ActionEvent    event){
                ratObject.R1    =   Integer.parseInt(menuLect.jTextField1.getText());
                ratObject.R2    =   Integer.parseInt(menuLect.jTextField2.getText());
                ratObject.gradPointAverage    =   Integer.parseInt(menuLect.jTextField3.getText());
                rat.set(idRate,ratObject);
                downloadAndSave.saveR(rat);
            }                
        }
     class   safeWrRate   implements  ActionListener{
            @Override
            public  void actionPerformed(ActionEvent    event){
                for(int i=0;i<re1.size();i++){
                    ratings rateOb  =   re1.get(i);
                    int iR  =   rat.indexOf(rateOb);
                    rateOb.wR1=Integer.parseInt(menuLect.jTextField4.getText());
                    rateOb.wR2=Integer.parseInt(menuLect.jTextField5.getText());
                    rat.set(iR,rateOb);
                }
                 downloadAndSave.saveR(rat);
            }                
        }
    }  