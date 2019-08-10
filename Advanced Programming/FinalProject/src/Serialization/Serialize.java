package Serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serialize {
    public static void serializeObject(Object ob) {
        String filename = "test.tanki";

        // save the object to file
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(ob);

            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Object deserializeObject(String fileName) {
        FileInputStream fis = null;
        ObjectInputStream in = null;
        Object outPut = null ;
        try {
            fis = new FileInputStream(fileName);
            in = new ObjectInputStream(fis);
            outPut = in.readObject();
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return outPut;
    }
}
