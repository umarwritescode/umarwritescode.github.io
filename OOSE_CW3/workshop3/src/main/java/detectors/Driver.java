package detectors ;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitor;

public class Driver {
	public static final String FILE_PATH = "src/main/java/detectors/Calculator.java";
	public CompilationUnit cu;
	public VoidVisitor<List<String>> methodVisitor;
	public List<String> collector;

	

	public static void main(String args[]) {
		try {
			CompilationUnit cu = JavaParser.parse(new FileInputStream(FILE_PATH));
			

			VoidVisitor<List<Breakpoints>> methodVisitor = new UselessControlFlowDetector();
			VoidVisitor<List<Breakpoints>> mVisitor = new RecursionDetector();
			
			List<Breakpoints> collector = new ArrayList<>();
			List<Breakpoints> recursionCollector = new ArrayList<>();
			
			
			methodVisitor.visit(cu, collector);
			System.out.println("Useless Control Flows: ");
			collector.forEach(m->{
				System.out.println(m);
			});
			
			
			
			mVisitor.visit(cu, recursionCollector);
			System.out.println("Polymorphic Recursions: ");
			recursionCollector.forEach(m->{
				System.out.println(m);
			});
			
			
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
