# HelloTransform-Soot
Getting started with Soot Transformer and Packs

## Setup Project in IDE
1. Create an empty Java project and include pre-compile Soot as jar located under /libs. ([For IntelliJ follow here](#IntelliJ-Import-instructions) and continue to step 2)
2. Add ***Main.java*** located under /src to your project. 
3. Add ***VNTransformer.java*** located under /src to your project.
4. Download ***Test.class*** located under /inputs and provide path in Main.java, where "inputs" is a directory containing .class file. 
```java
String classPath =  "inputs";
```
5. Configure Soot as it mentioned in Part 1.
6. Inside main method ***Main.java***, create object for our ***VNTransformer*** class.
```java
VNTransformer mVNTransformer = new VNTransformer();
```
7. Then, an object for Transform. 
```java
Transform mVNTransform = new Transform("wjtp.valNumbering", mVNTransformer);
//First Argument is name of Transform, second argument is an object of our Transformer
```
8. Add Transform to the whole-jimple transformation pack.
```java
PackManager.v().getPack("wjtp").add(mVNTransform);
```
9. Next, here are pointers for Soot APIs used in ***VNTransformer***  class.
10. Get Main class as a ***SootClass*** Object.
```java
SootClass mainClass = Scene.v().getMainClass();
```
11. Get ***test*** method as a ***SootMethod*** Object and get method body.
```java
SootMethod testMethod = mainClass.getMethodByName("test");
Body methodBody = testMethod.retrieveActiveBody();
```
12. Create Directed graph object for method body.
```java
UnitGraph graph = new BriefUnitGraph(methodBody);
```
13. Next, iterate over the UnitGraph object and go through the statements. 
```java
Iterator<Unit> unitIt = graph.iterator();  
while (unitIt.hasNext()) { 
	Unit unit = unitIt.next();
	...
}
```
14. Following API helps to get Used Boxes of current Unit (statement) -- These are the things appearing right hand side of the statement. ***Local*** is Soot's representation of local variables of a method. 
```java
for (ValueBox useBox : unit.getUseBoxes()) {  
    // if Value of defBox is Local variable  
  if (useBox.getValue() instanceof Local) {  
        Local useLocal = (Local) useBox.getValue();  
		System.out.println("Used:" + useLocal.getName());  
  }  
}
```
16. Following API helps to get Defined Boxes of current Unit (statement) -- These are the things appearing left hand side of the statement.
```java
for (ValueBox defBox : unit.getDefBoxes()) {  
    // if Value of defBox is Local variable  
  if (defBox.getValue() instanceof Local) {  
        Local defLocal = (Local) defBox.getValue();  
		System.out.println("Defined:" + defLocal.getName());  
  }  
}
```


## IntelliJ Import instructions
1. Import project to IntelliJ idea (You might need to configure dependencies, Click File → Project Structure → Modules → click + → JARs or directories → Browse the jar file in lib directory) 
