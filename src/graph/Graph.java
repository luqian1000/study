package graph;

public interface Graph<V,E> {
    /**
     * 增加顶点
     * @param v
     * @return
     */
    boolean addVertex(V v);

    /**
     * 增加边 不带权值
     * @param from
     * @param to
     * @return
     */
    boolean addEdge(V from,V to);
    /**
     * 增加边 带权值
     * @param from
     * @param to
     * @return
     */
    boolean addEdge(V from,V to,E wight);


    /**
     * 顶点是否包含某个值
     * @param value
     * @return
     */
    boolean contains(V value);

    /**
     * 移除某个顶点
     * @param value
     * @return
     */
    boolean remove(V value);

    /**
     * 顶点数量
     * @return
     */
    int vertexSize();

    /**
     * 广度优先
     * @param begin
     */
    void bfs(V begin);
    /**
     * 深度优先
     * @param begin
     */
    void dfs(V begin);
    /**
     * 拓扑排序
     */
    void topologicalSorting();


}
