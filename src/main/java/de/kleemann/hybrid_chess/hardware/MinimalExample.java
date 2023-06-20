package de.kleemann.hybrid_chess.hardware;

/**
 * Dies ist ein Beispiel wie der HardwareController verwendet wird.
 * @author Fabian Siemens
 *
 */
//public class MinimalExample implements HardwareListener {
public class MinimalExample {
	/*
	private static int buttonPressCount = 0;
	
	public static void main(String[] args) throws Exception {
		
		HardwareController controller = HardwareController.getInstance();
		controller.addListener(new MinimalExample());
		
		while(buttonPressCount < 5) {
			try {
				Thread.sleep(500);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		controller.shutdown();
	}
	
	private static void printMatrix(boolean[][] matrix) {
		 System.out.println("Input  | A B C D E F G H  <-- Output");
		  for(int row = 0; row < 8; row++) {
			  System.out.print("     ");
			  System.out.print(row+1);
			  System.out.print(" | ");
			  for(int col = 0; col < 8; col++){
				  System.out.print(matrix[col][row] ? 1 : 0);
				  System.out.print(" ");
			  }
			  System.out.print("\n");
		  }
		  System.out.print("\n");
	}

	@Override
	public void onConfirmButtonPressed(HardwareController controller) {
		boolean[][] field = controller.readField();
		printMatrix(field);
		buttonPressCount++;
	}

	 */
}
