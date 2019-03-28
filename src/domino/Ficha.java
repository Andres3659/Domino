package domino;

public class Ficha {
    private int numA;
    private int numB;

    public Ficha(int numA, int numB) {
        this.numA = numA;
        this.numB = numB;
    }
    
    public Ficha(Ficha f) {
        this.numA = f.numA;
        this.numB = f.numB;
    }

    public int getNumA() {
        return numA;
    }

    public void setNumA(int numA) {
        this.numA = numA;
    }

    public int getNumB() {
        return numB;
    }

    public void setNumB(int numB) {
        this.numB = numB;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.numA;
        hash = 17 * hash + this.numB;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ficha other = (Ficha) obj;
        if (this.numA != other.numA) {
            return false;
        }
        return this.numB == other.numB;
    }
}