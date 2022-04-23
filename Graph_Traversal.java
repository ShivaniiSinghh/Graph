import java.util.*;

public class Graph_Traversal {

    static Map<Integer, LinkedList<Integer>> obj;
    public Graph_Traversal()
    {
        obj = new HashMap<>();
    }

    public static void main(String[] args) {
        Graph_Traversal gf = new Graph_Traversal();
        gf.addedge(1,2,true);
        gf.addedge(2,4,true);
        gf.addedge(3,1,true);
        gf.addedge(4,3,true);
        gf.addedge(5,3,true);
        gf.addedge(6,5,true);
        System.out.println("Graph Traversal:");
        gf.BFS_traversal(6);
        System.out.println("Nodes and their Connection:");
        gf.print();
        System.out.println("Source, Destination and Distance:");
        gf.ssspwithbfs(4);

    }

    public void addedge(int v1, int v2, boolean bidirectional)
    {
        LinkedList<Integer> v1neighbour = obj.getOrDefault(v1,new LinkedList<>());
        v1neighbour.add(v2);
        obj.put(v1,v1neighbour);

        if(bidirectional){
            LinkedList<Integer> v2neighbour = obj.getOrDefault(v2, new LinkedList<>());
            v2neighbour.add(v1);
            obj.put(v2,v2neighbour);
        }
    }

    public void BFS_traversal(int source)
    {
        Queue<Integer> bfs = new Queue<>();
        bfs.enqueue(source);
        Set<Integer> visited = new HashSet<>();
        visited.add(source);

        while(!bfs.isEmpty())
        {
            int front = bfs.dequeue();
            System.out.println(front);
            LinkedList<Integer> neighbour = obj.get(front);
            for (int res:neighbour) {
                if(!visited.contains(res))
                {
                    bfs.enqueue(res);
                    visited.add(res);
                }
            }
        }
    }
    //  method to find the shortest Path [SSSP-Single source Shortest Path]
    public void ssspwithbfs(int source)
    {
        Queue<Integer> bfs = new Queue<>();
        bfs.enqueue(source);
        Map<Integer,Integer> dis = new HashMap<>();
        for (int vertex: obj.keySet())
            dis.put(vertex,Integer.MAX_VALUE);

        dis.put(source,0);
        while(!bfs.isEmpty())
        {
            int front = bfs.dequeue();
            LinkedList<Integer> neighbourList = obj.get(front);
            for (int neighbour:neighbourList) {
                if(dis.get(neighbour) == Integer.MAX_VALUE)
                {
                    bfs.enqueue(neighbour);
                    int distance = dis.get(front)+1;
                    dis.put(neighbour,distance);
                }
            }
            System.out.println(source+"--"+front+"->"+dis.get(front));
        }
    }

    public void print() {
        for (Map.Entry<Integer, LinkedList<Integer>> e : obj.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
    }
}