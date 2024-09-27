import java.io.*;

public class arithmetic_calculator {

    public static int calculator(String expression) {
    	stackArray<Integer> values = new stackArray<>();
		stackArray<Character> operators = new stackArray<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (ch == ' ') {
                continue;
            }


            if (Character.isDigit(ch)) {
            	StringBuilder sb = new StringBuilder();
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    sb.append(expression.charAt(i++));
                }
                values.push(Integer.parseInt(sb.toString()));
                i--; 
            }
            
 
            else if (isOperator(ch)) {
            	
            	if (ch=='(') {
                    int j = i;
                    int bracketCount = 0;
                    for (; j < expression.length(); j++) {
                        if (expression.charAt(j) == '(') bracketCount++;
                        if (expression.charAt(j) == ')') bracketCount--;
                        if (bracketCount == 0) break;
                    }
                    String subExpression = expression.substring(i + 1, j);
                    int subResult = calculator(subExpression);
                    values.push(subResult);
                    i = j;
                }
            	

            	else {while (!operators.isEmpty() && !isHigherPrecedence(ch, operators.top())) {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(ch);}
                
            }
        }

        // calculate the rest 
        while (!operators.isEmpty()) {
            values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    // return True if op2 have the higher precedence of op1
    public static boolean isHigherPrecedence(char op1, char op2) {
        int precedenceOp1 = getPrecedence(op1);
        int precedenceOp2 = getPrecedence(op2);
        return precedenceOp2 > precedenceOp1;
    }

    public static int getPrecedence(char op) {
        switch (op) {
            case '(':
            case ')':
                return 1; // Parentheses
            case '^':
                return 2; // Power function
            case '*':
            case '/':
                return 3; // Multiplication and division
            case '+':
            case '-':
                return 4; // Addition and subtraction
            case '>':
            case '≥':
            case '≤':
            case '<':
                return 5; // Comparison operators
            case '=':
            case '!':
                return 6; // Equality and inequality
            default:
                throw new IllegalArgumentException("Invalid operator: " + op);
        }
	}

    public static int applyOperator(char op, int b, int a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new UnsupportedOperationException("Cannot divide by zero");
                }
                return a / b;
            case '^':
                return (int) Math.pow(a, b);
            case '>':
                return a > b ? 1 : 0;
            case '<':
                return a < b ? 1 : 0;
            case '=':
                return a == b ? 1 : 0;
            case '!':
                return a != b ? 1 : 0;
            default:
                throw new UnsupportedOperationException("Unsupported operator: " + op);
        }
    }

    public static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^' || ch == '>' || ch == '<' || ch == '=' || ch == '!' || ch=='(';
    }

    public static void main(String[] args) {
        //String expression = "11 / 2 + 15 -( 11+5 )";
        //int result = evaluate(expression);
        //System.out.println("Result: " + result);
    	
    	String filePath = "arithmetic_expression.txt";
        String tempFilePath = "temp_arithmetic_expression.txt";
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFilePath))) {

            String line;
            while ((line = br.readLine()) != null) {
            	System.out.println(line);
                bw.write(line);
                bw.newLine();
                try {
                    int result = calculator(line);
                    System.out.println("Result: "+result);
                    bw.write("Result: " + result);
                } catch (Exception e) {
                    bw.write("Error in evaluating the expression: " + line);
                }
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Rename temp file to original file
        File originalFile = new File(filePath);
        File tempFile = new File(tempFilePath);

        if (originalFile.delete()) {
            tempFile.renameTo(originalFile);
        } else {
            System.out.println("Could not delete original file.");
        }
    	
    }
}
