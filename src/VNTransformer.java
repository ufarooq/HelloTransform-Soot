import soot.*;
import soot.toolkits.graph.BriefUnitGraph;
import soot.toolkits.graph.UnitGraph;
import java.util.Iterator;
import java.util.Map;

public class VNTransformer extends SceneTransformer {

    @Override
    protected void internalTransform(String arg0, Map arg1) {
        SootClass helloWorld = Scene.v().getMainClass(); // get main class

        System.out.println("Methods: " + helloWorld.getMethodCount()); // get methods count for class
        SootMethod valueNumbering = helloWorld.getMethodByName("test");
        // Create graph based on the method
        UnitGraph graph = new BriefUnitGraph(valueNumbering.getActiveBody());
        Iterator<Unit> unitIt = graph.iterator();
        while (unitIt.hasNext()) {
            Unit unit = unitIt.next();
            System.out.println(unit);
            for (ValueBox useBox : unit.getUseBoxes()) {
                if (useBox.getValue() instanceof Local) {
                    Local useLocal = (Local) useBox.getValue();
                    System.out.println("Used:" + useLocal.getName());
                }
            }
            for (ValueBox defBox : unit.getDefBoxes()) {
                if (defBox.getValue() instanceof Local) {
                    Local defLocal = (Local) defBox.getValue();
                    System.out.println("Defined:" + defLocal.getName());
                }
            }
        }


    }
}