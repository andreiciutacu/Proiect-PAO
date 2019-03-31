package People;

public class Provider {
    private String name;
    static int nrContracte = 0;
    private int contractNumber;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(int contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Provider(String name) {
        this.name = name;
        nrContracte++;
        contractNumber = nrContracte;
    }




}
