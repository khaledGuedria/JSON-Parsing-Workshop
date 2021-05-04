/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author khaledguedria
 */
public class HomeForm extends Form{
    
    
    public HomeForm(){
        
        this.setTitle("Home Form");
        this.setLayout(BoxLayout.y());
        
        Button addTaskBtn = new Button("Add Task");
        Button listTasksBtn = new Button("Tasks List");
        
        addTaskBtn.addActionListener(e -> new AddTaskForm().show());
        listTasksBtn.addActionListener(x-> new ListTasksForm().show());
        
        this.addAll(addTaskBtn, listTasksBtn);
        
    }
    
}
