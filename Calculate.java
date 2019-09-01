package Calculator;

import java.util.HashMap;
import java.util.Stack;

public class Calculate {
	private Stack<Character> operation;
	private Stack<Integer> nums;
	private HashMap<Character,Integer> map;
	
	public Calculate() {
		operation = new Stack<>();
		nums = new Stack<>();
		map = new HashMap<>();
		map.put('(',0);
		map.put('+', 1);
		map.put('-', 1);
		map.put('*', 2);
		map.put('/', 2);
		map.put('%', 2);
		map.put('^', 3);
		map.put('√', 3);
	}
	
	public String calculate(String form) {  
		if (checkError(form)) return "FormulaFormatError!";
		for (int i = 0;i < form.length();i++) {
			char c = form.charAt(i);
			if (ifNum(c)) {
				int start = i;
				while (i < form.length() && ifNum(form.charAt(i))) {
					i++;
				}
				nums.push(Integer.valueOf(form.substring(start,i--)));
			}
			else {
				if (operation.isEmpty()) {
					if (c == '(' && form.charAt(i + 1) == '-') {
						i += 2;
						int start = i;
						while (i < form.length() && ifNum(form.charAt(i))) {
							i++;
						}
						nums.push(Integer.valueOf("-" + form.substring(start,i)));
					}
					else {
						operation.push(c);
					}
				}
				else {
				if (c == '(') {
					if (form.charAt(i + 1) == '-') {
						i += 2;
						int start = i;
						while (i < form.length() && ifNum(form.charAt(i))) {
							i++;
						}
						nums.push(Integer.valueOf("-" + form.substring(start,i)));
					}
					else {
						operation.push(c);
					}				}
				else if (c == ')') {
					while (!operation.isEmpty() && operation.peek() != '(') {
						operate(operation.pop(),nums);
					}
					operation.pop();
				}
				else {//如果是括号以外的符号
					while (!operation.isEmpty() && map.get(c) <= map.get(operation.peek())) {
						operate(operation.pop(),nums);
					}
					operation.push(c);
				}
			}
		}
		}
		while (!operation.isEmpty()) {
			operate(operation.pop(),nums);
		}
		return String.valueOf(nums.pop());
	}
	
	public static boolean ifNum(char c) {
		return (c <= '9' && c >= '0');
	}
	
	public void operate(char c,Stack<Integer> nums) {
		int rig = nums.pop();
		int lef = nums.pop();
		switch(c) {
		case '+' :
			nums.push(lef + rig);
			break;
		case '-' :
			nums.push(lef - rig);
			break;
		case '*' :
			nums.push(lef * rig);
			break;
		case '/' :
			nums.push(lef / rig);
			break;
		case '^' :
			nums.push((int)Math.pow(lef, rig));
			break;
		}
	}
	
	public boolean checkError(String form) {//return true when error or errors exist
		//Check if the parenthesis are right and if elements except numbers and calculate signs appeared.
		Stack<Character> stack = new Stack<>();
		for (int i = 0;i < form.length();i++) {
			char c = form.charAt(i);
			if (c != '(' && c != ')') {
				if (!Character.isDigit(c)) {
					if (map.get(c) == null) {
						return true;
					}
				}
			}
			else {
				if (c == '(') {
					stack.push('(');
				}
				else {
					if (!stack.isEmpty()) {
						stack.pop();
					}
					else {
						return true;
					}
				}
			}
		}
		return !stack.isEmpty();
	}
}
