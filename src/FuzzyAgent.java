import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.rule.Variable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class FuzzyAgent {
    private static final String[] fileArray = new String[]{"tipper1.fcl", "tipper2.fcl", "MamdaniQoSManyRules1.fcl", "MamdaniQoSManyRules2.fcl", "MamdaniQoSManyRules3.fcl", "MamdaniQoSManyRules4.fcl"};
    ArrayList<String> variables;
    private FIS fis;
    private String name;
    private String title;
    private String outputVar;
    private String application;
    private String aggregation;

    public FuzzyAgent(String title, String application, String aggregation, String name) {
        this.name = name;
        this.title = title;
        this.application = application;
        this.aggregation = aggregation;
        variables = new ArrayList<>();
        if (name.startsWith("fcl")) {
            String shN = name.replace("fcl", "");
            int numbr = Integer.parseInt(shN) - 1;
            fis = FIS.load(fileArray[numbr], true);
            Iterator<FunctionBlock> bl = fis.iterator();
            FunctionBlock fb = bl.next();
            for (Variable vv : fb.getVariables().values()) {
                if (vv.isInput()) {
                    variables.add(vv.getName());
                } else {
                    outputVar = vv.getName();
                }
            }
            Collections.sort(variables);
        }

    }

    public String getAggregation() {
        return aggregation;
    }

    public Double evaluate(String domain, int[] vars) {
        if (application.equals(domain)) {
            if (vars.length != variables.size()) {
                return null;
            }
            for (int i = 0; i < vars.length; i++) {
                fis.setVariable(variables.get(i), vars[i]);
            }
            fis.evaluate();
            return fis.getVariable(outputVar).getValue();
        } else {
            return null;
        }
    }
}
