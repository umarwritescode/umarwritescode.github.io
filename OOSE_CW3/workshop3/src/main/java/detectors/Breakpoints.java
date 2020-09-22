package detectors;

import java.awt.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;

public class Breakpoints {
	private String className;
	private String methodName;
	private int startLine;
	private int endLine;

	public Breakpoints(String className, String methodName, int startLine, int endLine) {
		// TODO Auto-generated constructor stub
		this.className = className;
		this.methodName = methodName;
		this.startLine = startLine;
		this.endLine = endLine;
		
	}
	
	public String toString() {
		return "Class Name = " + className + ", Method Name = " + methodName + ", Start line = "
				+ startLine + ", End line = " + endLine;
	}
	/*
	private static final String FILE_PATH = "src/main/java/detectors/Calculator.java";
	
	public static void main(String args []) {
		try {
			CompilationUnit cu = JavaParser.parse(new FileInputStream(FILE_PATH));
			
		}
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	*/
	
	

}
