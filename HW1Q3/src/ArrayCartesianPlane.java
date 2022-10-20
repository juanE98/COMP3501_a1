

/**
 * A 2D cartesian plane implemented as with an array. Each (x,y) coordinate can
 * hold a single item of type <T>.
 *
 * @param <T> The type of element held in the data structure
 */
public class ArrayCartesianPlane<T> implements CartesianPlane<T> {

    private int minimumX;
    private int maximumX;
    private int minimumY;
    private int maximumY;
    private T[][] array;

    /**
     * Constructs a new ArrayCartesianPlane object with given minimum and
     * maximum bounds.
     *
     * Note that these bounds are allowed to be negative.
     *
     * @param minimumX A new minimum bound for the x values of
     *         elements.
     * @param maximumX A new maximum bound for the x values of
     *         elements.
     * @param minimumY A new minimum bound for the y values of
     *         elements.
     * @param maximumY A new maximum bound for the y values of
     *         elements.
     * @throws IllegalArgumentException if the x minimum is greater
     *         than the x maximum (and resp. with y min/max)
     */
    public ArrayCartesianPlane(int minimumX, int maximumX, int minimumY,
            int maximumY) throws IllegalArgumentException {
        this.minimumX = minimumX;
        this.maximumX = maximumX;
        this.minimumY = minimumY;
        this.maximumY = maximumY;
        if(this.minimumX < this.maximumX && this.minimumY < this.maximumY){
            this.array =
                    (T[][])new Object[(this.maximumX-this.minimumX)+1]
                            [(this.maximumY-this.minimumY)+1];
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void add(int x, int y, T element) throws IllegalArgumentException {
        if (x<=this.maximumX && x >= this.minimumX &&
                y<=this.maximumY && y >= this.minimumY) {
            this.array[x][y] = element;
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    @Override
    public T get(int x, int y) throws IndexOutOfBoundsException {
        if(x<=this.maximumX && x >= this.minimumX &&
                y<=this.maximumY && y >= this.minimumY){
            return this.array[x][y];
        }
        else{
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean remove(int x, int y) throws IndexOutOfBoundsException{
        if(this.get(x,y) != null){
            this.array[x][y] = null;
            return true;
        }
        return false;
    }

    @Override
    public void clear(){
        for(int i=0; i < this.maximumX-this.minimumX; i++){
            for(int j=0; j < this.maximumY - this.minimumY; j++){
                this.array[i][j] = null;
            }
        }
    }

    @Override
    public void resize(int newMinimumX, int newMaximumX, int newMinimumY,
                       int newMaximumY) throws IllegalArgumentException {

        if(newMinimumX > newMaximumX || newMinimumY > newMaximumY) {
            throw new IllegalArgumentException();
        }

        if (newMaximumX >= this.maximumX && newMinimumX <= this.minimumX &&
                    newMaximumY >= this.maximumY && newMinimumY <= this.minimumY)
        {
            this.array = this.copyArray(this.array, newMinimumX, newMaximumX,
                    newMinimumY,
                    newMaximumY);
            this.maximumX = newMaximumX;
            this.minimumX = newMinimumX;
            this.maximumY = newMaximumY;
            this.minimumY = newMinimumY;
        }

        else{
            throw new IllegalArgumentException();
        }
    }

    /**
     * Helper method to return a copy of the modified resized array with the
     * contents of the original array.
     * @param arrayOriginal original array
     * @param newMinimumX A new minimum bound for the x values of
     *         elements.
     * @param newMaximumX A new maximum bound for the x values of
     *         elements.
     * @param newMinimumY A new minimum bound for the y values of
     *         elements.
     * @param newMaximumY A new maximum bound for the y values of
     *         elements.
     * @return newArray New resized array.
     */
    private T[][] copyArray(T[][] arrayOriginal,int newMinimumX,
                           int newMaximumX,
                           int newMinimumY, int newMaximumY ){
        T[][]newArray =
        (T[][])new Object[(newMaximumX-newMinimumX)+1]
                [(newMaximumY-newMinimumY)+1];
        for(int i=0; i < (this.maximumX-this.minimumX); i++){
            for (int j=0; j < (this.maximumY-this.minimumY); j++ ){
                 newArray[i][j] = this.get(i,j);
            }
        }
        return newArray;
    }

}

