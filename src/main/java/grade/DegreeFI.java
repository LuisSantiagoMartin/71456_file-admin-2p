package grade;

public class DegreeFI {
    private int idDegree;
    private String degreeName;

    public DegreeFI() {}

    public DegreeFI(int idDegree, String degreeName) {
        this.idDegree = idDegree;
        this.degreeName = degreeName;
    }

    public void setDegreeName(String degreeName) { this.degreeName = degreeName; }
    public String getDegreeName() { return this.degreeName; }

    public void setIdDegree(int idDegree) { this.idDegree = idDegree; }
    public int getIdDegree() { return this.idDegree; }

    @Override
    public String toString() {
        return idDegree + "|" + degreeName;
    }
}
