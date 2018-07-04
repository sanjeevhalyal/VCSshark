package treeoperations;

import branchoperations.listtree;
import versiontree.versiontree;

import java.util.LinkedList;

public class gettreeaslist {
    public static LinkedList<listtree> getversiontreeaslist(versiontree tree)
    {
        LinkedList<listtree> out=new LinkedList<listtree>();
        addtreetolist(out,tree);

        return out;
    }

    private static void addtreetolist(LinkedList<listtree> out, versiontree tree) {

        String path= getpath.getpath(tree);
        String filename=tree.getName();
        if(tree.isFolder())
        {
            versiontree[] subNote = tree.getSubs().stream().toArray(versiontree[]::new);
            out.add(new listtree(tree.getShavalue(),tree.isFolder(),path,filename));


            for(versiontree sub:subNote)
            {
                addtreetolist(out,sub);
            }
        }
        else
        {

            out.add(new listtree(tree.getShavalue(),tree.isFolder(),path,filename));
        }
    }
}
