
public class PercolationUF implements IPercolate {
	boolean [][] myGrid; 
	int myOpencells;
	IUnionFind myFinder; 
	private final int VTOP; 
	private final int VBOTTOM;
	public PercolationUF(int size, IUnionFind finder) {
		myGrid = new boolean[size][size]; 
		finder.initialize(size*size + 2);
		myFinder = finder; 
		VTOP = size*size; 
		VBOTTOM = size*size + 1; 
	}

	@Override
	public void open(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		if(row == 0) {
			myFinder.union(VTOP, row*myGrid.length + col);
		}
		if(row == myGrid.length-1) {
			myFinder.union(VBOTTOM, row*myGrid.length + col);
		}
		if(myGrid[row][col]) {
			return;
		}
		myOpencells++;
		myGrid[row][col] = true;
		int[] rowDelta = {-1,1,0,0};
		int[] colDelta = {0,0,-1,1};
		for(int k=0; k < rowDelta.length; k++){
    		int nrow = (row + rowDelta[k]);
            int ncol = (col + colDelta[k]);
            if (inBounds(nrow,ncol) && isOpen(nrow, ncol)){
            	myFinder.union(nrow*myGrid.length+ ncol, row*myGrid.length + col);
               	}
		}
	}

	@Override
	public boolean isOpen(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myGrid[row][col]; 
		
	}

	private boolean inBounds(int row, int col) {
			if (row < 0 || row >= myGrid[0].length) return false;
			if (col < 0 || col >= myGrid[1].length) return false;
			return true;
	}

	@Override
	public boolean isFull(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myFinder.connected(VTOP, row*myGrid.length +col);
	}

	@Override
	public boolean percolates() {
		return myFinder.connected(VTOP, VBOTTOM);
	}

	@Override
	public int numberOfOpenSites() {
		return myOpencells;
	}
	
	

}
