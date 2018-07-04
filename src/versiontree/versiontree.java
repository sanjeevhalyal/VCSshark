package versiontree;

import java.util.ArrayList;
import java.util.List;

public class versiontree implements java.io.Serializable{
    private String shavalue;
    private versiontree parent;
    private String name;
    private List<versiontree> subs;
    private boolean folder;

    public boolean isFolder() {
        return folder;
    }

    public void setFolder(boolean folder) {
        this.folder = folder;
    }

    public List<versiontree> getSubs() {
        return subs;
    }

    public void setSubs(List<versiontree> subs) {
        this.subs = subs;
    }

    public versiontree() {
    }

    public String getShavalue() {
        return shavalue;

    }

    private void asignsubs() {
        subs = new ArrayList<versiontree>();
    }

    public void setShavalue(String shavalue) {
        this.shavalue = shavalue;

    }

    public versiontree getParent() {
        return parent;
    }

    public void setParent(versiontree parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public versiontree(String shavalue, versiontree parent, String name,boolean flag) {
        this.shavalue = shavalue;
        this.parent = parent;
        this.name = name;
        asignsubs();
        folder=flag;
    }

    public versiontree(versiontree parent, String name) {
        this.parent = parent;
        this.name = name;
        asignsubs();

    }

    public versiontree(versiontree parent, String name,boolean flag) {
        this.parent = parent;
        this.name = name;
        folder=flag;
        asignsubs();

    }

    public void printtree() {
        versiontree node = parent == null ? this : findparent(parent);

        System.out.println(node.name);
        System.out.println(node.shavalue);


    }

    private versiontree findparent(versiontree node) {
        return node.parent != null ? findparent(node.parent) : node;
    }

}
