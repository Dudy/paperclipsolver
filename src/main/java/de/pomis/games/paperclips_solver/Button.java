package de.pomis.games.paperclips_solver;

public enum Button {

    MAKE_PAPERCLIP("btnMakePaperclip"),
    LOWER_PRICE("btnLowerPrice"),
    RAISE_PRICE("btnRaisePrice"),
    MARKETING("btnExpandMarketing"),
    BUY_WIRE("btnBuyWire"),
    AUTOCLIPPER("btnMakeClipper"),
    ADD_PROCESSOR("btnAddProc"),
    ADD_MEMORY("btnAddMem");
    
    private final String id;

    private Button(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    
}
