package graph;

import java.util.*;

public class DirectedGraph <V,E> implements Graph<V,E>{
    Map<V,Vertex<V,E>> vertexsMap=new HashMap<>();
    Set<Edge<V,E>> edgesSet=new HashSet<>();

    private class Vertex<V,E>{
        V value;
        Set<Edge<V,E>> inEdges=new HashSet<>();
        Set<Edge<V,E>> outEdges=new HashSet<>();
        public Vertex(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            Vertex<V,E> o1 = (Vertex<V,E>) o;
            return value.equals(o1.value);
        }

        @Override
        public int hashCode() {
            return value!=null?value.hashCode():0;
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "value=" + value +
                    ", inEdges=" + inEdges +
                    ", outEdges=" + outEdges +
                    '}';
        }
    }
    private class Edge<V,E>{
        Vertex<V,E> from;
        Vertex<V,E> to;
        E weight;

        public Edge(Vertex<V, E> from, Vertex<V, E> to) {

            this.from = from;
            this.to = to;
        }

        public Edge(Vertex<V, E> from, Vertex<V, E> to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int hashCode() {
            return from.hashCode()*31+to.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj==null){
                return false;
            }
            Edge<V,E> edge=(Edge<V,E>)obj;
            //不加权值，边相等就认定相等
            if (from.equals(edge.from) && to.equals(edge.to)){
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from.value +
                    ", to=" + to.value +
                    ", weight=" + weight +
                    '}';
        }
    }

    /**
     * 查看要增加的订单值在顶点Map里有没有
     * 有：
     *   覆盖
     * 没有：
     *   新增
     * @param v
     * @return
     */

    @Override
    public boolean addVertex(V v) {
        Vertex<V, E> veVertex = vertexsMap.get(v);
        if (Objects.nonNull(veVertex)){
            veVertex.value=v;
        }else {
            Vertex<V,E> newVertex=new Vertex<>(v);
            vertexsMap.put(v,newVertex);
        }
        return true;
    }

    @Override
    public boolean addEdge(V from, V to) {
        return addEdge(from,to,null);
    }

    @Override
    /**
     * 1、首先看vertexsMap存在不存在两个顶点，存在取出来不存在new一个放进去
     * 2、如果fromVertex该顶点的出边有要加入的这个边，移除，同时移除toVertex中的该入边及edgeSet里的该边，再重新加入。
     * 就是不管有没有都移除先，之后重新加
     */
    public boolean addEdge(V from, V to, E wight) {
        Vertex<V,E> fromVertex=vertexsMap.get(from);
        Vertex<V,E> toVertex=vertexsMap.get(to);
        if (fromVertex==null){
            fromVertex=new Vertex<>(from);
            vertexsMap.put(from,fromVertex);
        }
        if (toVertex==null){
            toVertex=new Vertex<>(to);
            vertexsMap.put(to,toVertex);
        }

        Edge<V,E> edge=new Edge<V,E>(fromVertex,toVertex,wight);
        if (fromVertex.outEdges.remove(edge)){
            toVertex.inEdges.remove(edge);
            edgesSet.remove(edge);
        }
        fromVertex.outEdges.add(edge);
        toVertex.inEdges.add(edge);
        edgesSet.add(edge);
        return true;
    }


    @Override
    public boolean contains(V value) {
        Vertex<V, E> veVertex = vertexsMap.get(value);
        return Objects.nonNull(veVertex);
    }

    @Override
    /**
     * 1、从找出vertexsMap要移除的顶点，并找出该顶点所有的入边和出边；
     * 2、分别遍历出边和入边，拿入边举例：遍历入边，直接移除所有入边，
     * 移除入边之前还要找到这条入边的from顶点A即另一个跟要删除的顶点有联系的顶点，将A顶点的所有出边找出来，
     * 遍历这些出边，将这些出边的to跟要删除的顶点比较，相等则删除该出边
     */
    public boolean remove(V value) {
        if (contains(value)){
            Vertex<V, E> exsitVertex = vertexsMap.get(value);
            Set<Edge<V, E>> exsitInEdges = exsitVertex.inEdges;
            Set<Edge<V, E>> exsitOutEdges = exsitVertex.outEdges;
            Iterator<Edge<V, E>> inIterator = exsitInEdges.iterator();
            while (inIterator.hasNext()){
                Edge<V, E> inEdge = inIterator.next();
                Vertex<V, E> from = inEdge.from;
                //该条边的另一个顶点
                Set<Edge<V, E>> outEdges = from.outEdges;
                Iterator<Edge<V, E>> otherOutiterator = outEdges.iterator();
                while (otherOutiterator.hasNext()){
                    Edge<V, E> outNext = otherOutiterator.next();
                    if (exsitVertex.equals(outNext.to)){
                        otherOutiterator.remove();
                    }
                }
                inIterator.remove();
                edgesSet.remove(inEdge);
            }
            Iterator<Edge<V, E>> outIterator = exsitOutEdges.iterator();
            while (outIterator.hasNext()){
                Edge<V, E> outEdge = outIterator.next();
                Vertex<V, E> to = outEdge.to;
                //该条边的另一个顶点
                Set<Edge<V, E>> inEdges = to.inEdges;
                Iterator<Edge<V, E>> otherinIterator = inEdges.iterator();
                while (otherinIterator.hasNext()){
                    Edge<V, E> inNext = otherinIterator.next();
                    if (inNext.from.equals(exsitVertex)){
                        otherinIterator.remove();
                    }
                }
                outIterator.remove();
                edgesSet.remove(outEdge);
            }
            vertexsMap.remove(value);
            return true;
        }
        return false;
    }

    @Override
    public int vertexSize() {
        return vertexsMap.size();
    }

    @Override
    /**
     * 从开始的节点开始，然后遍历所有子树根节点，这些子树的根节点算一层，
     * 遍历完再遍历这些子树根节点的子树根节点
     * 相当于树的层序遍历
     */
    public void bfs(V begin) {
        Vertex<V, E> veVertex = vertexsMap.get(begin);
        if (Objects.isNull(veVertex)){
            return;
        }
        Set<Vertex<V,E>> browsedVertex=new HashSet<>();
        Queue<Vertex<V,E>> queue=new LinkedList<>();
        queue.offer(veVertex);
        browsedVertex.add(veVertex);
        while (!queue.isEmpty()){
            Vertex<V, E> pollVertex = queue.poll();
            System.out.println(pollVertex.value);
            Set<Edge<V, E>> outEdges = pollVertex.outEdges;
            if (Objects.nonNull(outEdges)&&outEdges.size()>0){
                Iterator<Edge<V, E>> outEdgesIterator = outEdges.iterator();
                while (outEdgesIterator.hasNext()){
                    Edge<V, E> nextOutEdge = outEdgesIterator.next();
                    Vertex<V, E> to = nextOutEdge.to;
                    if (!browsedVertex.contains(to)){
                        queue.offer(to);
                        browsedVertex.add(to);
                    }
                }
            }
        }
    }



    @Override
    /**
     * 类似树的前序遍历
     */
    public void dfs(V begin) {
        Vertex<V, E> veVertex = vertexsMap.get(begin);
        if (Objects.isNull(veVertex)){
            return;
        }
        //存放遍历过的元素
        Set<Vertex<V,E>> browsedVertex=new HashSet<>();
        Stack<Vertex<V,E>> stack=new Stack<>();
        browsedVertex.add(veVertex);
        stack.push(veVertex);
        while (!stack.isEmpty()){
            Vertex<V, E> popVertex = stack.pop();
            Set<Edge<V, E>> outEdges = popVertex.outEdges;
            if (Objects.nonNull(outEdges)&&!outEdges.isEmpty()){
                for (Edge<V, E> outEdge : outEdges) {
                    if (!browsedVertex.contains(outEdge.to)){
                        //将from入栈的目的是为了起点的子节点（假如左边）的子节点全部遍历完之后能再回来遍历起点的右节点
                        //from是当前顶点，所以要打印的是to
                        stack.push(outEdge.from);
                        stack.push(outEdge.to);
                        System.out.println(outEdge.to.value);
                        browsedVertex.add(outEdge.to);
                        break;
                    }
                }
            }
        }

    }

    @Override
    public void topologicalSorting() {

    }

    private static Graph<Object, Double> directedGraph(Object[][] data) {
        Graph<Object, Double> graph = new DirectedGraph<>();
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
            }
        }
        return graph;
    }
    public static void main(String[] args) {
        Graph<Object, Double> objectDoubleGraph = directedGraph(Data.BFS_01);
        objectDoubleGraph.bfs("B");
        System.out.println("------");
        objectDoubleGraph.dfs("B");

        objectDoubleGraph.remove(3);
        System.out.println(objectDoubleGraph);
    }



}
