import java.util.*;



class DisjointSet {
    private Map<Ubicacion1, Ubicacion1> parent;
    private Map<Ubicacion1, Integer> rank;

    public DisjointSet() {
        parent = new HashMap<>();
        rank = new HashMap<>();
    }

    public void makeSet(Ubicacion1 ubicacion) {
        parent.put(ubicacion, ubicacion);
        rank.put(ubicacion, 0);
    }

    public Ubicacion1 find(Ubicacion1 ubicacion) {
        if (parent.get(ubicacion) != ubicacion) {
            parent.put(ubicacion, find(parent.get(ubicacion)));
        }
        return parent.get(ubicacion);
    }

    public void union(Ubicacion1 ubicacion1, Ubicacion1 ubicacion2) {
    	Ubicacion1 root1 = find(ubicacion1);
    	Ubicacion1 root2 = find(ubicacion2);

        if (root1 == root2) return;

        if (rank.get(root1) < rank.get(root2)) {
            parent.put(root1, root2);
        } else if (rank.get(root1) > rank.get(root2)) {
            parent.put(root2, root1);
        } else {
            parent.put(root2, root1);
            rank.put(root1, rank.get(root1) + 1);
        }
    }
}





 
    


    

