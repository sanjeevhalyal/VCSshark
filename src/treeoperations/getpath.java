package treeoperations;

import versiontree.versiontree;

public class getpath {

    public static  String getpath(versiontree tree) {

        if(tree.getParent()==null)
        {
            return "\\";
        }
        String out="";
        tree=tree.getParent();
        while(tree.getParent()!=null)
        {
            out=tree.getName()+"\\"+out;
            tree=tree.getParent();
        }
        return out;
    }
}
