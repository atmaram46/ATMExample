package atm.def;

public enum CurrencyDenom {
	Twenty(20),
    Ten(10),
    Five(5),
    One(1);
    
    private final int denom;
    
    private CurrencyDenom(int denom) {
    	this.denom = denom;
    }
    
    public int getDenom() {
    	return denom;
    }
}
