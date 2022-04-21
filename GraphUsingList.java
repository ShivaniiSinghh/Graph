import java.util.*;

public class GraphUsingList {
    Map<Integer, ArrayList<Integer>> obj=new HashMap<>();

    // Graph implementation method
    public void add_edge(int v1, int v2, boolean bidirectional){
        ArrayList<Integer> v1neighbours=obj.getOrDefault(v1,new ArrayList<>());
        v1neighbours.add(v2);
        obj.put(v1,v1neighbours);
        if(bidirectional){
            ArrayList<Integer> v2neighbours=obj.getOrDefault(v2,new ArrayList<>());
            v2neighbours.add(v1);
            obj.put(v2,v2neighbours);
        }
    }
    // BFS Traversal method
    public void BFSTraversal(int source){
        Queue<Integer> q=new LinkedList<>();
        q.add(source);
        Set<Integer> visited=new HashSet<>();
        visited.add(source);
        while(!q.isEmpty()){
            int front=q.poll();
            System.out.print(front+" ");
            List<Integer> neighbour=obj.get(front);
            for(int res:neighbour){
                if(!visited.contains(res)){
                    q.add(res);
                    visited.add(res);
                }
            }
        }
    }

    // SSSP --> Single source shortest path method
    public void SSSP(int source){
        Queue<Integer> q=new LinkedList<>();
        q.add(source);
        Map<Integer,Integer> dis=new HashMap<>();
        for(Integer vertex:obj.keySet()){
            dis.put(vertex,Integer.MAX_VALUE);
        }
        dis.put(source,0);
        while(!q.isEmpty()){
            int front=q.poll();
            ArrayList<Integer> neighbourList=obj.getOrDefault(front,new ArrayList<>());
            for(Integer neighbour:neighbourList){
                if(dis.get(neighbour)==Integer.MAX_VALUE){
                    q.add(neighbour);
                    int distance=dis.get(front)+1;
                    dis.put(neighbour,distance);
                }
            }
        }
        for (Map.Entry<Integer,Integer>res: dis.entrySet())
            System.out.println(res.getKey()+"-->"+res.getValue());
    }
    // Main method
    public static void main(String[] args){
        GraphUsingList g=new GraphUsingList();
        g.add_edge(1,2,true);
        g.add_edge(2,4,true);
        g.add_edge(3,4,true);
        g.add_edge(3,1,true);
        g.add_edge(3,5,true);
        g.add_edge(5,6,true);
        for(Map.Entry<Integer, ArrayList<Integer>> res:g.obj.entrySet()){
            System.out.print(res.getKey()+": ");
            System.out.println(res.getValue());
        }
        System.out.println("Various roots BFS Traversals are:-");
        g.BFSTraversal(1);
        System.out.println();
        g.BFSTraversal(2);
        System.out.println();
        g.BFSTraversal(3);
        System.out.println();
        g.BFSTraversal(4);
        System.out.println();
        System.out.println("SSSP below from 1 to all");
        g.SSSP(1);
        System.out.println("SSSP below from 4 to all");
        g.SSSP(4);
    }
}