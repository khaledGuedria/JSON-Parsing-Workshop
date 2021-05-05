/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import models.Task;
import utils.Statics;

/**
 *
 * @author khaledguedria
 */
public class TaskService {
    
    //var
    boolean resultOK;
    ConnectionRequest req;
    static TaskService instance;
    ArrayList<Task> tasks = new ArrayList<>();
    
    
    //constructor
    private TaskService() {
        req = new ConnectionRequest();
    }
    
    //SINGLETON
    public static TaskService getInstance(){
        
        if (instance == null) {
            instance = new TaskService();
        }
        
        return instance;
    }
    
    
    
    
    //ADD TASK 
    public boolean addTaskAction(Task t){
        
        String url = Statics.BASE_URL + "/tasks/"+ t.getName() + "/" + t.getStatus();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((evt) -> {
            resultOK = req.getResponseCode()==200;
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
    
    //PARSE TASKS JSON : convert JSON to java objects
    public ArrayList<Task> parseJSONAction(String textJson){
        
        JSONParser j = new JSONParser();
        
        try {
            
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(textJson.toCharArray()));
            ArrayList<Map<String,Object>> tasksList = (ArrayList<Map<String,Object>>) tasksListJson.get("root");
            
            for (Map<String, Object> obj : tasksList) {
                
                Task t = new Task();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setStatus((int) Float.parseFloat(obj.get("status").toString()));
                t.setName(obj.get("name").toString());
                
                tasks.add(t);

            }
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return tasks;  
    }



    //GET TASKS
    public ArrayList<Task> getTasks(){
        
         String url = Statics.BASE_URL+"/tasks/";
         ConnectionRequest request = new ConnectionRequest(url);
         request.setPost(false);
         request.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 
             tasks = parseJSONAction(new String(request.getResponseData()));
             request.removeResponseListener((ActionListener<NetworkEvent>) this);
             }
         });
         
        
        
        NetworkManager.getInstance().addToQueueAndWait(request);
        return tasks;
    }
    

}
