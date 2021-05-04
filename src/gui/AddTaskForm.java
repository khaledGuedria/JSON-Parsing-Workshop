/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import models.Task;
import services.TaskService;

/**
 *
 * @author khaledguedria
 */
public class AddTaskForm extends Form{
    
    public AddTaskForm(){
        this.setTitle("Add Task");
        this.setLayout(BoxLayout.y());
        
        TextField tfname = new TextField("", "Insert Task Name");
        TextField tfstatus = new TextField("", "Insert Task Status : 0 - 1");
        
        Button submitTaskBtn = new Button("Submit");
        submitTaskBtn.addActionListener((evt) -> {
            
            if (tfname.getText().length() ==0 || tfstatus.getText().length()==0) {
                Dialog.show("Alert", "Textfields cannot be empty.",null, "OK");
            }else {
                
                try {
                    
                Task task = new Task(Integer.parseInt(tfstatus.getText()), tfname.getText());
                    if (TaskService.getInstance().addTaskAction(task)) {
                        Dialog.show("Success", "Task added successfully.",null, "OK");
                    }
                    
                } catch (NumberFormatException e) {
                    Dialog.show("Alert", "Task's status must be a number.",null, "OK");
                }
                
                
                
            }
            
            
            
        });
        
        this.addAll(tfname, tfstatus, submitTaskBtn);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeForm().showBack());
        
        
    }
    
}
