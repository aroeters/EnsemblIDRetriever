/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filewriter;
import databaseconnection.ENSEMBLDatabaseConnector;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;

/**
 *
 * @author arne
 */
public class TsvWriter {

    public final void writeTsv(final String dbConncection, final String resultDir) throws Exception {
        String db;
        if (dbConncection.contains("jdbc:mysql://")) {
           db = dbConncection;
        } else {
            db = "jdbc:mysql://" + dbConncection;
        }
        ENSEMBLDatabaseConnector edc = new ENSEMBLDatabaseConnector(db);
        File file = new File(resultDir+dbConncection.split("/")[1]+ "_dump.tsv");
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        HashMap<String, String> edcDump = edc.getIDConversion();
        try (BufferedWriter bw = new BufferedWriter(fw)) {
            for (String ENSG: edcDump.keySet()) {
                bw.write(edcDump.get(ENSG) + "\t" + ENSG + "\n");
            }
        }
    }
}
