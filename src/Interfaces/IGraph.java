package Interfaces;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: daltondick
 * Date: 10/22/13
 * Time: 10:03 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IGraph<V, E> {
    E addEdge(V sourceVertex, V targetVertex);
    boolean addEdge(V sourceVertex, V targetVertex, E e);
    boolean addVertex(V v);
    boolean containsEdge(E e);
    boolean containsVertex(V v);
    Set<E> edgeSet();
    Set<E> edgesOf(V v);
    E getEdge(V sourceVertex, V targeVertex);
    boolean removeEdge(E e);
    E removeEdge(V sourceVertex, V targetVertex);
    boolean removeVertex(V v);
}
