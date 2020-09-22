package detectors;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.SwitchEntry;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class UselessControlFlowDetector extends VoidVisitorAdapter <List<Breakpoints>> {
	
	String methodName;
	String className;
	
	 public void visit(MethodDeclaration md, List<Breakpoints> collector) {
		 
		 methodName = md.getNameAsString();
		 super.visit(md, collector);
		 
	 }
	 
	 public void visit(ClassOrInterfaceDeclaration md, List<Breakpoints> collector) {
		 
		 className = md.getNameAsString();
		 super.visit(md, collector);
		 
	 }
	
	 @Override
	 public void visit(IfStmt n, List<Breakpoints> collector) {
		 super.visit(n, collector);
		 
		 int begin = n.getRange().get().begin.line;
		 int end = n.getRange().get().end.line;

		 
		 if (n.getThenStmt().getChildNodes().isEmpty()) { // If 'Then' statements' child nodes are empty, then its an empty block
			 Breakpoints uselessControl = new Breakpoints(className, methodName, begin, end); // Hence useless
			 collector.add(uselessControl);

		 }  
	 }
	 
	 public void visit(WhileStmt n, List<Breakpoints> collector) {
		 super.visit(n, collector);
		 int begin = n.getRange().get().begin.line;
		 int end = n.getRange().get().end.line;

		 
		 if (n.getBody().getChildNodes().isEmpty()) {
			 Breakpoints uselessControl = new Breakpoints(className, methodName, begin, end);
			 collector.add(uselessControl);

		 }
		 
	 }
	 
	 public void visit(ForStmt n, List<Breakpoints> collector) {
		 super.visit(n, collector);
		 int begin = n.getRange().get().begin.line;
		 int end = n.getRange().get().end.line;
		 


		 
		 if (n.getBody().getChildNodes().isEmpty()) {
			Breakpoints uselessControl = new Breakpoints(className, methodName, begin, end);
			collector.add(uselessControl);
			 
		 }		  
	 } 
	 
	 public void visit(SwitchEntry n, List<Breakpoints> collector) {
		 super.visit(n, collector);
		 int begin = n.getRange().get().begin.line;
		 int end = n.getRange().get().end.line;
		 

		 List<Node> children = n.getChildNodes();
		 
		 boolean flag = false; // Set flag to false initially
		 
		 for (Node child : children) {
			 if (child instanceof ExpressionStmt) { // If a child node is an expression statement, then switch entry is not useless
				 flag = true; // Hence, set flag to true.
				 }
		 }
		 
		 if (flag == false) {
			 Breakpoints uselessControl = new Breakpoints(className, methodName, begin, end);
			 collector.add(uselessControl);
		 }

		 

	 }
	 
	 

	 
	 

}
