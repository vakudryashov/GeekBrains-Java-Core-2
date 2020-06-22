package Lesson_02;

public class App {
    public static void main(String[] args) {
        String[][][] arr = {
                {
                    {"1", "2", "3", "4"},
                    {"1", "2", "3", "a4"},
                    {"1", "2", "5", "4"}},

                {
                    {"1", "2", "3", "4"},
                    {"1", "2"},
                    {"1", "2", "3", "4a"},
                    {"1", "2", "5", "4"}},

                {
                    {"1", "2", "3", "4"},
                    {"1", "2", "3", "4"},
                    {"1", "2", "3", "4a"},
                    {"1", "2", "5", "4"}},

                {
                    {"1", "2", "3", "4"},
                    {"1", "2", "3", "4"},
                    {"1", "2", "3", "4"},
                    {"1", "2", "5", "4"}},
        };

        for (int i=0; i<arr.length; i++) {
            System.out.printf("%n* Обработка массива №%d.%n",i+1);
            try {
                System.out.printf("Результат суммирования элементов массива: %d%n%n", arraySum(arr[i]));
            }catch(MyArraySizeException e){
                e.printStackTrace();
                String subString = e.getRow() < 0 ? "строк" : String.format("столбцов в %d-й строке",e.getRow()+1);
                System.out.printf("Массив задан неправильно. Количество %s (%d) не равно ожидаемому (%d)%n",
                        subString, e.getRealSize(), e.getExpectedSize());
            }catch(MyArrayDataException e){
                e.printStackTrace();
                System.out.printf("Строку \"%s\" в ячейке [%d, %d] невозможно преобразовать в целое число!%n",
                    arr[i][e.getRow()][e.getCol()], e.getRow(), e.getCol());
            }
        }

    }

    public static int arraySum(String[][] arr) throws MyArraySizeException, MyArrayDataException{
        final int SIZE = 4;
        int sum = 0;
        int rowNum = arr.length;

        if (rowNum != SIZE){
            throw new MyArraySizeException(SIZE,rowNum);
        }
        for (int i=0; i<rowNum; i++){
            int colNum = arr[i].length;
            if (colNum != SIZE){
                throw new MyArraySizeException(SIZE,colNum,i);
            }
            for (int j=0; j<arr.length; j++){
                try{
                    sum += new Integer(arr[i][j]);
                } catch(NumberFormatException e){
                    throw new MyArrayDataException(i,j);
                }
            }
        }
        return sum;
    }

}

class MyArraySizeException extends Exception{
    private int expectedSize;
    private int realSize;
    private int row = -1;

    public MyArraySizeException(int expectedSize, int realSize){
        super(String.format("Неправильный размер массива! " +
                "Ожидалось строк: %d. Фактически строк: %d",expectedSize, realSize));
        this.expectedSize = expectedSize;
        this.realSize = realSize;
    }

    public MyArraySizeException(int expectedSize, int realSize, int row){
        super(String.format("Неправильный размер массива в строке %d! " +
                "Ожидалось столбцов: %d. Фактически столбцов: %d",row, expectedSize, realSize));
        this.expectedSize = expectedSize;
        this.realSize = realSize;
        this.row = row;
    }
    public int getExpectedSize() {
        return expectedSize;
    }

    public int getRealSize() {
        return realSize;
    }

    public int getRow() {
        return row;
    }
}

class MyArrayDataException extends Exception{
    private int row;
    private int col;

    public MyArrayDataException(int row, int col){
        super(String.format("Неправильный формат числа в ячейке [%d, %d]",row,col));
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}