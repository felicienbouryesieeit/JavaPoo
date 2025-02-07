package org.example;
import java.io.*;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.ArrayList;


public class Save {

    String savefile = "save.txt";
    private BufferedWriter writer = null;
    private BufferedReader reader = null;

    private StockProduct stockproduct;

    private ArrayList<Product> inventory = new ArrayList<>();
    private ArrayList<User> inventoryindex = new ArrayList<>();

    public void setInventoryindex(ArrayList<User> inventoryindex) {
        this.inventoryindex = inventoryindex;

    }

    public ArrayList<User> getInventoryindex() {

        return inventoryindex;
    }


    public void setStockProduct(StockProduct stockproduct) {
        this.stockproduct = stockproduct;
    }

    public StockProduct getStockProduct() {
        return stockproduct;
    }
    public void setInventory(ArrayList<Product> inventory) {
        this.inventory = inventory;
    }
    public ArrayList<Product> getInventory() {
        return inventory;
    }

    public static void save(String savefile) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(savefile));
            writer.write("Liste des utilisateurs");
            //writer.write(stockUser.showIneventory());

            writer.write("\n12.5");
            writer.write("\n12.5");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



/*
    public static Object load(String savefile) {
        try { BufferedReader reader = new BufferedReader(new FileReader(savefile));
            String line;
            while((line = reader.readLine()) != null) {
                Double linenumber = Double.parseDouble(line);
                System.out.println("zubzub"+linenumber);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Aucune sauvegarde trouvée pour " + savefile);
            return null;
        }
    }*/



    public void initialize() throws IOException {
        writer = new BufferedWriter(new FileWriter(savefile));
        reader = new BufferedReader(new FileReader(savefile));
        //writer.write("begin");
    }
    public void writesavefile(String stringarg) throws IOException {


        writer.write(stringarg+"\n");
        //writer.close();

    }

    public void closesavefile() throws IOException {
        writer.close();
    }

    public ArrayList<String> savefilebackup() throws IOException {
        reader = new BufferedReader(new FileReader(savefile));
        ArrayList<String> savefilebackup = new ArrayList<>();
        String line;

        while((line = reader.readLine()) != null) {

            savefilebackup.add(line);
            //writesavefile(line);
        }
        return savefilebackup;
    }

    public void addline(String currenttext) throws IOException {

        ArrayList<String> savefilebackup2 = savefilebackup();


        writer = new BufferedWriter(new FileWriter(savefile));

        for (String line2 : savefilebackup2) {
            writesavefile(line2);
        }


        writesavefile(currenttext);
        closesavefile();
    }


    public void loadsavefile() throws IOException {

        reader = new BufferedReader(new FileReader(savefile));

        String line;
        int typeofobject = -1;
        int index = 0;
        ArrayList<String> savefilebackup2 = savefilebackup();
        ArrayList<String> arglist = new ArrayList<>();
        writer = new BufferedWriter(new FileWriter(savefile));

        for (String line2 : savefilebackup2) {

            if (line2.equals("product")) {
                typeofobject = 0;
                index=4;
            }


            if (line2.equals("user")) {
                typeofobject = 1;
                index=3;
            }


            System.out.println("zubzub indexv:"+index+" type of object :"+typeofobject+line2);
            arglist.add(line2);


            if (index==0) {
                switch (typeofobject) {
                    case 0:
                        createproduct(arglist);
                        break;
                    case 1:

                        break;

                }

                arglist = new ArrayList<>();

            } else {

                index-=1;

            }



        }

        reader.close();
    }

    public void customwritesave(int filetype, ArrayList<String> liste) throws IOException {


        for(String currenttext : liste) {
            writesavefile(currenttext);
        }


    }




    public void setsavetext() throws IOException {
    initialize();
    for(Product p : inventory) {
        addline("product");
        addline(p.getName());
        addline(Integer.toString(p.getQuantity()));
        addline(Double.toString(p.getPrice()));
        addline(p.getCategory().getName());


    }
    for (User user : inventoryindex) {
        addline("user");
        addline(user.getUsername());
        addline(user.getPassword());
        user.setUserType();
        addline(Integer.toString(user.getUsertypeid()));



    }
    }

    public void createproduct(ArrayList<String> arglist) throws IOException {
        System.out.println("createproduct 000000000000 " +arglist.get(0)+" "+arglist.get(1)+" "+arglist.get(2)+" "+arglist.get(3) + " "+arglist.get(4));
        stockproduct.addStocktosave(arglist.get(1),Integer.parseInt(arglist.get(2)),Double.parseDouble(arglist.get(3)),arglist.get(4));
    }



    public void createuser(ArrayList<String> arglist) throws IOException {
        System.out.println("createproduct 000000000000 " +arglist.get(0)+" "+arglist.get(1)+" "+arglist.get(2)+" "+arglist.get(3) );
        stockproduct.addStocktosave(arglist.get(1),Integer.parseInt(arglist.get(2)),Double.parseDouble(arglist.get(3)),arglist.get(4));
    }
}
