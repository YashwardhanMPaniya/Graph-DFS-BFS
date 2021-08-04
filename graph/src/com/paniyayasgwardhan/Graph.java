package com.paniyayasgwardhan;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Graph {
    public static Scanner  sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("ENTER VERTICES");
        int ver = sc.nextInt();
        int e = sc.nextInt();
        Graph graph = new Graph(ver);
        System.out.println("add edge");
        for(int i=0;i<e;i++){
            int s = sc.nextInt();
            int d = sc.nextInt();
            graph.addEdge(s,d);
        }

        System.out.println("Enter Source And Dest");
        int s = sc.nextInt();
        int d = sc.nextInt();
      int dis = graph.bfs(s,d);
        System.out.println(dis);
        System.out.println(graph.dfs(s,d));

        System.out.println(graph.dsfStack(s,d));
    }


    LinkedList<Integer> adj[];

    public Graph(int v){
        adj = new LinkedList[v];
        for(int i=0;i<v;i++){
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int source,int destination){
        adj[source].add(destination);
        adj[destination].add(source);

    }

    public int bfs(int source,int destination){

        boolean[] vis = new boolean[adj.length];
        int[] parent = new int[adj.length];
        Queue<Integer> ver = new LinkedList<>();

        vis[source]= true;
        parent[source]= -1;
        ver.add(source);

        while(!ver.isEmpty()){
            int cur = ver.poll();
            if(cur == destination) break;
            for(int neighbour : adj[cur]){
                if(vis[neighbour] == false){
                    vis[neighbour] = true;
                    ver.add(neighbour);
                    parent[neighbour]= cur;
                }
            }
        }
        int recent = destination;
        int dist =0;
        while(parent[recent] != -1){
            System.out.println(recent+"-->");
            recent = parent[recent];

            dist++;
        }



        return dist;
    }
    public boolean dfs(int source,int destination){
        boolean[] vis = new boolean[adj.length];
        vis[source] = true;

        return dfsutil(source,destination,vis);
    }

    private boolean dfsutil(int source,int destination,boolean[] vis){

        if(source == destination) return true;

        for(int neighbour : adj[source]){
            if(!vis[neighbour]){
                vis[neighbour] = true;
                boolean con = dfsutil(neighbour,destination,vis);
                if(con) return true;
            }
        }
       return false;
    }

    public boolean dsfStack(int source,int destination){
        boolean[] vis = new boolean[adj.length];
        vis[source] = true;
        Stack<Integer> q = new Stack<>();
        q.push(source);
        while (!q.isEmpty()){
            int cur = q.pop();
            if(cur == destination) return true;
            for(int n: adj[cur]){
                if(!vis[n]){
                    vis[n]=true;
                    q.push(n);
                }
            }
        }
       return false;
    }




}
