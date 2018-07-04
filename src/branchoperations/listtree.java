package branchoperations;

public class listtree implements java.io.Serializable{
    private String sha;
    private boolean folder;
    private String pathname;
    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public boolean isFolder() {
        return folder;
    }

    public void setFolder(boolean folder) {
        this.folder = folder;
    }

    public String getPathname() {
        return pathname;
    }

    public void setPathname(String pathname) {
        this.pathname = pathname;
    }

    public String getSha() {

        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public listtree(String sha, boolean folder, String pathname,String filename) {
        this.sha = sha;
        this.folder = folder;
        this.pathname = pathname;
        this.filename=filename;
    }

}
