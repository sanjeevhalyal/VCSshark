package versiontree;

public class versiontreeroot extends versiontree {
    private  String branch;

    public versiontreeroot(versiontree o, String root) {
        super(o,root,true);
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
