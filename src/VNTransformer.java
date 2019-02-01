import soot.*;
import soot.toolkits.graph.BriefUnitGraph;
import soot.toolkits.graph.UnitGraph;
import java.util.Iterator;
import java.util.Map;

public class VNTransformer extends SceneTransformer {

    @Override
    protected void internalTransform(String arg0, Map arg1) {
        SootClass mainClass = Scene.v().getMainClass(); // get main class

        System.out.println("Methods: " + mainClass.getMethodCount()); // get methods count for class
        SootMethod testMethod = mainClass.getMethodByName("test"); // get method test from main class
        Body methodBody = testMethod.retrieveActiveBody();// get method body as an object
        // Create directed-graph based on the method body
        UnitGraph graph = new BriefUnitGraph(methodBody);
        Iterator<Unit> unitIt = graph.iterator();
        // iterate over method body
        while (unitIt.hasNext()) {
            Unit unit = unitIt.next();
            System.out.println(unit);
            //iterate over UseBoxes of current Unit (statement)-- It is right side of statement
            for (ValueBox useBox : unit.getUseBoxes()) {
                // if Value of defBox is Local variable
                if (useBox.getValue() instanceof Local) {
                    Local useLocal = (Local) useBox.getValue();
                    System.out.println("Used:" + useLocal.getName());
                }
            }
            //iterate over DefBoxes of current Unit (statement)-- It is left side of statement
            for (ValueBox defBox : unit.getDefBoxes()) {
                // if Value of defBox is Local variable
                if (defBox.getValue() instanceof Local) {
                    Local defLocal = (Local) defBox.getValue();
                    System.out.println("Defined:" + defLocal.getName());
                }
            }
        }


    }
}