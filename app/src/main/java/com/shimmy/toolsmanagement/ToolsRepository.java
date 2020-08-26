package com.shimmy.toolsmanagement;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class ToolsRepository {
    private static ToolsRepository toolsRepository;

    private List<Tool> toolsAll;
    private List<Tool> currentlyTakenTools;
    private List<Tool> wantToHaveTools;
    private List<Tool> favoritesTools;
    private List<Tool> alreadyUsedTools;

    private String incomingBarcodeId;


    //constructor of singleton
    private ToolsRepository() {
        if (toolsAll == null) {
            toolsAll = new ArrayList<>();
            initDummyData();
        }
        if (currentlyTakenTools == null) {
            currentlyTakenTools = new ArrayList<>();
        }
        if (wantToHaveTools == null) {
            wantToHaveTools = new ArrayList<>();
        }
        if (favoritesTools == null) {
            favoritesTools = new ArrayList<>();
        }
        if (alreadyUsedTools == null) {
            alreadyUsedTools = new ArrayList<>();
        }
        incomingBarcodeId = new String();
    }

    //singleton; synchronized keyword make it thread safe; checking of existing of the singleton
    public static synchronized ToolsRepository getInstance(){
        if(toolsRepository == null){
            toolsRepository = new ToolsRepository();
        }
        return toolsRepository;
    }

    //DummyData for constructor
    private void initDummyData() {
        //TODO: add more initial data
        toolsAll.add(new Tool(1, "Wierto TE-CX (SDS+)", "HILTI", "fi 12mm x 150mm", "W magazynie", "magazyn", 9, "https://www.hilti" +
                ".pl/medias/sys_master/images/h43/h78/9527053254686.jpg", "wiertło HILTI super-extra-mega-wypas-do_metalu i do drewna"));
        toolsAll.add(new Tool(2, "Wierto ", "Sandvik", "fi 10mm x 200mm", "W magazynie", "magazyn", 5, "https://images-na.ssl-images-amazon" +
                ".com/images/I/71gHlVx1SRL._SL1500_.jpg", "wiertło HILTI super-extra-mega-wypas-do_metalu i do drewna"));
        toolsAll.add(new Tool(3, "Piła", "Husqvarna", "40cm 2,45KM", "W lesie", "Staszek", 34, "https://www.forestandarb" +
                ".com/res/Husqvarna%20120%20Mk%20II.jpg", "Piła łieeee łieeee!"));
        toolsAll.add(new Tool(570242009, "Młotek", "STANLEY", "30cm wysokości; obuch 1kg", "Przy kowadle", "Zbyszek", +100, "https://www" +
                ".stanleytools" +
                ".com/~/media/stanleytools/images/listing-images/antivibe_hammers.jpg", "Młotek stuk stuk buch buch!"));
    }


    public void addTool(String name, String manufacturer, String dimensions, String shortDesc){
        toolsAll.add(new Tool(generateID(), name, manufacturer, dimensions,"waiting for input", "waiting for input", 0,"", shortDesc));

    }

    private int generateID(){
        int maxID = 1;
        for (Tool tool: toolsAll){
            if (tool.getId()>maxID){
                maxID = tool.getId();
            }
        }
        return maxID+1;
    }


    public List<Tool> getToolsAll() {
        return toolsAll;
    }

    public List<Tool> getCurrentlyTakenTools() {
        return currentlyTakenTools;
    }

    public List<Tool> getWantToHaveTools() {
        return wantToHaveTools;
    }

    public List<Tool> getFavoritesTools() {
        return favoritesTools;
    }

    public List<Tool> getAlreadyUsedTools() {
        return alreadyUsedTools;
    }

    public String getIncomingBarcodeId() {
        return incomingBarcodeId;
    }


    //tells if added the tool successfully to the list
    public boolean addToCurrentlyTakenTools(Tool tool){
        return currentlyTakenTools.add(tool);
    }
    public boolean addToWantToHave(Tool tool){
        return wantToHaveTools.add(tool);
    }
    public boolean addToFavorites(Tool tool){
        return favoritesTools.add(tool);
    }
    public boolean addToAlreadyUsed(Tool tool){
        return alreadyUsedTools.add(tool);
    }

    //methods for handling id
    public Tool getToolById(int id){
        for (Tool t: toolsAll){
            if (t.getId() == id){
                return t;
            }
        }
        return null;
    }

    public void setIncomingBarcodeId(String incomingBarcodeId) {
        this.incomingBarcodeId = incomingBarcodeId;
    }

//    public String getIdFromScanner(){
//        Log.d(TAG, "getIdFromScanner: works or not");
//        String receivedBarcodeId = incomingIntent.getStringExtra("BARCODE_CAPTURE");
//        return receivedBarcodeId;
//    }

    public Tool searchForTool(String barcodeId){
        for (Tool t : getToolsAll()){
            if (String.valueOf(t.getId()).equals(barcodeId)){
                return t;
            }
        }
        return null;
    }

    public void removeFromCurrentlyTaken(String barcodeId) {
        for (Tool t : getCurrentlyTakenTools()) {
            if (String.valueOf(t.getId()).equals(barcodeId)) {
                currentlyTakenTools.remove(searchForTool(barcodeId));
                alreadyUsedTools.add(searchForTool(barcodeId));
            }
        }
    }


    public void displayToolById(){

    }

}
