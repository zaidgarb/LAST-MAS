import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

/**
 * Example of ManagerAgent
 */
public class ManagerAgent {
    boolean initialized;

    ArrayList<FuzzyAgent> agents;

    public ManagerAgent() {
        initialized = false;
        agents = new ArrayList<>();
    }

    public void evaluate(String file) {
        if (initialized) {
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(file));
                String line = reader.readLine();
                String domain = line;
                line = reader.readLine();
                while (line != null) {
                    String inputVariables = line;

                    System.out.println(translateData(domain, inputVariables));
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Agents not initialized");
        }
    }

    private String translateData(String domain, String inputVariables) {
        String[] varsS = inputVariables.split(",");
        int[] vars = new int[varsS.length];
        for (int i = 0; i < varsS.length; i++) {
            vars[i] = Integer.parseInt(varsS[i]);
        }
        ArrayList<Double> res = new ArrayList<>();
        String method = "";
        for (FuzzyAgent agent : agents) {
            Double d = agent.evaluate(domain, vars);
            if (d != null) {
                res.add(d);
                method = agent.getAggregation();
            }
        }
        if (res.size() == 0) {
            return "No data processed";
        } else {
            double resD = 0;
            switch (method.toLowerCase()) {
                case "":
                    resD = res.get(0);
                    break;
                case "max":
                    resD = Collections.max(res);
                    ;
                    break;
                case "min":
                    resD = Collections.min(res);
                    break;
                case "average":
                    for (Double d : res) {
                        resD = resD + d;
                    }
                    resD = resD / res.size();
                    break;
            }
            return "Output value:" + resD;
        }
    }

    /**
     * properties-based notation is used
     *
     * @param file
     */
    public boolean initialize(String file) {
        try (InputStream input = new FileInputStream(file)) {
            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            String title = prop.getProperty("urv.imas.title");
            String application = prop.getProperty("urv.imas.application");
            String aggregation = prop.getProperty("urv.imas.aggregation");
            int fuzzyagents = Integer.parseInt(prop.getProperty("urv.imas.fuzzyagents"));
            String[] ag = prop.getProperty("urv.imas.fuzzySettings").split(",");
            for (String s : ag) {
                agents.add(new FuzzyAgent(title, application, aggregation, s));
            }
            initialized = true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
