/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ensemblidretriever;

import cmdArgumentParser.CMDRetriever;
import filewriter.TsvWriter;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;
/**
 *
 * @author arne
 */
public class EnsemblIDRetriever {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, Exception {
        CMDRetriever cmdr = null;
        try {
        cmdr = new CMDRetriever(args);
        } catch (UnrecognizedOptionException e) {
            System.out.println("Please only use the -d for the database and\n"
                    + "the -r for the directory to place the file dump in.");
        }
        TsvWriter tw = new TsvWriter();
        tw.writeTsv(cmdr.getENSEMBLVersion(args), cmdr.getResultDir(args));
    }
    
}
