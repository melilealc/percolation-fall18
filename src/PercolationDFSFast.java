
public class PercolationDFSFast extends PercolationDFS{

	public PercolationDFSFast(int size) {
		super(size);
		
	}
	@ Override 
	/**
	 * Changes method so that it only calls dfs once, making it much more efficient 
	 */
	public void updateOnOpen(int row, int col) {
		if(row == 0) dfs(row, col);
		else if(isFull(row-1, col) || isFull(row,col-1) || isFull(row+1, col) || isFull(row, col+1)) {
			dfs(row, col);
		 }
	}
	

}
