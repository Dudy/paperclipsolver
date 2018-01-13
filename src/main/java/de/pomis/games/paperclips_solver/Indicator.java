package de.pomis.games.paperclips_solver;

public enum Indicator {

    CLIPS("clips"),
    WIRES("wire"),
    FUNDS("funds"),
    WIRE_COST("wireCost"),
    UNSOLD_CLIPS("unsoldClips"),
    AUTOCLIPPERS("clipmakerLevel2"),
    PROCESSORS("processors"),
    MEMORY("memory"),
    MARKETING_COST("adCost"),
    AUTOCLIPPER_COST("clipperCost");
    
    private final String id;

    private Indicator(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    
}
