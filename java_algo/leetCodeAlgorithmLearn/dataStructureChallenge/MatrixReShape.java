package dataStructureChallenge;

class MatrixReShape {
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        if (mat.length * mat[0].length < r * c)
            return mat;
        int[][] answer = new int[r][c];
        int curRow = 0;
        int curCol = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                int target = mat[i][j];
                if (curCol >= c) {
                    curRow++;
                    curCol = 0;
                } else {
                    answer[curRow][curCol] = target;
                    curCol++;
                }
            }
        }
        return answer;
    }
}
