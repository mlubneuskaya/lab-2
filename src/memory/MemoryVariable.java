package memory;

public enum MemoryVariable {
    MEM1("mem1"),
    MEM2("mem2"),
    MEM3("mem3");

    private final String name;

    MemoryVariable(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
