package br.edu.ufba.softvis.visminer.metric;

import java.util.List;

import br.edu.ufba.softvis.visminer.annotations.MetricAnnotation;
import br.edu.ufba.softvis.visminer.ast.MethodDeclaration;
import br.edu.ufba.softvis.visminer.ast.Statement;
import br.edu.ufba.softvis.visminer.constant.MetricType;
import br.edu.ufba.softvis.visminer.constant.MetricUid;

@MetricAnnotation(name = "Ciclomatic Complexity", description = "Cyclomatic complexity is a software metric, used to indicate the complexity of a"
		+ " program. It is a quantitative measure of the number of linearly independent paths through a"
		+ " program's source code.", acronym = "CC", type = MetricType.SIMPLE, uid = MetricUid.CC)
public class CCMetric extends MethodBasedMetric {

	@Override
	public void calculate(List<MethodDeclaration> methods) {
		for (MethodDeclaration method : methods) {
			int cc = calculate(method);
			persistence.postMetricValue(method.getId(), String.valueOf(cc));
		}
	}

	private int calculate(MethodDeclaration method) {
		if (method.getStatements() == null) {
			return 1;
		}

		int cc = 1;
		for (Statement statement : method.getStatements()) {
			switch (statement.getNodeType()) {
			case IF:
				cc += calculateExpression(statement.getExpression());
				break;
			case SWITCH_CASE:
				cc += calculateExpression(statement.getExpression());
				break;
			case FOR:
				cc += calculateExpression(statement.getExpression());
				break;
			case DO:
				cc += calculateExpression(statement.getExpression());
				break;
			case WHILE:
				cc += calculateExpression(statement.getExpression());
				break;
			default:
				break;
			}
		}

		return cc;
	}
	
	private int calculateExpression(String expression) {
		int cc = 1;
		
		char[] chars = expression.toCharArray();
		for (int i = 0; i < chars.length - 1; i++) {
			char next = chars[i];
			if ((next == '&' || next == '|') && (next == chars[i + 1])) {
				cc++;
			}
		}
		
		return cc;
	}

}
