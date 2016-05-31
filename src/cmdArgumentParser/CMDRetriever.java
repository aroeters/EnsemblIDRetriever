/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmdArgumentParser;

import java.io.File;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;

/**
 *
 * @author arne
 */
public class CMDRetriever {

    /**
     * All CMD options.
     */
    Options options = new Options();

    public CMDRetriever(final String[] args) throws UnrecognizedOptionException {
        options.addOption(OptionBuilder.withLongOpt("ensembl_db")
                .withDescription("The ensembl database version to use. for example:\n"
                        + "ensembldb.ensembl.org:5306/homo_sapiens_core_83_38\n"
                        + "for version 83")
                .hasArg()
                .create("d"));
        options.addOption(OptionBuilder.withLongOpt("result_dir")
                .withDescription("The directory where the resulting dump should be placed in.\n"
                        + "for version 83")
                .hasArg()
                .create("r"));
    }

    public final String getENSEMBLVersion(final String[] args) throws ParseException {
        CommandLineParser parser = new BasicParser();
        CommandLine cmd = parser.parse(options, args);
        if (cmd.hasOption("d") || cmd.hasOption("ensembl_db")) {
            return cmd.getOptionValue("d");
        } else {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("Please provide a ensembl database version", options);
            System.exit(0);
        }
        return "No ensembl database given";
    }

    public final String getResultDir(final String[] args) throws ParseException {

        CommandLineParser parser = new BasicParser();
        CommandLine cmd = parser.parse(options, args);
        if (cmd.hasOption("r") || cmd.hasOption("result_dir")) {
            if (new File(cmd.getOptionValue("r")).isDirectory()) {
                return cmd.getOptionValue("r");
            } else {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("Please provide a valid directory to place the results in", options);
                System.exit(0);
            }
        }
        return "./";
    }
}
