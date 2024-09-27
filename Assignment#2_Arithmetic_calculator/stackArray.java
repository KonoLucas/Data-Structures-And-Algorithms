public class stackArray<T> {

	private T[] stackArray;   // since we do not know the exact data type, so using <T>
	private int f=0;       // the top available index, also reference the number  of item in the stack
	private int size=0;
	
	public stackArray() {
		size = 1; // initial size of array to one;
        stackArray = (T[]) new Object[size];
	}
	
	public void push(T element) {
        if (f == size) {doubleSize();}
        stackArray[f++] = element;
    }
	
	public T pop() {
		if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T element = stackArray[(--f)];
        stackArray[f] = null; 
        return element;
	}
	
	public boolean isEmpty() {return f == 0;}
	

	public void doubleSize() {
		size = (size == 0) ? 1 : size * 2;
		T[] newList = (T[]) new Object[size];
        
		for (int i = 0; i < f; i++) {
            newList[i] = stackArray[i];
        }
        stackArray = newList;        
    }
	
	public int size() {return f;}
	
	public T top() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stackArray[f - 1];
    }
	
	public static void main(String[] args) {
		stackArray<Integer> stack = new stackArray<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop()); // 3
        System.out.println(stack.top()); // 2
        System.out.println(stack.isEmpty()); // false
        stack.push(11);
        System.out.println(stack.top()); //11
        System.out.println(stack.size()); // 3
    }

}