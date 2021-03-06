import java.util.LinkedList;
import java.util.Queue;

public class PercolationBFS extends PercolationDFSFast{

	public PercolationBFS(int size) {
		super(size);	
	}

	@Override
	/**
	 * DFS method uses a queue to make a cell full and check and fill all those cells around it that are open
	 * This method is more efficient because it uses a queue instead of recursion
	 */
	public void dfs(int row, int col) {
		int size = myGrid.length;
	    int[] rowDelta = {-1,1,0,0};
	    int[] colDelta = {0,0,-1,1};
	    Queue<Integer> queuePerc = new LinkedList<Integer>();     
	    myGrid [row][col] = FULL; 
	    queuePerc.add(row*size + col );
	    while (queuePerc.size() != 0){
	    	int value = queuePerc.remove();
	    	for(int k=0; k < rowDelta.length; k++){
	    		row = value/size + rowDelta[k];
	            col = value%size + colDelta[k];
	            if (inBounds(row,col) && isOpen(row, col) && ! isFull(row, col)){
	            	queuePerc.add(row*size + col);
	                myGrid [row][col]= FULL;
	                    
	               	}
	            }
	        }
		}
}


