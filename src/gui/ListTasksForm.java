/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import services.TaskService;

/**
 *
 * @author khaledguedria
 */
public class ListTasksForm extends Form{
 
    public ListTasksForm(){
        
        this.setTitle("Tasks List");
        this.setLayout(BoxLayout.y());
        
        SpanLabel tasksListSP = new SpanLabel();
        tasksListSP.setText(TaskService.getInstance().getTasks().toString());
        
        this.add(tasksListSP);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeForm().showBack());
        
    }
}
