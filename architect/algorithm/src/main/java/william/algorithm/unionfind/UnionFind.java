package william.algorithm.unionfind;

import org.springframework.util.Assert;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/19 13:13
 * @Description:使用数组实现的并查集
 */
public class UnionFind {
    private int[] ids;      //使用数组存储每个元素所属集合的编号
    private int size;

    public UnionFind(int capacity){
        ids = new int[capacity];
        size = capacity;

        //初始化，每一个ids[i]指向自己，没有合并的元素
        for (int i = 0;i < capacity;i++){
            ids[i] = i;
        }
    }

    //查找元素p所属的集合编号,时间复杂度O(1)
    public int find(int p){
        Assert.isTrue((p >= 0 && p < size),"待查询的元素非法");
        return ids[p];
    }

    //判断元素p和q是否属于同一个集合,时间复杂度O(1)
    public boolean isConnected(int p,int q){
        return (find(p) == find(q));
    }

    //合并元素q和q所在的集合,时间复杂度O(n)
    public void union(int p,int q){
        int idP = find(p);
        int idQ = find(q);
        if(idP == idQ){
            return;
        }

        //合并过程需要遍历一次所有元素，将两个元素的所属集合编号合并
        for (int i = 0;i < size;i++){
            if (ids[i] == idP){
                ids[i] = idQ;
            }
        }
    }
}
