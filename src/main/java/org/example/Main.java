package org.example;

import picocli.CommandLine;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@CommandLine.Command(name="GithubtrendingCLI", mixinStandardHelpOptions = true)
public class Main implements Runnable {
    @CommandLine.Option(names = {"--d", "--duration"}, defaultValue = "WEEK", description = "Duration")
    Duration duration;
    @CommandLine.Option(names = {"--l", "--limit"}, description = "limit the result")
    private int value=10;

    @Override
    public void run() {
        System.out.println(duration +"and"+ value);
    }

    public static void main(String[] args) {
        System.exit(new CommandLine(new Main()).execute(args));
    }

    enum Duration {
        MONTH, YEAR, WEEK, DAY,
    }
}