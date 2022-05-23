package william.leetcode.array;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/21 下午3:12
 * <p>
 * https://leetcode.cn/problems/rotate-image/
 */
public class 旋转图像_48 {
    //对矩阵进行两次翻转
    //数组维度=N
    //第一次:水平翻转 原坐标=(i,j) 新坐标=(N-1-i,j)
    //第二次:沿主对角线翻转 原坐标=(i,j) 新坐标=(j,i)
    //时间复杂度O(N*N) 需要遍历2层数组
    //空间复杂度O(1)
    public void rotate(int[][] matrix) {
        //边界条件
        if (matrix == null || matrix.length == 0) {
            return;
        }

        int N = matrix.length;

        //第一次翻转:水平翻转 原坐标=(i,j) 新坐标=(N-1-i,j)
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < N; j++) {
                swap(matrix, i, j, N - 1 - i, j);
            }
        }

        //第二次翻转:沿主对角线翻转 原坐标=(i,j) 新坐标=(j,i)
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                swap(matrix, i, j, j, i);
            }
        }
    }

    //交换矩阵的matrix[i1][j1]和matrix[i2][j2]两个元素
    private void swap(int[][] matrix, int i1, int j1, int i2, int j2) {
        int tmp = matrix[i1][j1];
        matrix[i1][j1] = matrix[i2][j2];
        matrix[i2][j2] = tmp;
    }
}
