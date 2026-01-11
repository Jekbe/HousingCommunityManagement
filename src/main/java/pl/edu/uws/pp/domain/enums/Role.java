package pl.edu.uws.pp.domain.enums;

public enum Role {
    HOUSING_MANAGER(3),
    BUILDING_MANAGER(2),
    RESIDENT(1);

    private final int level;

    Role(int level){
        this.level = level;
    }

    public boolean isHigher(Role other){
        return this.level > other.level;
    }
}
