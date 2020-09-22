package detectors;

import java.util.List;
import java.util.Set;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class RecursionDetector extends VoidVisitorAdapter <List<Breakpoints>>{
	
	int start;
	int end;
	String className;
	String methodName;
	
	@Override
	 public void visit(ClassOrInterfaceDeclaration classDec, List<Breakpoints> recursionCollector) {
		 className = classDec.getNameAsString();
		 super.visit(classDec, recursionCollector);
	 }
	
	@Override
	public void visit(MethodDeclaration mdDec, List<Breakpoints> recursionCollector) {
		
		
		methodName = mdDec.getNameAsString();
		start = mdDec.getRange().get().begin.line;
		end = mdDec.getRange().get().end.line;
		super.visit(mdDec, recursionCollector);
	}
	
	@Override
	public void visit(MethodCallExpr mdCall, List<Breakpoints> recursionCollector) {
		super.visit(mdCall, recursionCollector);
		if (mdCall.getNameAsString().equals(methodName)) { // If the method call expression name is the same as the parent method name
			recursionCollector.add(new Breakpoints(className, methodName, start, end)); // We have polymorphic recursion
			
		
		}
	
	}
}
	

