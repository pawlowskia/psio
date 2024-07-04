//Adam Pawlowski 266888
//used java version: correcto 1.8
//used mockito version: 1.10.19
//used gson version: 2.8.9
package observer.threaded_demo;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        new Main().run();
    }

    public void run() throws FileNotFoundException {
        Csi csi = new Csi(new DataRandomizer());
        csi.startObservable();
        String choice = "0";
        Utility u = new Utility();

        while(!choice.equals("8")){
            printMenu();
            choice = u.scanString();
            switch (choice) {
                case "0" : //export the list of all sensors to a .json file
                        csi.exportSensors();
                        break;
                case "1" : //create new KUPA
                        csi.register(u);
                        break;
                case "2" : //add new location to a chosen KUPA
                        csi.addLoc(u);
                        break;
                case "3" : //print the list of locations of a chosen KUPA
                        csi.printLoc(u);
                        break;
                case "4" : //delete location from a chosen KUPA
                        csi.deleteLoc(u);
                        break;
                case "5" : //avg+min+max values from a chosen location from a chosen KUPA
                        csi.calc(u);
                        break;
                case "6" : //save all data to a .json file
                        csi.saveToJson();
                        break;
                case "7" : // print all data;
                        csi.printAll();
                        break;
                case "8" : //exit or sth
                        csi.stopObservable();
                        break;
                default :
                        System.out.println("Insert an integer from range [0, 8]. Please, try again.");
                        break;
            }
        }
    }

    private void printMenu(){
        System.out.print("\nChoose an action:\n" +
                "0) export detailed list of sensors to .json file\n" +
                "1) create new KUPA\n" +
                "2) add new location to a chosen KUPA\n" +
                "3) print the list of locations of a chosen KUPA\n" +
                "4) delete location from a chosen KUPA (from now on chosen KUPA won't save measurements from this location, but old data is not going to be deleted)\n" +
                "5) print avg, min, max values from a chosen location from a chosen KUPA\n" +
                "6) save all data to a .json file\n" +
                "7) print all data\n" +
                "8) exit the program\n");
    }
}
